package us.party2.crawler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import us.party2.crawler.bean.BeanV1C;
import us.party2.crawler.bean.IBean;
import us.party2.crawler.dao.DaoBlueTicket;
import us.party2.crawler.dao.DaoV1C;
import us.party2.crawler.factory.SocketFactory;
import us.party2.crawler.parser.ParserBlueTicket;
import us.party2.crawler.parser.ParserV1C;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//desenhar workflow para todas integracoes 
		SocketFactory sock = new SocketFactory();
		
		//public .. BT
		String[] arrayBTCities = {"30", "12", "101", "99", "95", "72", "98", "67", "54", "56", "103", "80", "35", "52", "93", "32", "38", "43", "64", "29", "6", "19", "27", "1", "15", "9", "7", "26", "68", "8", "90"};
		
		for (int i = 0; i < arrayBTCities.length; i++) {			
			
			String sb = sock.getBTData(arrayBTCities[i]);
			if (sb.length() > 0) {
				ParserBlueTicket parserBT = new ParserBlueTicket();
				parserBT.setResponse(sb);
				ArrayList<IBean> beans = parserBT.parse();			
				DaoBlueTicket dao = new DaoBlueTicket();
				try {
					dao.insert(beans);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		/*
		 * public ... V1C
		String sb = sock.getData();
		
		if (sb.length() > 0) {
			ParserV1C parserVC1 = new ParserV1C();
			parserVC1.setResponse(sb);
			ArrayList<IBean> beans = parserVC1.parse();			
			try {
				DaoV1C dao = new DaoV1C();
				dao.insert(beans);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		*/
	}

}
