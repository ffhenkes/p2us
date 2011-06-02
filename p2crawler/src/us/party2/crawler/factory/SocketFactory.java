package us.party2.crawler.factory;

import java.io.PrintWriter;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class SocketFactory {

	//proof of concept
	public String getData(){
				
		String targetURL = "http://www.vale1convite.com.br/site/agenda/agenda.php?pegadata=4/6/2011&dia=01&mes=06&ano=2011&idcidade=1"; 
		String paramname = "content";
		
		PostMethod post = new PostMethod(targetURL);
		
		HttpClient httpclient = new HttpClient();
		PrintWriter myout = null;
		String postResp = "";
		
		// Execute http request
		try 
		{
			long t1 = System.currentTimeMillis();
			int result = httpclient.executeMethod(post);
			System.out.println("HTTP Response status code: " + result);
			System.out.println(">> Time taken " + (System.currentTimeMillis() - t1));

			//------------Get response as a string ----------
			postResp = post.getResponseBodyAsString();
			//System.out.println("Response=======>"+postResp.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		finally 
		{
			//myout.close();
			post.releaseConnection();
		}
		
		System.out.println("hacked");		
		
		return postResp;

	}
}
