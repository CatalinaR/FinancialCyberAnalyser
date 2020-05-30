package com.financialcyber.project.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.financialcyber.project.services.CaptureDataImpl.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CaptureDataImpl.class)
public class CaptureDataImplTest {

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockStatic(System.class);

        Map<String, String> envTest = new HashMap<>();
        envTest.put("API_ALPHA_VANTAGE", "api_alpha_key");
        envTest.put("API_GUARDIAN", "api_guardian_key");
        when( System.getenv()).thenReturn(envTest);
    }


    @Test
    public void constructURLNewsTest() {
        String result = "http://content.guardianapis.com/search?q=data-computer-security&section=technology&page=" + "30" + "&api-key=" + "api_guardian_key";
        assertEquals(result, constructURLNews("30"));
    }

    @Test
    public void constructURLStocksTest() {
        String result = "https://www.alphavantage.co/query?function=FUNCTION&symbol=STOCKNAME&apikey=api_alpha_key";
        assertEquals(result, constructURLStocks("FUNCTION", "STOCKNAME"));


    }


    @Test
    public void processJsonMetadataTest() {
        JSONObject jsonObjectResult = new JSONObject();
        String format = "Time Zone:timeZone, Symbol:symbol, Last Refreshed:lastRefreshed";
        jsonObjectResult.put("Meta Data", format);
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("timezone", "timeZone");
        expectedResult.put("symbol", "symbol");
        expectedResult.put("lastRefreshDate", "lastRefreshed");
        assertEquals(expectedResult, processJsonMetadata(jsonObjectResult));


    }

    @Test
    public void processJsonStocksTest() throws ParseException {
        JSONObject jsonObjectResult = new JSONObject();
        String format = "{\n" +
                "        \"2020-05-29\": {\n" +
                "            \"1. open\": \"319.2500\",\n" +
                "            \"2. high\": \"321.1500\",\n" +
                "            \"3. low\": \"316.4700\",\n" +
                "            \"4. close\": \"317.9400\",\n" +
                "            \"5. volume\": \"38257485\"\n" +
                "        }}";
        jsonObjectResult.put("Time Series (Daily)", format);
        Map<String, Map<String, String>> stockValuesExpectedResults = new HashMap<>();
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("open", "319.2500");
        expectedResult.put("high", "321.1500");
        expectedResult.put("low", "316.4700");
        expectedResult.put("close", "317.9400");
        expectedResult.put("volume", "38257485");
        stockValuesExpectedResults.put("2020-05-29", expectedResult);
        assertEquals(stockValuesExpectedResults, processJsonStocks(jsonObjectResult));
    }

    @Test
    public void processNewsMetaDataTest() throws ParseException {
        JSONObject jsonObjectResult = new JSONObject();
        String format = "{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":6221,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":623,\"orderBy\":\"relevance\", \"results\":[{\"id\":\"ID\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2020-05-24T18:32:10Z\",\"webTitle\":\"title\",\"webUrl\":\"web\",\"apiUrl\":\"api\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}";
        jsonObjectResult.put("response", format);
        Map<Integer, Map<String, String>> newsExpectedResults = new HashMap<>();
        Map<String, String> expectedResult = new HashMap<>();
        expectedResult.put("webPublicationDate", "2020-05-24T18:32:10Z");
        expectedResult.put("webUrl", "web");
        expectedResult.put("webTitle", "title");
        expectedResult.put("id", "ID");
        newsExpectedResults.put(1, expectedResult);
        assertEquals(newsExpectedResults, processNewsMetaData(jsonObjectResult));
    }
}