package com.ws.darkstar.service;

import java.util.Set;

import org.json.simple.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.ws.darkstar.mongo.dao.XMongoDAO;
import com.ws.darkstar.parser.XMongoParser;
import com.ws.darkstar.parser.factory.XJSONParserFactory;

public class XService {
	
	public static boolean loadMongo(String path) {
		
		XMongoParser xMongoParser = new XMongoParser();
		
		Set<BasicDBObject> setBasicObjects = xMongoParser.parse(path);

		XMongoDAO xMongoDAO = new XMongoDAO();
		
		boolean done = false;
		
		for (BasicDBObject basicDBObject : setBasicObjects) {
			done = xMongoDAO.insert(basicDBObject);
		}
		
		return done;
	}
	
	public static JSONArray loadJSON(String path, String param) {
		return XJSONParserFactory.valueOf(param).parse(path);
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
	
	
	public static void findByFlProcessamento(String param) {
		XMongoDAO dao = new XMongoDAO();
		
		for (DBObject dbo : dao.findByFlProcessamento(param)) {
			System.out.println(dbo);
		}
	}
	
	public static void update() {
		XMongoDAO dao = new XMongoDAO();
		String idEndereco = "33641344";
		
		System.out.println(dao.update(idEndereco));
	}
	
	public static void closeMongo() {
		new XMongoDAO().closeMongo();
	}

}
