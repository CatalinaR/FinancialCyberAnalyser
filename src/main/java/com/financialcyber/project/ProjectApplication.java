package com.financialcyber.project;


import com.financialcyber.project.services.CaptureDataImpl;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


import static com.financialcyber.project.services.CaptureDataImpl.*;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) throws IOException, ParseException {
		SpringApplication.run(ProjectApplication.class, args);
		JSONObject obj = getURLData(CaptureDataImpl.constructURLStocks("TIME_SERIES_DAILY", "AAPL"));
		CaptureDataImpl.processJsonMetadata(obj);

		JSONObject obj2 = CaptureDataImpl.getURLData(CaptureDataImpl.constructURLNews("1"));
		CaptureDataImpl.processNewsMetaData(obj2);
	}

}
