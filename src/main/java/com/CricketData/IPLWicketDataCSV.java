package com.CricketData;

import com.opencsv.bean.CsvBindByName;

public class IPLWicketDataCSV {
        @CsvBindByName(column = "PLAYER")
        public String player;

        @CsvBindByName(column = "Mat")
        public int match;

        @CsvBindByName(column = "Inns")
        public int innings;

        @CsvBindByName(column = "Over")
        public double over;

        @CsvBindByName(column = "Runs")
        public int runs;

        @CsvBindByName(column = "Wkts")
        public int wickets;

        @CsvBindByName(column = "BestBowling")
        public double bestBowlingIn;

        @CsvBindByName(column = "Avg")
        public double average;

        @CsvBindByName(column = "Econ")
        public double economyRate;

        @CsvBindByName(column = "SR")
        public double strikeRate;

        @CsvBindByName(column = "4w")
        public int fourWickets;

        @CsvBindByName(column = "5w")
        public int fiveWickets;

        public IPLWicketDataCSV(String player,int runs, int wickets,
                                double bestBowlingIn, double average, double economyRate, double strikeRate,
                                int fourWickets, int fiveWickets) {
        }

        @Override
        public String toString() {
            return "IPLWicketSheetCSV{" +
                    "player='" + player + '\'' +
                    ", match=" + match +
                    ", innings=" + innings +
                    ", over=" + over +
                    ", runs=" + runs +
                    ", wickets=" + wickets +
                    ", bestBowlingIn=" + bestBowlingIn +
                    ", average=" + average +
                    ", economyRate=" + economyRate +
                    ", strikeRate=" + strikeRate +
                    ", fourWickets=" + fourWickets +
                    ", fiveWickets=" + fiveWickets +
                    '}';
        }
    }

