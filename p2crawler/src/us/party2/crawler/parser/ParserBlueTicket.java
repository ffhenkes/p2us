

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
				lateral = kernel.substring("<div class='item_evento item_evento_1' style='height:97px'>".length());
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
		}
		
		return beans;
	}
	
	private ArrayList<IBean> parseBlocks(ArrayList<String> blocks){
		ArrayList<IBean> beans = new ArrayList<IBean>(); 
		
		for (int i = 0; i < blocks.size(); i++) {  
			BeanV1C bean = new BeanV1C();
            String block = blocks.get(i);  
            
            String event = block.substring(block.indexOf("class=\"mlink\"><B>")+"class=\"mlink\"><B>".length(), block.indexOf("</B></A></TD>", block.indexOf("class=\"mlink\"><B>")));
            bean.setPartyName(event);
                                    
            String ini_block_place = block.substring(block.indexOf("<B>Local:"));
            String place = ini_block_place.substring("<B>Local:".length(), ini_block_place.indexOf("</FONT>", ini_block_place.indexOf("<B>Local:")));
            
            String ini_block_atracao = block.substring(block.indexOf("<FONT class=\"topicoAgenda\">Atrações:"));
            
            //String atracao = ini_block_atracao.substring(ini_block_atracao.indexOf("<FONT class=\"topicoAgenda\">Atrações:".length()));
            bean.setPartyDetail(ini_block_atracao);           

            //imagem
            String ini_block_img = block.substring(block.indexOf("src=\"../img/parceiros"));
            String imgURL = ini_block_img.substring(ini_block_img.indexOf("src=")+"src=".length(),ini_block_img.indexOf(".gif")+".gif".length());
            
            //sempre dar um sub para o inicio do bloco de interesse
            String ini_block_date = block.substring(block.indexOf("<TD><FONT class=\"topicoAgenda\"></FONT><FONT class=\"tpmedio\">"));            
            String date = ini_block_date.substring(
            		ini_block_date.indexOf("<TD><FONT class=\"topicoAgenda\"></FONT><FONT class=\"tpmedio\">")+"<TD><FONT class=\"topicoAgenda\"></FONT><FONT class=\"tpmedio\">".length(), 
            		ini_block_date.indexOf("</FONT></TD>",ini_block_date.indexOf("<TD><FONT class=\"topicoAgenda\"></FONT><FONT class=\"tpmedio\">")));
            
            bean.setPartyDate(date);
            
            beans.add(bean);
		}
		return beans;
	}
}
