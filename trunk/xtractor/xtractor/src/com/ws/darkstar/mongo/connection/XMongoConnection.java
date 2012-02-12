package com.ws.darkstar.mongo.connection;

import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class XMongoConnection {

	private String host = ResourceBundle.getBundle("xtractor").getString("mongo.host");
	
	private Integer port = Integer.parseInt(ResourceBundle.getBundle("xtractor").getString("mongo.port"));
	
	private String dbname = ResourceBundle.getBundle("xtractor").getString("mongo.dbname");
	
	private String username = ResourceBundle.getBundle("xtractor").getString("mongo.username");
	
	private String password = ResourceBundle.getBundle("xtractor").getString("mongo.password");
	
	private Mongo mongo;
	
	private DB db;

	public XMongoConnection(String host, Integer port, String dbname, String username,
			String password) {
		
		this.host = host;
		this.port = port;
		this.dbname = dbname;
		this.username = username;
		this.password = password;
	}

	public XMongoConnection() {}
	
	private Mongo getMongo() {
		
		if (mongo == null) {
			try {
				mongo = new Mongo(host, port);
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
		}
		
		return mongo;
	}
	
	private DB getDB() {
		
		if (db == null) {
			db = getMongo().getDB(dbname);
		}
		
		return db;
	}
	
	public Boolean isAuthenticated() {
		return getDB().isAuthenticated();
	}
	
	public DBCollection getCollection(String colName) {
		return getDB().getCollection(colName);
	}
	
	public Set<String> getCollectionsNames() {
		return getDB().getCollectionNames();
	}
	
	public void close() {
		getMongo().close();
	}
	
}
