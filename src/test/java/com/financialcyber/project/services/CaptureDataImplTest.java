package com.financialcyber.project.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static com.financialcyber.project.services.CaptureDataImpl.constructURLNews;
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
    public void constructURLStocks() {
    }

    @Test
    public void getURLData() {
    }

    @Test
    public void processJsonMetadata() {
    }

    @Test
    public void processJsonStocks() {
    }

    @Test
    public void processNewsMetaData() {
    }
}