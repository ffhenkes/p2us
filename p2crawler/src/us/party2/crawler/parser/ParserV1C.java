package us.party2.crawler.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyHtmlSerializer;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

import us.party2.crawler.adapter.IParserAdapter;
import us.party2.crawler.bean.BeanV1C;

public class ParserV1C implements IParserAdapter {

	private String response;
	
	@Override
	public void parse() {
	
		ArrayList<String> blocks = new ArrayList();
		ArrayList<TagNode> nodes = new ArrayList();
		
		//extrai centro da pagina
		String kernel = response.substring(response.indexOf("<TD bgcolor=\"#F7F1DE\" align=\"left\" colspan=\"2\">", 0), response.lastIndexOf("<HR size=\"1\" color=\"#EDD28F\" width=\"95%\">"));
		
		//System.out.println(kernel);
		//enqto tiver bloco de interesse, extrai bloco e insere no array de blocos
		do{						
			String block = kernel.substring(kernel.indexOf("<TD bgcolor=\"#F7F1DE\" align=\"left\" colspan=\"2\">"),
						kernel.indexOf("<HR size=\"1\" color=\"#EDD28F\" width=\"95%\">"));	
			blocks.add(block);
			//System.out.println(block);
			
			HtmlCleaner hc = new HtmlCleaner();
			CleanerProperties props = hc.getProperties();
			props.setTranslateSpecialEntities(true);
			props.setTransResCharsToNCR(true);
			props.setOmitComments(true);
			
			TagNode node = hc.clean(block);
			nodes.add(node);
						
			kernel = kernel.substring(kernel.indexOf("<TD bgcolor=\"#F7F1DE\" align=\"left\" colspan=\"2\">",200));
			//System.out.println(kernel);
			
		}while(kernel.indexOf("<TD bgcolor=\"#F7F1DE\" align=\"left\" colspan=\"2\">",200)>0);
			
		parseBlocks(blocks);
		/*
		//TagNode[] nodes = node.getElementsByName("", true);
		try {
			new PrettyXmlSerializer(props).writeToFile(node, "/home/fabio/Develop/parseado.xml", "utf-8");
			new PrettyHtmlSerializer(props).writeToFile(node, "/home/fabio/Develop/htm_parseado.xml", "utf-8");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/			
	}
		
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	private void parseBlocks(ArrayList<String> blocks){
		ArrayList<BeanV1C> beans = new ArrayList<BeanV1C>(); 
		
		for (int i = 0; i < blocks.size(); i++) {  
			BeanV1C bean = new BeanV1C();
            String block = blocks.get(i);  
            
            String event = block.substring(block.indexOf("class=\"mlink\"><B>"), block.indexOf("</B></A></TD>",block.indexOf("class=\"mlink\"><B>")));
            bean.setPartyName(event);
            String atracao = block.substring(block.indexOf("<FONT class=\"topicoAgenda\">Atrações:"), block.indexOf("</B></A></TD>",block.indexOf("<FONT class=\"topicoAgenda\">Atrações:")));
            bean.setPartyDetail(atracao);
            String date = block.substring(block.indexOf("<TD><FONT class=\"topicoAgenda\"></FONT><FONT class=\"tpmedio\">"), block.indexOf("</FONT></TD>",block.indexOf("<TD><FONT class=\"topicoAgenda\"></FONT><FONT class=\"tpmedio\">")));
            bean.setPartyDate(date);
		} 
	}
}
