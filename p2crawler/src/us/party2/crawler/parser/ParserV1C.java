package us.party2.crawler.parser;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyHtmlSerializer;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

import us.party2.crawler.adapter.IParserAdapter;

public class ParserV1C implements IParserAdapter {

	private String response;
	
	@Override
	public void parse() {
		//.replaceAll("\\<.*?>", "")
		StringUtils.deleteWhitespace(response);
		
		System.out.println("parse1>> "+response.indexOf("class=\"mlink\">"));
		System.out.println("parse2>> "+response.indexOf("OFERTA DO DIA:"));
		
		response = response.substring(response.indexOf("class=\"mlink\">"), response.indexOf("OFERTA DO DIA:"));
		
		
		System.out.println("parser test>>"+response);
		
		/*
		HtmlCleaner hc = new HtmlCleaner();
		CleanerProperties props = hc.getProperties();
		props.setTranslateSpecialEntities(true);
		props.setTransResCharsToNCR(true);
		props.setOmitComments(true);
		TagNode node = hc.clean(response);
		*/
		
		
		
		//TagNode[] nodes = node.getElementsByName("", true);
		/*
		try {
			new PrettyXmlSerializer(props).writeToFile(node, "/home/fabio/Develop/parseado.xml", "utf-8");
			new PrettyHtmlSerializer(props).writeToFile(node, "/home/fabio/Develop/htm_parseado.xml", "utf-8");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
		
		//System.out.println("parser>> "+response);
		
		//String titulo = response.substring(response.indexOf(" class=\"mlink\"><B>"), response.indexOf("</B></A></TD>"));
		//System.out.println(titulo);
		
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
