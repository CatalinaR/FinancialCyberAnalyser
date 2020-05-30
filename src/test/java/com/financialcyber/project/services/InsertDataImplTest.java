package com.financialcyber.project.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.financialcyber.project.services.CaptureDataImpl.getURLData;
import static com.financialcyber.project.services.InsertDataImpl.constructSQLArticle;
import static com.financialcyber.project.services.InsertDataImpl.constructSQLStock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({InsertDataImpl.class, CaptureDataImpl.class})
public class InsertDataImplTest {

    @Before
    public void setUp() throws IOException, ParseException {
        MockitoAnnotations.initMocks(this);

        mockStatic(System.class);
       PowerMockito.mockStatic(CaptureDataImpl.class);

        Map<String, String> envTest = new HashMap<>();
        JSONObject jsonObjectResult = new JSONObject();

        Map<String, Map<String, String>> stockValuesExpectedResults = new HashMap<>();
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("open", "319.2500");
        expectedResult.put("high", "321.1500");
        expectedResult.put("low", "316.4700");
        expectedResult.put("close", "317.9400");
        expectedResult.put("volume", "38257485");
        stockValuesExpectedResults.put("2020-05-29", expectedResult);

        envTest.put("API_ALPHA_VANTAGE", "api_alpha_key");
        envTest.put("API_GUARDIAN", "api_guardian_key");
        when( System.getenv()).thenReturn(envTest);

       when( CaptureDataImpl.constructURLStocks("TIME_SERIES_DAILY", "AAPL")).thenReturn("");
       when(getURLData(CaptureDataImpl.constructURLStocks("TIME_SERIES_DAILY", "AAPL"))).thenReturn(jsonObjectResult);
       when(CaptureDataImpl.processJsonStocks(jsonObjectResult)).thenReturn(stockValuesExpectedResults);

       when(CaptureDataImpl.constructURLNews("1")).thenReturn("");
        when(getURLData(CaptureDataImpl.constructURLNews("1"))).thenReturn(jsonObjectResult);
        Map<Integer, Map<String, String>> resultsCapture = new HashMap<>();
        Map<String, String> values = new HashMap<>();
        values.put("webTitle", "title") ;
        values.put("webUrl", "web");
        values.put("id", "ID");
        values.put("webPublicationDate", "date");
        resultsCapture.put(1, values);

        when(CaptureDataImpl.processNewsMetaData(jsonObjectResult)).thenReturn(resultsCapture);
    }


    @Test
    public void constructSQLStockTest() throws IOException, ParseException {
        String expectedResult = "INSERT INTO article (stockName, volume, high, low, close, open, date) VALUES ('AAPL',38257485,321.1500,316.4700,317.9400,319.2500,'2020-05-29');\n";
        assertEquals(expectedResult, constructSQLStock().toString());
    }

    @Test
    public void constructSQLArticleTest() throws IOException, ParseException {
        String expectedResult = "INSERT INTO article (title, web_URL, web_id, article_date) VALUES ('title','web','ID','date');\n";
        assertEquals(expectedResult, constructSQLArticle().toString());
    }
}