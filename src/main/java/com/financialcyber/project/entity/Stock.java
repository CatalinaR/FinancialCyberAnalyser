package com.financialcyber.project.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="stock_id")
    private Integer id;

    @Column(name="stockName")
    private String stockName;

    @Column(name="date")
    private LocalDate date;

    @Column(name="volume")
    private int volume;

    @Column(name="high")
    private  double high;

    @Column(name="low")
    private  double low;

    @Column(name="close")
    private  double close;

    @Column(name="open")
    private double open;

    public Stock(){

    }

    public Stock(String stockName, LocalDate date, int volume, double high, double low, double close, double open ){
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

    public LocalDate getDate() {
        return date;
    }

    public int getVolume() {
        return volume;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getClose() {
        return close;
    }

    public double getOpen() {
        return open;
    }


    public Integer getId() {
        return id;
    }


}
