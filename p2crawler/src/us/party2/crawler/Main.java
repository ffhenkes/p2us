package us.party2.crawler;

import java.sql.SQLException;
import java.util.ArrayList;

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
		String sb = sock.getBTData();
		if (sb.length() > 0) {
			ParserBlueTicket parserBT = new ParserBlueTicket();
			parserBT.setResponse(sb);
			ArrayList<IBean> beans = parserBT.parse();			
			DaoBlueTicket dao = new DaoBlueTicket();
			dao.insert(beans);
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
