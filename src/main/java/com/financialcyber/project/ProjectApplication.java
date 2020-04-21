package com.financialcyber.project;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@SpringBootApplication
public class ProjectApplication {

	private static final Map<String, String> env = System.getenv();
	private static final String API_ALPHA =  env.get("API_ALPHA_VANTAGE");
	private static final String API_GUARDIAN =  env.get("API_GUARDIAN");


	public static String constructURLNews(String pageNumber){
		String urlName = "http://content.guardianapis.com/search?q=data-computer-security&section=technology&page="+pageNumber;
		return urlName + "&api-key=" + API_GUARDIAN;
	}

	public static String constructURLStocks(String function, String stockName){
		String urlName = "https://www.alphavantage.co/query?function="+ function + "&symbol=";
		return  urlName + stockName + "&apikey=" + API_ALPHA;
	}


	public static JSONObject getURLData(String urlName) throws IOException, ParseException {
		URL url = new URL(urlName);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responsecode = conn.getResponseCode();
		JSONObject objectReturn;
		if(responsecode!=200){
			throw new NullPointerException();
		} else{
			String inline = "";
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNext())
			{
				inline+=sc.nextLine();
			}

			sc.close();

			JSONParser parse = new JSONParser();
			Object jsonObj = parse.parse(inline);
			objectReturn = (JSONObject) jsonObj;
		}
		return objectReturn;
	}

	public static Map<String, String> processJsonMetadata(JSONObject objectInput){
		Map<String, String> metadata = new HashMap<>();
		String meta = objectInput.get("Meta Data").toString();
		String[] entries = meta.split(",");

		for(String entry : entries){
			if(entry.contains(":")){
				String[] subEntries = entry.split(":");
				if(subEntries[0].contains("Time Zone")){
					metadata.put("timezone", subEntries[1]);
				} else if(subEntries[0].contains("Symbol")){
					metadata.put("symbol", subEntries[1]);
				} else if(subEntries[0].contains("Last Refreshed")) {
					int len = subEntries[1].length();
					if (subEntries[1].charAt(len - 1) == '}') {
						metadata.put("lastRefreshDate", subEntries[1].substring(0, len - 1));
					} else {
						metadata.put("lastRefreshDate", subEntries[1]);
					}
				}
			}
		}
		return metadata;
	}


	public static Map<String, Map<String, String>> processJsonStocks(JSONObject objectInput) throws ParseException {
		Map<String, Map<String, String>> stockValues = new HashMap<>();
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


	public static Map<Integer, Map<String, String>> processNewsMetaData(JSONObject obj) throws ParseException {
		Map<Integer, Map<String, String>> result = new HashMap<>();
		String response = obj.get("response").toString();
		JSONParser parse = new JSONParser();
		JSONObject jsonObj = (JSONObject) parse.parse(response);
		String resutsList = jsonObj.get("results").toString();
		JSONArray resultListObj = (JSONArray) parse.parse(resutsList);
		for(int i = 0; i<resultListObj.size(); i++){
			Map<String, String> setResultJson = new HashMap<>();
			JSONObject elementObj = (JSONObject) parse.parse(resultListObj.get(i).toString());
			setResultJson.put("webPublicationDate", elementObj.get("webPublicationDate").toString());
			setResultJson.put("webUrl", elementObj.get("webUrl").toString());
			setResultJson.put("webTitle", elementObj.get("webTitle").toString());
			setResultJson.put("id", elementObj.get("id").toString());
			result.put(i+1, setResultJson);
		}

		return result;
	}

	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(ProjectApplication.class, args);
		JSONObject obj = getURLData(constructURLStocks("TIME_SERIES_DAILY", "AAPL"));
		processJsonMetadata(obj);

		JSONObject obj2 = getURLData(constructURLNews("1"));
		 processNewsMetaData(obj2);

	}

}
