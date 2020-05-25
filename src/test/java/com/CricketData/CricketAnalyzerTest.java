package com.CricketData;

import com.OpenCsv.CSVBuilderException;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Type;

public class CricketAnalyzerTest {
    private static final String BATSMAN_DATA = "src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String BATSMAN_DATA_INCORRECT_FILE = "src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String BATSMAN_DATA_WITH_WRONG_DELIMETER = "src/test/resources/IPL2019FactsheetMostRuns,csv";
    private static final String BATSMAN_DATA_EMPTY_FILE = "src/test/resources/IPL2019FactsheetMostRunsEmpty.csv";
    private static final String WICKETS_DATA = "src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String WICKETS_DATA_WRONG_FILE_NAME = "src/test/resources/IPL2019FactsheetWickets.csv";
    private static final String WICKETS_DATA_WRONG_FILE_TYPE = "src/test/resources/IPL2019FactsheetWickets.txt";
    private static final String WICKETS_DATA_WRONG_FILE_DELIMETER = "src/test/resources/IPL2019FactsheetWickets,csv";


    @Test
    public void givenIPLBatsmanData_shouldReturnTotalNumberOfPlayers() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer(CricketAnalyzer.IPLSheet.RUNS);
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA);
            Assert.assertEquals(100, numOfRecords);
        } catch (CricketAnalyzerException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLBatsmanData_shouldReturnsIncorrectFile() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer();
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA_INCORRECT_FILE);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException | CSVBuilderException e) {

        }
    }

    @Test
    public void givenIPLBatsmanData_shouldReturnEmptyCSVFile() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer();
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA_EMPTY_FILE);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException | CSVBuilderException e) {
        }
    }

    @Test
    public void givenIPLBatsmanData_shouldReturnWrongFileDelimeterCSVFile() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer();
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA_WITH_WRONG_DELIMETER);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOnAverage_shouldReturnSortedResult() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA);
            String sortedData = cricketAnalyzer.getSortData(SortData.IPLData.BATSMAN_AVERAGE);
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(sortedData, BatsMansCSVFile[].class);
            Assert.assertEquals("MS Dhoni", iplRunCSV[iplRunCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

  @Test
    public void givenCricketLeagueData_whenSorted_shouldReturnBestStrikeRate(){
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA);
            String strikeRate = cricketAnalyzer.getSortData(SortData.IPLData.STRIKE_RATE);
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(strikeRate, BatsMansCSVFile[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[iplRunCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

   @Test
    public void givenCricketLeagueData_whenSorted_shouldReturnFours(){
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA);
            String foursInMatch = cricketAnalyzer.getSortData(SortData.IPLData.FOURS);
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(foursInMatch, BatsMansCSVFile[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunCSV[iplRunCSV.length-1].player);
        } catch (CricketAnalyzerException e) {

        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

   @Test
    public void givenCricketLeagueData_whenSorted_shouldReturnSix(){
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA);
            String sixesInMatch = cricketAnalyzer.getSortData(SortData.IPLData.SIX);
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(sixesInMatch, BatsMansCSVFile[].class);
            Assert.assertEquals("Andre Russell", iplRunCSV[iplRunCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

   @Test
    public void givenCricketLeagueData_whenSortedStrikeRateOfFoursAndSix_shouldReturnFourAndSix(){
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA);
            String sortedStrikeRateOfFoursAndSix = cricketAnalyzer.getSortData(SortData.IPLData.STRIKE_RATE_AVERAGE_SIX_FOURS);
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(sortedStrikeRateOfFoursAndSix, BatsMansCSVFile[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[iplRunCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GivenCricketLeagueData_whenSorted_ShouldReturnAverageWithBestStrikeRate(){
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA);
            String  battingAverage= cricketAnalyzer.getSortData(SortData.IPLData.AVERAGE_BEST_STRIKE_RATE);
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(battingAverage, BatsMansCSVFile[].class);
            Assert.assertEquals("MS Dhoni", iplRunCSV[iplRunCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenCricketLeagueData_whenSorted_shouldReturnSortedRunWithBestAverage(){
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.RUNS,BATSMAN_DATA);
            String runsWithBestAverage = cricketAnalyzer.getSortData(SortData.IPLData.MAX_RUN_WITH_BEST_AVERAGE);
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(runsWithBestAverage, BatsMansCSVFile[].class);
            Assert.assertEquals("David Warner ", iplRunCSV[iplRunCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLWicketsData_shouldReturnTotalNumberOfPlayers() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            int numOfRecords = cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLWicketsData_shouldReturnWrongFileName() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            int numOfRecords = cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA_WRONG_FILE_NAME);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLWicketsData_shouldReturnWrongFileType() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            int numOfRecords = cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA_WRONG_FILE_TYPE);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLWicketsData_shouldReturnWrongFileDelimeter() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            int numOfRecords = cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA_WRONG_FILE_DELIMETER);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

   @Test
    public void givenIplWicketsCSVFile_ShouldReturnPlayerName_WhoHasTopBowlingAverage() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            String sortBowlingAverage = cricketAnalyzer.getSortData(SortData.IPLData.WICKET_BOWLING_AVERAGE);
            IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(sortBowlingAverage, IPLWicketDataCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

   @Test
    public void givenIplWicketsCSVFile_ShouldReturnPlayerName_WhoHasTopBowlingStrikeRate() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            String sortBowlingStrikeRate = cricketAnalyzer.getSortData(SortData.IPLData.WICKET_STRIKE_RATE);
            IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(sortBowlingStrikeRate, IPLWicketDataCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_ShouldReturnBowlerName_WhoHasBestEconomyRate()  {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            String sortEconomyRate = cricketAnalyzer.getSortData(SortData.IPLData.ECONOMY_RATE);
            IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(sortEconomyRate, IPLWicketDataCSV[].class);
            Assert.assertEquals("Ben Cutting", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

  @Test
    public void givenIplMostWicketsCSVFile_ShouldReturnBowlerName_HasBestStrikingRateWithFourAndFiveWickets()  {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            String strikeRateWiseSortingWith5wAnd4wOnData = cricketAnalyzer.getSortData(SortData.IPLData.FIVE_FOUR_WICKETS_STRIKE_RATE);
            IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(strikeRateWiseSortingWith5wAnd4wOnData, IPLWicketDataCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

  @Test
    public void givenIplMostWicketsCSVFile_ShouldReturnBowlerName_HasAverageWithBestStrikeRate(){
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            String sortBestStrikeRateAverage = cricketAnalyzer.getSortData(SortData.IPLData.WICKET_BEST_BOWLING_AVERAGE_STRIKE_RATE);
            IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(sortBestStrikeRateAverage, IPLWicketDataCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_whenSortedOnWicketsWithAvg_shouldReturnSortedResult(){
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            String sortWicketAverage = cricketAnalyzer.getSortData(SortData.IPLData.WICKET_AVERAGE);
            IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(sortWicketAverage, IPLWicketDataCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

   @Test
    public void givenIplMostWicketsCSVFile_whenSortedOnBattingBowlingWithBestAverage_shouldReturnSortedResult(){
            try {
                CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
                cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA,BATSMAN_DATA);
                String sortedBattingAndBowlingAverage = cricketAnalyzer.getSortData(SortData.IPLData.BEST_BOWLING_BATTING_AVERAGE);
                IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedBattingAndBowlingAverage, IPLWicketDataCSV[].class);
                Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
            } catch (CricketAnalyzerException e) {
            } catch (CSVBuilderException e) {
                e.printStackTrace();
            }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnMostRunsAndWickets_shouldReturnAllRounderCricketer() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer(CricketAnalyzer.IPLSheet.WICKETS);
            cricketAnalyzer.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA,BATSMAN_DATA);
            String sortAllRounder = cricketAnalyzer.getSortData(SortData.IPLData.ALL_ROUNDER);
            IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(sortAllRounder, IPLWicketDataCSV[].class);
            Assert.assertEquals("David Warner ", iplWicketSheetCSV[iplWicketSheetCSV.length-1].player);
        } catch (CricketAnalyzerException e) { } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
}
