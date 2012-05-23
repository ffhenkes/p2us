package com.ws.darkstar.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.ws.darkstar.mongo.dao.XMongoDAO;
import com.ws.darkstar.parser.bean.MongoBean;
import com.ws.darkstar.parser.constant.XConstants;
import com.ws.darkstar.parser.strategy.XCsvParserStrategy;

public class XMongoParser implements IParser<String, Boolean>{
	
	private MongoBean mongoBean = new MongoBean();
	
	private Boolean randomAttribute = Boolean.parseBoolean(ResourceBundle.getBundle("xtractor").getString("mongo.randomattribute"));
	
	private Boolean customAttribute = Boolean.parseBoolean(ResourceBundle.getBundle("xtractor").getString("mongo.customattribute"));
	
	private String customAttributeName = ResourceBundle.getBundle("xtractor").getString("mongo.customattribute.name");
	
	private String customAttributeValue = ResourceBundle.getBundle("xtractor").getString("mongo.customattribute.value");
	
	private long position = 0;
	
	private XMongoDAO dao = new XMongoDAO();
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean parse(String p) {
		
		Boolean done = false;
		
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
				hashMap.put(-5, isCustomAttribute());
				hashMap.put(-6, getCustomAttributeName());
				hashMap.put(-7, getCustomAttributeValue());
				
				hashMap = (Map<Integer, Object>) XCsvParserStrategy.valueOf((counter < 1) ? XConstants.MATTRIBUTES : XConstants.MVALUES).parse(hashMap);
				
				mongoBean = (MongoBean) hashMap.get(-2);
				
				if (counter > 0)
					dao.insert(mongoBean.getBasicObject());
					//setBasicObjects.add(mongoBean.getBasicObject());
				
				buffer.clear();
				
				inChannel.position(position);
				
				bytesRead = inChannel.read(buffer);
				
				counter++;
			}
			
			randomFile.close();
			inChannel.close();
			done = true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return done;
	}

	public Boolean isRandomAttribute() {
		return randomAttribute;
	}
	
	public Boolean isCustomAttribute() {
		return customAttribute;
	}

	public void setRandomAttribute(Boolean randomAttribute) {
		this.randomAttribute = randomAttribute;
	}

	public Boolean getCustomAttribute() {
		return customAttribute;
	}

	public void setCustomAttribute(Boolean customAttribute) {
		this.customAttribute = customAttribute;
	}

	public String getCustomAttributeName() {
		return customAttributeName;
	}

	public void setCustomAttributeName(String customAttributeName) {
		this.customAttributeName = customAttributeName;
	}

	public String getCustomAttributeValue() {
		return customAttributeValue;
	}

	public void setCustomAttributeValue(String customAttributeValue) {
		this.customAttributeValue = customAttributeValue;
	}
	
}
