package com.ws.darkstar.main;

import com.ws.darkstar.main.strategy.XMainStrategy;
import com.ws.darkstar.service.XService;

public class XMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMainStrategy.valueOf(args[0]).go(args);
		XService.closeMongo();
		
	}

}
