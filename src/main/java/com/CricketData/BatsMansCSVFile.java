package com.CricketData;

import com.opencsv.bean.CsvBindByName;

public class BatsMansCSVFile {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "4s")
    public int four;

    @CsvBindByName(column = "6s")
    public int six;

    @CsvBindByName(column = "Runs")
    public int runs;


    @CsvBindByName(column = "Avg")
    public double average;

    @CsvBindByName(column = "SR")
    public double strikeRate;

    public BatsMansCSVFile() {
    }

    @Override
    public String toString() {
        return "IPLRunsDataCsv{" +
                "player='" + player + '\'' +
                ", four=" + four +
                ", six=" + six +
                ", runs=" + runs +
                ", average=" + average +
                ", strikeRate=" + strikeRate +
                '}';
    }
}
