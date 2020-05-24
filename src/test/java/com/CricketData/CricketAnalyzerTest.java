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
            String sortedData = cricketAnalyzer.getBattingAverageWiseSorted();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(sortedData, BatsMansCSVFile[].class);
            Assert.assertEquals("MS Dhoni", iplRunCSV[0].player);
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
            String strikeRate = cricketAnalyzer.getTopStrikeRate();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(strikeRate, BatsMansCSVFile[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[0].player);
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
            String foursInMatch = cricketAnalyzer.getMaximumFoursInMatch();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(foursInMatch, BatsMansCSVFile[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunCSV[0].player);
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
            String sixesInMatch = cricketAnalyzer.getMaximumSixesInMatch();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(sixesInMatch, BatsMansCSVFile[].class);
            Assert.assertEquals("Andre Russell", iplRunCSV[0].player);
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
            String sortedStrikeRateOfFoursAndSix = cricketAnalyzer.getSortedStrikeRateOfFoursAndSixs();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(sortedStrikeRateOfFoursAndSix, BatsMansCSVFile[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[0].player);
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
            String battingaverage = cricketAnalyzer.getSortedDataAverageWithBestStrikeRate();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(battingaverage, BatsMansCSVFile[].class);
            Assert.assertEquals("MS Dhoni", iplRunCSV[0].player);
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
            String runsWithBestAverage = cricketAnalyzer.getSortedRunsWithBestAverage();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(runsWithBestAverage, BatsMansCSVFile[].class);
            Assert.assertEquals("David Warner ", iplRunCSV[0].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLWicketsData_shouldReturnTotalNumberOfPlayers() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer();
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLWicketsData_shouldReturnWrongFileName() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer();
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA_WRONG_FILE_NAME);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLWicketsData_shouldReturnWrongFileType() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer();
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA_WRONG_FILE_TYPE);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLWicketsData_shouldReturnWrongFileDelimeter() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer();
            int numOfRecords = cricketLeagueAnalysis.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA_WRONG_FILE_DELIMETER);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplWicketsCSVFile_ShouldReturnPlayerName_WhoHasTopBowlingAverage() {
        try {
            CricketAnalyzer cricketLeagueAnalyser = new CricketAnalyzer();
            cricketLeagueAnalyser.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            String sortedIPLData = cricketLeagueAnalyser.getSortedWicketsAverageData();
            IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketDataCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[0].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplWicketsCSVFile_ShouldReturnPlayerName_WhoHasTopBowlingStrikeRate() {
        try {
            CricketAnalyzer cricketLeagueAnalyser = new CricketAnalyzer();
            cricketLeagueAnalyser.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            String sortedIPLData = cricketLeagueAnalyser.getSortedByBowlingStrikeRate();
            IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketDataCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWicketSheetCSV[0].player);
        } catch (CricketAnalyzerException e) {
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplMostWicketsCSVFile_ShouldReturnBowlerName_WhoHasBestEconomyRate()  {
        try {
            CricketAnalyzer cricketLeagueAnalyser = new CricketAnalyzer();
            cricketLeagueAnalyser.loadIPLData(CricketAnalyzer.IPLSheet.WICKETS,WICKETS_DATA);
            String sortedIPLData = cricketLeagueAnalyser.getSortedByEconomyRate();
            IPLWicketDataCSV[] iplWicketSheetCSV = new Gson().fromJson(sortedIPLData, IPLWicketDataCSV[].class);
            Assert.assertEquals("Ben Cutting", iplWicketSheetCSV[0].player);
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
            String strikeRateWiseSortingWith5wAnd4wOnData = cricketAnalyzer.getBowlingStrikeRateWiseSortingWith5wAnd4wOnData();
            IPLWicketDataCSV[] iplRunCSV = new Gson().fromJson(strikeRateWiseSortingWith5wAnd4wOnData, IPLWicketDataCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplRunCSV[0].player);
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
            String strikeRateWiseSortingWith5wAnd4wOnData = cricketAnalyzer.getSortedDataAverageWithBestStrikeRate();
            IPLWicketDataCSV[] iplRunCSV = new Gson().fromJson(strikeRateWiseSortingWith5wAnd4wOnData, IPLWicketDataCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplRunCSV[0].player);
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
            String wicketsWiseSortDataWithBestAvgerage = cricketAnalyzer.getWicketsWiseSortDataWithBestAvgerage();
            IPLWicketDataCSV[] iplRunCSV = new Gson().fromJson(wicketsWiseSortDataWithBestAvgerage, IPLWicketDataCSV[].class);
            Assert.assertEquals("Imran Tahir", iplRunCSV[0].player);
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
                String sortedBattingAndBowlingAverage = cricketAnalyzer.getSortingOnBattingAndBowlingWithBestAverage();
                IPLWicketDataCSV[] iplWktsCSV = new Gson().fromJson(sortedBattingAndBowlingAverage, IPLWicketDataCSV[].class);
                Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[0].player);
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
            String sortedBattingAndBowlingAvg = cricketAnalyzer.getSortingOnMostRunsAndWicketsOnData();
            IPLWicketDataCSV[] iplWktsCSV = new Gson().fromJson(sortedBattingAndBowlingAvg, IPLWicketDataCSV[].class);
            Assert.assertEquals("David Warner ", iplWktsCSV[0].player);
        } catch (CricketAnalyzerException e) { } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
}
