package com.ws.darkstar.parser.bean;

import org.json.simple.JSONObject;

public class JSONBean {
	
	private String[] attributes;
	
	private JSONObject jsonObject;
	
	public JSONBean() {}

	public String[] getAttributes() {
		return attributes;
	}

	public void setAttributes(String[] attributes) {
		this.attributes = attributes;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

}
