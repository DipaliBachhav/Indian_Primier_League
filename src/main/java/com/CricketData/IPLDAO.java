package com.CricketData;

public class IPLDAO {
    public String player;
    public int four;
    public int six;
    public int runs;
    public double average;
    public double strikeRate;

    public int match;
    public double over;
    public int innings;
    public int wickets;
    public double bestBowlingIn;
    public double economyRate;
    public int fourWickets;
    public int fiveWickets;


    public IPLDAO(BatsMansCSVFile batsMansCSVFileCsv) {
        this.player = batsMansCSVFileCsv.player;
        this.four = batsMansCSVFileCsv.four;
        this.six = batsMansCSVFileCsv.six;
        this.runs = batsMansCSVFileCsv.runs;
        this.average = batsMansCSVFileCsv.average;
        this.strikeRate = batsMansCSVFileCsv.strikeRate;
    }
    public IPLDAO(IPLWicketDataCSV iplWicketDataCSV) {
        this.player = iplWicketDataCSV.player;
        this.match = iplWicketDataCSV.match;
        this.innings = iplWicketDataCSV.innings;
        this.over = iplWicketDataCSV.over;
        this.runs = iplWicketDataCSV.runs;
        this.wickets = iplWicketDataCSV.wickets;
        this.bestBowlingIn = iplWicketDataCSV.bestBowlingIn;
        this.average = iplWicketDataCSV.average;
        this.economyRate = iplWicketDataCSV.economyRate;
        this.strikeRate = iplWicketDataCSV.strikeRate;
        this.fourWickets = iplWicketDataCSV.fourWickets;
        this.fiveWickets = iplWicketDataCSV.fiveWickets;
    }
}
