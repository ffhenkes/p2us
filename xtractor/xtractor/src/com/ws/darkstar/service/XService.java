package com.ws.darkstar.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.ws.darkstar.mongo.dao.XMongoDAO;
import com.ws.darkstar.parser.XMongoParser;
import com.ws.darkstar.parser.factory.XJSONParserFactory;

public class XService {
	
	public static boolean loadMongoMultiple(Map<?, ?> map) {
		
		String path = (String) map.get("path");
		String prefix = (String) map.get("prefix");
		String list = (String) map.get("list");
		
		String[] values = list.split(";");
		
		XMongoParser xMongoParser = new XMongoParser();
		
		boolean done = false;
		
		//int counter = 0;
		
		for (String file : values) {
			done = xMongoParser.parse(path+prefix+file);
			System.out.println("cicle ended! "+file+" >>"+done);
			//counter++;
		}
		
		return done;
		
	}
	
	public static boolean loadMongo(String path) {
		XMongoParser xMongoParser = new XMongoParser();
		return xMongoParser.parse(path);
	}
	
	public static String loadJSON(String path, String param) {
		
		String output = ResourceBundle.getBundle("xtractor").getString("json.output");
		boolean done = false;
		
		try {
			FileWriter fileWriter = new FileWriter(output);
			
			XJSONParserFactory.valueOf(param).parse(path).writeJSONString(fileWriter);
			
			done = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return done ? "File saved at "+output : "Error creating "+output;
	}
	
	public static void showCollectionsNames() {
		
		XMongoDAO dao = new XMongoDAO();
		
		Set<String> collections = dao.getCollectionsName();
		
		for (String string : collections) {
			System.out.println(string);
		}
	}
	
	public static String showFirst() {
		XMongoDAO dao = new XMongoDAO();
		
		return dao.findFirst().toString();
	}
	
	public static void listAllDocuments() {
		XMongoDAO dao = new XMongoDAO();
		
		DBCursor cursor = dao.listAllDocuments(); 
		
		int i = 0;
		 while(cursor.hasNext()) {
	        System.out.println("counter >> "+i+">> "+cursor.next());
	        i++;
	     }
	}
	
	public static long getCollectionCount() {
		XMongoDAO dao = new XMongoDAO();
		return dao.getCollectionCount(); 
	}
	
	public static void findByParam(String field, String param) {
		XMongoDAO dao = new XMongoDAO();
		
		for (DBObject dbo : dao.findByParam(field, param)) {
			System.out.println(dbo);
		}
	}
	
	public static void update(String field, String param, String id) {
		XMongoDAO dao = new XMongoDAO();
		
		System.out.println(dao.update(field, param, id));
	}
	
	public static void drop() {
		XMongoDAO dao = new XMongoDAO();
		dao.drop();
		getCollectionCount();
	}
	
	public static void closeMongo() {
		new XMongoDAO().closeMongo();
	}

}
