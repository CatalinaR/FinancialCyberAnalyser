package com.financialcyber.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

public class InsertDataImpl {

    public static StringBuffer constructSQLStock(){
        return new StringBuffer();
    }



    public static void printToFile() throws IOException {
        StringBuffer bufferString = new StringBuffer();
        bufferString.append("INSERT; INSERT");
        FileOutputStream fos = new FileOutputStream("C:/Users/catal/Desktop/FinancialCyberProject/FinancialCyberAnalyser/src/main/resources/DML.sql");
        DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
        outStream.writeUTF(String.valueOf(bufferString));
        outStream.close();

    }



}
