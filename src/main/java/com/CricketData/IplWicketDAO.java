package com.CricketData;

public class IplWicketDAO {
    public String player;
    public int match;
    public int innings;
    public double over;
    public int runs;
    public int wickets;
    public double bestBowlingIn;
    public double average;
    public double economyRate;
    public double strikeRate;
    public int fourWickets;
    public int fiveWickets;

    public IplWicketDAO() {
    }

    public IplWicketDAO(IPLWicketDataCSV iplWicketDataCSV) {
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

