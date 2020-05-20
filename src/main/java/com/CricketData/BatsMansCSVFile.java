package com.CricketData;

import com.opencsv.bean.CsvBindByName;

public class BatsMansCSVFile {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "4s", required = true)
    public int four;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
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
