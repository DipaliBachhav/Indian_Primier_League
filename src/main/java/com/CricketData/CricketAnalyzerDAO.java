package com.CricketData;

public class CricketAnalyzerDAO {

    public String player;
    public int four;
    public int six;
    public int runs;
    public double average;
    public double strikeRate;

    public CricketAnalyzerDAO(BatsMansCSVFile batsMansCSVFileCsv) {
        this.player = batsMansCSVFileCsv.player;
        this.four = batsMansCSVFileCsv.four;
        this.six = batsMansCSVFileCsv.six;
        this.runs = batsMansCSVFileCsv.runs;
        this.average = batsMansCSVFileCsv.average;
        this.strikeRate = batsMansCSVFileCsv.strikeRate;
    }
}