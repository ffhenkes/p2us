package com.ws.darkstar.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public Set<BasicDBObject> parse(String p) {
		
		String separator = ResourceBundle.getBundle("xtractor").getString("csv.separator");
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(p));
			
			String line = bufferedReader.readLine();
			
			int counter=0;
			
			while (line != null) {
				
				Map<Integer, Object> hashMap = new HashMap<Integer, Object>();
				hashMap.put(counter, line);
				hashMap.put(-1, separator);
				hashMap.put(-2, mongoBean);
				hashMap.put(-3, counter);
				hashMap.put(-4, isRandomAttribute());
				
				System.out.println("counter >> "+counter);
				
				hashMap = (Map<Integer, Object>) XCsvParserStrategy.valueOf((counter < 1) ? XConstants.MATTRIBUTES : XConstants.MVALUES).parse(hashMap);
				
				mongoBean = (MongoBean) hashMap.get(-2);
				
				if (counter > 0)
					setBasicObjects.add(mongoBean.getBasicObject());
				
				line = bufferedReader.readLine();
				counter++;
			}
			
			
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
