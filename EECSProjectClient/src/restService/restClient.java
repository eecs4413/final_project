package restService;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class restClient { 
	public static void main(String[] argv){
		//create a client
		Client client = ClientBuilder.newClient();
		//create a web target
		WebTarget wt=client.target("http://localhost:8080/EECSProject/rest/wsr/getOrders");
		//add the query parameter
		wt.queryParam("partNumber", "b001");	
		//create a request and invoke get; the argument of get is the type of return
		String xml = wt.request(MediaType.TEXT_XML)
		        .get(String.class);
		System.out.println("GET getOrders/?partNumber=b001 returns ");
		System.out.println(xml);
	 
		
		client.close();	
	}
}
