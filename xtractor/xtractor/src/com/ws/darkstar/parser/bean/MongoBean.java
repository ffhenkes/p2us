package com.ws.darkstar.parser.bean;

import com.mongodb.BasicDBObject;

public class MongoBean {
	
	private String[] attributes;
	
	private BasicDBObject basicObject;

	public MongoBean() {}

	public String[] getAttributes() {
		return attributes;
	}

	public void setAttributes(String[] attributes) {
		this.attributes = attributes;
	}

	public BasicDBObject getBasicObject() {
		return basicObject;
	}

	public void setBasicObject(BasicDBObject basicObject) {
		this.basicObject = basicObject;
	}
	

}
