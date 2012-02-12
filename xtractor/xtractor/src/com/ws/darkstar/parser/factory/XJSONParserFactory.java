package com.ws.darkstar.parser.factory;

import org.json.simple.JSONArray;

import com.ws.darkstar.parser.XJSONCsvParser;

public enum XJSONParserFactory {
	
	CSV {

		@Override
		public JSONArray parse(String s) {
			return new XJSONCsvParser().parse(s);
		}
		
	} ,
	
	XML {

		@Override
		public JSONArray parse(String s) {
			return null;
		}
		
	};
	
	public abstract JSONArray parse(String s);
	
	private XJSONParserFactory() {
		
	}

}
