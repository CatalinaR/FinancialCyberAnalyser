package com.financialcyber.project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@SpringBootApplication
public class ProjectApplication {

	private static final Map<String, String> env = System.getenv();
	private static final String API_key =  env.get("API_ALPHA_VANTAGE");

	public static JSONObject getJsonTradingData(String stockName, String function) throws IOException, ParseException {
		String urlName_base = "https://www.alphavantage.co/query?function="+ function + "&symbol=";
		String urlName_complete = urlName_base + stockName + "&apikey=" + API_key;
		URL url = new URL(urlName_complete);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responsecode = conn.getResponseCode();
		JSONObject objectReturn;
		if(responsecode!=200){
			throw new RuntimeException("HTTP status " + responsecode);
		} else{
			//System.out.println(responsecode);
			//System.out.println(conn.getResponseMessage());

			String inline = "";
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNext())
			{
				inline+=sc.nextLine();
			}
			//System.out.println("\nJSON data in string format");
			//System.out.println(inline);
			sc.close();

			JSONParser parse = new JSONParser();
			Object jsonObj = parse.parse(inline);
			objectReturn = (JSONObject) jsonObj;
			//System.out.println(objectReturn.get("Meta Data"));
		}
		return objectReturn;
	}

	public static Map<String, String> processJsonMetadata(JSONObject objectInput){
		Map<String, String> metadata = new HashMap<>();
		System.out.println(objectInput.get("Meta Data"));
		String meta = objectInput.get("Meta Data").toString();
		String[] entries = meta.split(",");

		for(String entry : entries){
			if(entry.contains(":")){
				String[] subEntries = entry.split(":");
				if(subEntries[0].contains("Time Zone")){
					metadata.put("timezone", subEntries[1]);
				} else if(subEntries[0].contains("Symbol")){
					metadata.put("symbol", subEntries[1]);
				} else if(subEntries[0].contains("Last Refreshed"));
					metadata.put("lastRefreshDate", subEntries[1]);
			}
		}
		return metadata;
	}

	public static Map<String, Map<String, String>> processJsonStocks(JSONObject objectInput) throws ParseException {
		Map<String, Map<String, String>> stockValues = new HashMap<>();
		System.out.println(objectInput.get("Time Series (Daily)"));
		String stocks =objectInput.get("Time Series (Daily)").toString();
		JSONParser parse = new JSONParser();
		JSONObject jsonObj = (JSONObject) parse.parse(stocks);
		Set<Map.Entry<String, Object>> stocksEntries = jsonObj.entrySet();
		for(Map.Entry<String, Object> en : stocksEntries){
			String date = en.getKey();
			JSONParser parseValue = new JSONParser();
			JSONObject jsonObjValue = (JSONObject) parseValue.parse(en.getValue().toString());
			Set<Map.Entry<String, String>> entrValue = jsonObjValue.entrySet();
			Map<String, String> resultSubValue = new HashMap<>();
			for(Map.Entry<String, String> subEntry: entrValue){
				if(subEntry.getKey().contains("open")){
					resultSubValue.put("open", subEntry.getValue());
				} else if(subEntry.getKey().contains("low")){
					resultSubValue.put("low", subEntry.getValue());
				} else if(subEntry.getKey().contains("high")){
					resultSubValue.put("high", subEntry.getValue());
				} else if(subEntry.getKey().contains("close")){
					resultSubValue.put("close", subEntry.getValue());
				}
				else if(subEntry.getKey().contains("volume")){
					resultSubValue.put("volume", subEntry.getValue());
				}
			}
			stockValues.put(date, resultSubValue);
		}


		return stockValues;
	}


	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(ProjectApplication.class, args);
		JSONObject obj = getJsonTradingData("AAPL", "TIME_SERIES_DAILY");
		Map<String, String> processR = processJsonMetadata(obj);
		processR.forEach((a, b) -> System.out.println(a + " " + b));
		Map<String, Map<String, String>> result = processJsonStocks(obj);
		System.out.println(result.toString());
	}


}
