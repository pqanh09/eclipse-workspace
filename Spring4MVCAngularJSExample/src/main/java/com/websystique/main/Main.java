package com.websystique.main;


import java.io.*;
import java.net.*;
import javax.xml.xpath.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.apache.http.entity.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.xml.sax.*;

public class Main {

	public static void main(String[] args) {
//		String stubsApiBaseUri = "http://localhost:8080/Spring4MVCAngularJSExample/api/manga";
//		String stubsApiBaseUri = "http://localhost:8080/Spring4MVCAngularJSExample/api/manga";
		String stubsApiBaseUri = "http://services.groupkt.com/country/get/all";
		HttpClient client = HttpClients.createDefault();
		try {

			

			HttpGet startStubMethod = new HttpGet(stubsApiBaseUri);
			HttpResponse startStubResponse = client.execute(startStubMethod);
			int startStubStatusCode = startStubResponse.getStatusLine().getStatusCode();
			if (startStubStatusCode < 200 || startStubStatusCode >= 300) {
				// Handle non-2xx status code
				return;
			}
			// If you want to check the status of the stub that is starting, you
			// can use the response data to get the stub instance URI and poll
			// it
			// for updates
			System.out.println(startStubStatusCode);
			String startStubResponseBody = EntityUtils.toString(startStubResponse.getEntity());
			System.out.println(startStubResponseBody);

			// HttpEntity entity = httpResponse.getEntity();
			//
			// System.out.println("----------------------------------------");
			// System.out.println(httpResponse.getStatusLine());
			// Header[] headers = httpResponse.getAllHeaders();
			// for (int i = 0; i < headers.length; i++) {
			// System.out.println(headers[i]);
			// }
			// System.out.println("----------------------------------------");
			//
			// if (entity != null) {
			// System.out.println(EntityUtils.toString(entity));
			// }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			client.getConnectionManager().shutdown();
		}
	}

}
