package com.comics.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main2 {

	public static void main(String[] args) {
		
		 try {

				URL url = new URL("https://bittrex.com/api/v2.0/pub/Markets/GetMarketSummaries?_=1513138154377");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				StringBuilder responseStrBuilder = new StringBuilder();
				while ((output = br.readLine()) != null) {
					responseStrBuilder.append(output);
				}

				conn.disconnect();
				System.out.println(responseStrBuilder);
				JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
				JSONArray myResponse = jsonObject.getJSONArray("result");
				System.out.println(myResponse.length());
				
				Iterator<Object> iterator = myResponse.iterator();
				ArrayList<JSONObject> list = new ArrayList<JSONObject>();
				
	            while (iterator.hasNext()) {
	            	JSONObject element = (JSONObject) iterator.next();
	            	System.out.println(element.getJSONObject("Market").getString("BaseCurrency"));
	            	if(element.getJSONObject("Market").getString("BaseCurrency").equals("USDT")){
	            		
	            		list.add(element);
//	            		System.out.println("@DASFAS:    " + element.getJSONObject("Market").getString("BaseCurrency"));
	            		
	            	}
	            }
	            System.out.println(list.size());

			  } catch (Exception e) {

				e.printStackTrace();
			  }

	}

}
