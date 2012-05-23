package com.ws.darkstar.mongo.dao;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.ws.darkstar.mongo.connection.XMongoConnection;

public class XMongoDAO {
	
	private XMongoConnection xConn;
	
	private String collectionName = ResourceBundle.getBundle("xtractor").getString("mongo.collection");
	
	public XMongoDAO() {
		xConn = new XMongoConnection();
	}
	
	public boolean insert(BasicDBObject b) {
		boolean done = false;
		
		try {
			
			xConn.getCollection(collectionName).insert(b);
			
			done = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return done;
	}
	
	public Set<String> getCollectionsName() {
		return xConn.getCollectionsNames();
	}
	
	public DBObject findFirst() {
		return xConn.getCollection(collectionName).findOne();
	}
	
	public DBCursor listAllDocuments() {
		return xConn.getCollection(collectionName).find();
	}
	
	public long getCollectionCount() {
		return xConn.getCollection(collectionName).getCount();
	}
	
	public Set<DBObject> findByFlProcessamento(String param) {
		
		Set<DBObject> setBasicObjects = new HashSet<DBObject>();
		
		BasicDBObject query = new BasicDBObject();
		query.put("fl_processamento", param);
		
		DBCursor cursor = xConn.getCollection(collectionName).find(query);
		
		while (cursor.hasNext()) {
			setBasicObjects.add(cursor.next());
		}
		
		return setBasicObjects;
		
	}
	
	public Set<DBObject> findByParam(String field, String param) {
		
		Set<DBObject> setBasicObjects = new HashSet<DBObject>();
		
		BasicDBObject query = new BasicDBObject();
		query.put(field, param);
		
		DBCursor cursor = xConn.getCollection(collectionName).find(query);
		
		while (cursor.hasNext()) {
			setBasicObjects.add(cursor.next());
		}
		
		return setBasicObjects;
		
	}
	
	public DBObject findById(String id) {
		BasicDBObject query = new BasicDBObject();
		query.put("id_endereco", id);
		
		DBCursor cursor = xConn.getCollection(collectionName).find(query);
		
		DBObject dbObject = null;
		
		while (cursor.hasNext()) {
			dbObject = cursor.next();
		}

		return dbObject;
	}
	
	public DBObject update(String field, String param, String id) {
		DBObject dbo = findById(id);
		dbo.put(field, param);
		
		xConn.getCollection(collectionName).update(new BasicDBObject().append(field, id), dbo);
		
		return findById(id);
	}
	
	public void drop() {
		xConn.getCollection(collectionName).drop();
	}
	
	public void closeMongo() {
		xConn.close();
	}

}
