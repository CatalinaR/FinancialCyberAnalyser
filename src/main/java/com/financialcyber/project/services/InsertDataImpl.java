package com.financialcyber.project.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.financialcyber.project.services.CaptureDataImpl.getURLData;

public class InsertDataImpl {

    private static final Logger logger = LoggerFactory.getLogger(InsertDataImpl.class);

    private InsertDataImpl(){

    }


    public static StringBuilder constructSQLStock() throws IOException, ParseException {
        String defaultSQL = "INSERT INTO article (stockName, volume, high, low, close, open, date) VALUES (";
        StringBuilder sqlStatements = new StringBuilder();
        JSONObject obj = getURLData(CaptureDataImpl.constructURLStocks("TIME_SERIES_DAILY", "AAPL"));
        Map<String, Map<String, String>> resultCaptureStock = CaptureDataImpl.processJsonStocks(obj);
        for(Map.Entry<String, Map<String, String>> entry: resultCaptureStock.entrySet()) {
            sqlStatements.append(defaultSQL);
            sqlStatements.append("'" +  "AAPL" + "'" );
            sqlStatements.append(",");
            sqlStatements.append(entry.getValue().get("volume"));
            sqlStatements.append(",");
            sqlStatements.append(entry.getValue().get("high"));
            sqlStatements.append(",");
            sqlStatements.append(entry.getValue().get("low"));
            sqlStatements.append(",");
            sqlStatements.append(entry.getValue().get("close"));
            sqlStatements.append(",");
            sqlStatements.append(entry.getValue().get("open"));
            sqlStatements.append(",");
            sqlStatements.append("'" + entry.getKey() + "'");
            sqlStatements.append(");\n");
        }
        return sqlStatements;
    }

    public static StringBuilder constructSQLArticle() throws IOException, ParseException {
        String defaultSQL = "INSERT INTO article (title, web_URL, web_id, article_date) VALUES (";
        StringBuilder sqlStatements = new StringBuilder();
        String webTitle = "webTitle";
        JSONObject obj2 = getURLData(CaptureDataImpl.constructURLNews("1"));
        Map<Integer, Map<String, String>> resultsCapture = CaptureDataImpl.processNewsMetaData(obj2);
        for(Map.Entry<Integer, Map<String, String>> entry: resultsCapture.entrySet()){
            Map<String, String> valuesArticle = entry.getValue();
            if(valuesArticle.get(webTitle).contains("'")){
                valuesArticle.put(webTitle, valuesArticle.get(webTitle).replace("'", " "));
            }
            sqlStatements.append(defaultSQL);
            sqlStatements.append("'" + valuesArticle.get(webTitle)+ "'" );
            sqlStatements.append(",");
            sqlStatements.append("'"  + valuesArticle.get("webUrl") + "'");
            sqlStatements.append(",");
            sqlStatements.append("'" + valuesArticle.get("id") + "'");
            sqlStatements.append(",");
            sqlStatements.append("'" +  valuesArticle.get("webPublicationDate") + "'");
            sqlStatements.append(");\n");
        }
        return sqlStatements;
    }



    public static void printToFile(StringBuilder stringBuilder, String filename) throws IOException {
        FileOutputStream fos = null;
        DataOutputStream outStream = null;

        try{
             fos = new FileOutputStream(filename);
            outStream = new DataOutputStream(new BufferedOutputStream(fos));
            outStream.writeUTF(String.valueOf(stringBuilder));

        } catch(Exception e){
            logger.info("Exception writing to file!");
        } finally{
           Objects.requireNonNull(outStream).close();
        }

    }



}
