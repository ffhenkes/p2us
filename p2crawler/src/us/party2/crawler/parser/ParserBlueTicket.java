

package us.party2.crawler.parser;

import java.util.ArrayList;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import us.party2.crawler.adapter.IParserAdapter;
import us.party2.crawler.bean.BeanBT;
import us.party2.crawler.bean.BeanV1C;
import us.party2.crawler.bean.IBean;

public class ParserBlueTicket implements IParserAdapter {

	private String response;
	
	@Override
	public ArrayList<IBean> parse() {
	
		ArrayList<String> blocks = new ArrayList();
		
		//extrai centro da pagina
		String kernel = response.substring(response.indexOf("<div class=\"lista_eventos\">", 0), response.lastIndexOf("<div class='outros_eventos'>"));
		//System.out.println(kernel);
		String lateral = response.substring(response.indexOf("<div class='evento_lateral_pequeno' style='padding-left:20px'>", 0), response.indexOf("<div id=\"footer_wrapper\">"));
		//System.out.println(lateral);
		
		//enqto tiver bloco de interesse, extrai bloco e insere no array de blocos		 		
		 while (kernel.indexOf("<div class='item_evento item_evento_1' style='height:97px'>")>=0){
			if(kernel.indexOf("<div class='item_evento item_evento_1' style='height:97px'>",
					"<div class='item_evento item_evento_1' style='height:97px'>".length()) == -1){
				String block = kernel.substring(
						kernel.indexOf("<div class='item_evento item_evento_1' style='height:97px'>"));
				
				blocks.add(block);
				kernel = kernel.substring("<div class='item_evento item_evento_1' style='height:97px'>".length());
			}
			else{
				String block = kernel.substring(
						kernel.indexOf("<div class='item_evento item_evento_1' style='height:97px'>",0),
						kernel.indexOf("<div class='item_evento item_evento_1' style='height:97px'>",
								"<div class='item_evento item_evento_1' style='height:97px'>".length()));
				
				blocks.add(block);
				kernel = kernel.substring(kernel.indexOf("<div class='item_evento item_evento_1' style='height:97px'>","<div class='item_evento item_evento_1' style='height:97px'>".length()));
			}
		}

		//parse evento lateral
		ArrayList<String> blocosLaterais = new ArrayList();
							
		while (lateral.indexOf("<div class='evento_lateral_pequeno' style='padding-left:20px'>")>=0){
			if(lateral.indexOf("<div class='evento_lateral_pequeno' style='padding-left:20px'>",
					"<div class='evento_lateral_pequeno' style='padding-left:20px'>".length()) == -1){
				String blocoLateral = lateral.substring(
						lateral.indexOf("<div class='evento_lateral_pequeno' style='padding-left:20px'>"));
				
				blocosLaterais.add(blocoLateral);
				lateral = lateral.substring("<div class='evento_lateral_pequeno' style='padding-left:20px'>".length());
			}
			else{
				String blocoLateral = lateral.substring(
						lateral.indexOf("<div class='evento_lateral_pequeno' style='padding-left:20px'>",0),
						lateral.indexOf("<div class='evento_lateral_pequeno' style='padding-left:20px'>",
								"<div class='evento_lateral_pequeno' style='padding-left:20px'>".length()));
				
				blocosLaterais.add(blocoLateral);
				lateral = lateral.substring(lateral.indexOf("<div class='evento_lateral_pequeno' style='padding-left:20px'>","<div class='evento_lateral_pequeno' style='padding-left:20px'>".length()));
			}
		}
		
		parseLateralBlocks(blocosLaterais);		
		return parseBlocks(blocks);
				
	}
		
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	private ArrayList<IBean> parseLateralBlocks(ArrayList<String> blocks){
		ArrayList<IBean> beans = new ArrayList<IBean>(); 
		for (int i = 0; i < blocks.size(); i++) {  
			BeanBT bean = new BeanBT();
            String block = blocks.get(i);
            
            String eventId = block.substring(block.indexOf("?secao=Eventos&evento=")+"?secao=Eventos&evento=".length(),block.indexOf("' style",block.indexOf("?secao=Eventos&evento="))); 
            bean.setBtId(eventId);
            String eventImg = block.substring(block.indexOf("src=")+"src=".length(),block.indexOf("alt",block.indexOf("src=")));
            bean.setPartyImage(eventImg);
            String eventTitle = block.substring(block.indexOf("titulo_evento_lateral'>")+"titulo_evento_lateral'>".length(),block.indexOf("</p>"));
            bean.setPartyName(eventTitle);            
            String eventPlace = block.substring(block.indexOf("local_evento_lateral'>")+"local_evento_lateral'>".length(),block.indexOf("</p>",block.indexOf("local_evento_lateral'>")));
            bean.setPartyPlace(eventPlace);
            String subBlock = block.substring(block.indexOf("local_evento_lateral'>")+"local_evento_lateral'>".length());            
            
            String eventDate = subBlock.substring(subBlock.indexOf("local_evento_lateral'>")+"local_evento_lateral'>".length(),subBlock.indexOf("</p>",subBlock.indexOf("local_evento_lateral'>")));
            bean.setPartyDate(eventDate);                       
            beans.add(bean);

		}
		
		return beans;
	}
	
	private ArrayList<IBean> parseBlocks(ArrayList<String> blocks){
		ArrayList<IBean> beans = new ArrayList<IBean>(); 
		for (int i = 0; i < blocks.size(); i++) {  
			BeanBT bean = new BeanBT();
            String block = blocks.get(i);  
            
            String eventId = block.substring(block.indexOf("?secao=Eventos&evento=")+"?secao=Eventos&evento=".length(),block.indexOf("'>",block.indexOf("?secao=Eventos&evento="))); 
            bean.setBtId(eventId);
            String eventImg = block.substring(block.indexOf("src=")+"src=".length(),block.indexOf("alt",block.indexOf("src=")));
            bean.setPartyImage(eventImg);            
            String eventTitle = block.substring(block.indexOf("titulo_evento_lista'>")+"titulo_evento_lista'>".length(),block.indexOf("</span>"));
            bean.setPartyName(eventTitle);
            String eventPlace = block.substring(block.indexOf("desc_evento_lista'><strong>")+"desc_evento_lista'><strong>".length(),block.indexOf("</strong>",block.indexOf("desc_evento_lista'>")));
            bean.setPartyPlace(eventPlace);
            String eventDate = block.substring(block.indexOf("data_evento_lista'>")+"data_evento_lista'>".length(),block.indexOf("</span>",block.indexOf("data_evento_lista'>")));            
            bean.setPartyDate(eventDate);                       
            beans.add(bean);
		}
		return beans;
	}
}
