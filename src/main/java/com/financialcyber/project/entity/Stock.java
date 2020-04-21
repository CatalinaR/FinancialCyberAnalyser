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
    private final String stockName;

    @Column(name="date")
    private final LocalDate date;

    @Column(name="volume")
    private final int volume;

    @Column(name="high")
    private final double high;

    @Column(name="low")
    private final double low;

    @Column(name="close")
    private final double close;

    @Column(name="open")
    private final double open;

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
