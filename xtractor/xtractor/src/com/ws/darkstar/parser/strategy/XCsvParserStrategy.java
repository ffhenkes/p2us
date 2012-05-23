package com.ws.darkstar.parser.strategy;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.mongodb.BasicDBObject;
import com.ws.darkstar.parser.bean.JSONBean;
import com.ws.darkstar.parser.bean.MongoBean;

public enum XCsvParserStrategy {
	
	JSON_ATTRIBUTES {

		
		@SuppressWarnings("unchecked")
		@Override
		public Map<?, ?> parse(Map<?, ?> map) {
			
			JSONBean jsonBean = (JSONBean) map.get(-2);
			String line = (String) map.get(map.get(-3));
			jsonBean.setAttributes(line.trim().replaceAll(" ", "").split((String) map.get(-1)));
			
			Map<Integer, Object> hashMap = new HashMap<Integer, Object>();
			hashMap = (Map<Integer, Object>) map;
			hashMap.put(-2, jsonBean);
			
			return hashMap;
		}
		
	},
	
	JSON_VALUES {

		@SuppressWarnings("unchecked")
		@Override
		public Map<?, ?> parse(Map<?, ?> map) {
			
			String line = (String) map.get(map.get(-3));
			String[] values = line.trim().split((String) map.get(-1));
			
			JSONBean jsonBean = (JSONBean) map.get(-2);
			JSONObject jsonObject = new JSONObject();
			
			int i = 0;
			for (String attribute : jsonBean.getAttributes()) {
				jsonObject.put(attribute, values[i].trim());
				i++;
			}
			
			jsonBean.setJsonObject(jsonObject);
			
			Map<Integer, Object> hashMap = new HashMap<Integer, Object>();
			hashMap = (Map<Integer, Object>) map;
			hashMap.put(-2, jsonBean);
			
			return hashMap;
		}
		
	},
	
	MONGO_ATTRIBUTES {

		@SuppressWarnings("unchecked")
		@Override
		public Map<?, ?> parse(Map<?, ?> map) {
			MongoBean mongoBean = (MongoBean) map.get(-2);
			String line = (String) map.get(map.get(-3)).toString();
			
			Boolean useRandom = (Boolean) map.get(-4);
			
			Boolean useCustom = (Boolean) map.get(-5);
			
			if (useRandom)
				line+=map.get(-1)+"randomAttribute";
			
			if (useCustom)
				line=(String) map.get(-6)+map.get(-1)+line;
			
			mongoBean.setAttributes(line.trim().replaceAll(" ", "").split((String) map.get(-1)));
			
			Map<Integer, Object> hashMap = new HashMap<Integer, Object>();
			hashMap = (Map<Integer, Object>) map;
			hashMap.put(-2, mongoBean);
			
			return hashMap;
		}
		
	},
	
	MONGO_VALUES {

		@SuppressWarnings("unchecked")
		@Override
		public Map<?, ?> parse(Map<?, ?> map) {
			
			String line = (String) map.get(map.get(-3)).toString();
			
			Boolean useRandom = (Boolean) map.get(-4);
			
			Boolean useCustom = (Boolean) map.get(-5);
			
			if (useRandom) {
				Double randomAttribute = Math.random();
				line+=map.get(-1)+randomAttribute.toString();
			}
			
			if (useCustom) {
				line=(String) map.get(-7)+map.get(-1)+line;
			}
			
			System.out.println(line);
			
			String[] values = line.trim().split((String) map.get(-1));
			
			MongoBean mongoBean = (MongoBean) map.get(-2);
			BasicDBObject basicObject = new BasicDBObject();
			
			int i = 0;
			for (String attribute : mongoBean.getAttributes()) {
				basicObject.put(attribute, values[i].trim());
				i++;
			}
			
			mongoBean.setBasicObject(basicObject);
			
			Map<Integer, Object> hashMap = new HashMap<Integer, Object>();
			hashMap = (Map<Integer, Object>) map;
			hashMap.put(-2, mongoBean);
			
			return hashMap;
		}
		
	};
	
	public abstract Map<?, ?> parse(Map<?, ?> map);

	private XCsvParserStrategy() {
		
	}
	

}
