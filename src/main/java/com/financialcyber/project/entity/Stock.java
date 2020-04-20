package com.financialcyber.project.entity;

import javax.persistence.*;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="stock_id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="stockName")
    private String stockName;

    @Column(name="date")
    private String date;

    @Column(name="volume")
    private int volume;

    @Column(name="high")
    private double high;

    @Column(name="low")
    private double low;

    @Column(name="close")
    private double close;

    @Column(name="open")
    private double open;

    public Stock(String stockName, String date, int volume, double high, double low, double close, double open ){
        this.stockName = stockName;
        this.date = date;
        this.volume = volume;
        this.high = high;
        this.low = low;
        this.close = close;
        this.open = open;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(int close) {
        this.close = close;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }


}
