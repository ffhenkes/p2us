package com.ws.darkstar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.ws.darkstar.parser.bean.MongoBean;
import com.ws.darkstar.parser.constant.XConstants;
import com.ws.darkstar.parser.strategy.XCsvParserStrategy;

public class XMongoParser implements IParser<String, Set<BasicDBObject>>{
	
	private MongoBean mongoBean = new MongoBean();
	
	private Set<BasicDBObject> setBasicObjects = new HashSet<BasicDBObject>();
	
	private Boolean randomAttribute = Boolean.parseBoolean(ResourceBundle.getBundle("xtractor").getString("mongo.randomattribute"));
	
	private long position = 0;
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<BasicDBObject> parse(String p) {
		
		String separator = ResourceBundle.getBundle("xtractor").getString("csv.separator");
		
		try {
			
			File file = new File(p);
			
			RandomAccessFile randomFile = new RandomAccessFile(file, "r");
			
			FileChannel inChannel = randomFile.getChannel();
			
			ByteBuffer buffer = ByteBuffer.allocate(256);
			
			int bytesRead = inChannel.read(buffer);
			
			int counter=0;
			
			System.out.println("parser!");
			
			while (bytesRead != -1) {
				buffer.flip();
				
				StringBuilder line = new StringBuilder();
				
				while (buffer.hasRemaining()) {
					line.append((char) buffer.get());
				}
				
				long index = line.indexOf("\n");
				line.deleteCharAt((int)index);

				position = position+index+1;
				
				line.delete((int)index, line.length());

				Map<Integer, Object> hashMap = new HashMap<Integer, Object>();
				hashMap.put(counter, line);
				hashMap.put(-1, separator);
				hashMap.put(-2, mongoBean);
				hashMap.put(-3, counter);
				hashMap.put(-4, isRandomAttribute());
				
				hashMap = (Map<Integer, Object>) XCsvParserStrategy.valueOf((counter < 1) ? XConstants.MATTRIBUTES : XConstants.MVALUES).parse(hashMap);
				
				mongoBean = (MongoBean) hashMap.get(-2);
				
				if (counter > 0)
					setBasicObjects.add(mongoBean.getBasicObject());
				
				buffer.clear();
				
				inChannel.position(position);
				
				bytesRead = inChannel.read(buffer);
				
				counter++;
			}
			
			randomFile.close();
			inChannel.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return setBasicObjects;
	}

	public Boolean isRandomAttribute() {
		return randomAttribute;
	}

	public void setRandomAttribute(Boolean randomAttribute) {
		this.randomAttribute = randomAttribute;
	}
	
}
