package com.ws.darkstar.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;

import com.ws.darkstar.parser.bean.JSONBean;
import com.ws.darkstar.parser.constant.XConstants;
import com.ws.darkstar.parser.strategy.XCsvParserStrategy;

public class XJSONCsvParser implements IParser<String, JSONArray> {

	private String separator = ResourceBundle.getBundle("xtractor").getString("csv.separator");
	
	private JSONArray jsonResponseArray = new JSONArray();
	
	private JSONBean jsonBean = new JSONBean();
	
	public XJSONCsvParser() {
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public JSONArray parse(String p) {
		
		try {
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(p));
			
			String line = bufferedReader.readLine();
			
			int counter=0;
			
			while (line != null) {
				
				Map<Integer, Object> hashMap = new HashMap<Integer, Object>();
				hashMap.put(counter, line);
				hashMap.put(-1, separator);
				hashMap.put(-2, jsonBean);
				hashMap.put(-3, counter);
				
				System.out.println("counter >> "+counter);
				
				hashMap = (Map<Integer, Object>) XCsvParserStrategy.valueOf((counter < 1) ? XConstants.JATTRIBUTES : XConstants.JVALUES).parse(hashMap);
				
				jsonBean = (JSONBean) hashMap.get(-2);
				
				if (counter > 0)
					jsonResponseArray.add(jsonBean.getJsonObject());
				
				line = bufferedReader.readLine();
				counter++;
			}
			
			bufferedReader.close();
			
		} catch (FileNotFoundException e) {
			//throw new XParserException(e);
			e.printStackTrace();
			
		} catch (IOException e) {
			//throw new XParserException(e);
			e.printStackTrace();
		}
		
		return jsonResponseArray;
	}

}


