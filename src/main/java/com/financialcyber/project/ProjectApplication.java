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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class ProjectApplication {

	private static final Map<String, String> env = System.getenv();
	private static final String API_key =  env.get("API_ALPHA_VANTAGE");

	public static List<String> getTradingData(String stockName) throws IOException, ParseException {
		String urlName_base = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=";
		String urlName_complete = urlName_base + stockName + "&apikey=" + API_key;
		URL url = new URL(urlName_complete);
		List<String> result = new ArrayList<>();
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responsecode = conn.getResponseCode();
		if(responsecode!=200){
			throw new RuntimeException("HTTP status " + responsecode);
		} else{
			//JSONParser json = new JSONParser();
			System.out.println(responsecode);
			System.out.println(conn.getResponseMessage());

			String inline = "";
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNext())
			{
				inline+=sc.nextLine();
			}
			System.out.println("\nJSON data in string format");
			System.out.println(inline);
			sc.close();

			JSONParser parse = new JSONParser();
			Object jsonObj = parse.parse(inline);
			JSONObject obj = (JSONObject) jsonObj;
			System.out.println(obj.get("Meta Data"));
		}


		return result;
	}


	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(ProjectApplication.class, args);
		List<String> result = getTradingData("AAPL");

	}


}
