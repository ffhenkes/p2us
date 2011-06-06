package us.party2.crawler;

import us.party2.crawler.factory.SocketFactory;
import us.party2.crawler.parser.ParserV1C;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//desenhar workflow 
		SocketFactory sock = new SocketFactory();
		String sb = sock.getData();
		
		if (sb.length() > 0) {
			ParserV1C parserVC1 = new ParserV1C();
			parserVC1.setResponse(sb);
			parserVC1.parse();
		}	
		
	}

}
