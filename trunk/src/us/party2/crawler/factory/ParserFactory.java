package us.party2.crawler.factory;

import us.party2.crawler.adapter.IParserAdapter;
import us.party2.crawler.parser.ParserBlueTicket;
import us.party2.crawler.parser.ParserFestasUfsc;
import us.party2.crawler.parser.ParserLastFM;
import us.party2.crawler.parser.ParserV1C;

public class ParserFactory {
	
	public static IParserAdapter createParserV1C(){
		return new ParserV1C();
	}
	
	public static IParserAdapter createParserBlueTicket(){
		return new ParserBlueTicket();
	}
	
	public static IParserAdapter createParserFestasUfsc(){
		return new ParserFestasUfsc();
	}
	
	public static IParserAdapter createParserLastFM(){
		return new ParserLastFM();
	}
}
