package com.CricketData;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Type;

public class CricketAnalyzerTest {
    private static final String BATSMAN_DATA = "C:\\Users\\Windows 10\\Desktop\\Indian_Primier_League\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String BATSMAN_DATA_INCORRECT_FILE = "C:\\Users\\Windows 10\\Desktop\\Indian_Primier_League\\src\\test\\resources\\IPL2019FactsheetMostWkts.csv";
    private static final String BATSMAN_DATA_EMPTY_FILE = "C:\\Users\\Windows 10\\Desktop\\Indian_Primier_League\\src\\test\\resources\\IPL2019FactsheetMostRunsEmpty.csv";


    @Test
    public void givenIPLBatsmanData_shouldReturnTotalNumberOfPlayers() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer();
            int numOfRecords = cricketLeagueAnalysis.loadCricketLeagueData(BATSMAN_DATA);
            Assert.assertEquals(100, numOfRecords);
        } catch (CricketAnalyzerException e) {
        }
    }

    @Test
    public void givenIPLBatsmanData_shouldReturnsIncorrectFile() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer();
            int numOfRecords = cricketLeagueAnalysis.loadCricketLeagueData(BATSMAN_DATA_INCORRECT_FILE);
            Assert.assertEquals(99, numOfRecords);
        } catch (CricketAnalyzerException e) {
            Assert.assertEquals(CricketAnalyzerException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLBatsmanData_shouldReturnEmptyCSVFile() {
        try {
            CricketAnalyzer cricketLeagueAnalysis = new CricketAnalyzer();
            int numOfRecords = cricketLeagueAnalysis.loadCricketLeagueData(BATSMAN_DATA_EMPTY_FILE);
            Assert.assertEquals(0, numOfRecords);
        } catch (CricketAnalyzerException e) {
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOnAverage_shouldReturnSortedResult() {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketLeagueData(BATSMAN_DATA);
            String sortedData = cricketAnalyzer.getBattingAverageWiseSorted();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(sortedData, BatsMansCSVFile[].class);
            Assert.assertEquals("MS Dhoni", iplRunCSV[0].player);
        } catch (CricketAnalyzerException e) {
        }
    }

    @Test
    public void givenCricketLeagueData_whenSorted_shouldReturnBestStrikeRate() throws CricketAnalyzerException {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketLeagueData(BATSMAN_DATA);
            String strikeRate = cricketAnalyzer.getTopStrikeRate();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(strikeRate, BatsMansCSVFile[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[0].player);
        } catch (CricketAnalyzerException e) {
        }
    }

    @Test
    public void givenCricketLeagueData_whenSorted_shouldReturnFours() throws CricketAnalyzerException {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketLeagueData(BATSMAN_DATA);
            String foursInMatch = cricketAnalyzer.getMaximumFoursInMatch();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(foursInMatch, BatsMansCSVFile[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunCSV[0].player);
        } catch (CricketAnalyzerException e) {

        }
    }

    @Test
    public void givenCricketLeagueData_whenSorted_shouldReturnSix() throws CricketAnalyzerException {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketLeagueData(BATSMAN_DATA);
            String sixesInMatch = cricketAnalyzer.getMaximumSixesInMatch();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(sixesInMatch, BatsMansCSVFile[].class);
            Assert.assertEquals("Andre Russell", iplRunCSV[0].player);
        } catch (CricketAnalyzerException e) {
        }
    }

    @Test
    public void givenCricketLeagueData_whenSorted_shouldReturnFourAndSix() throws CricketAnalyzerException {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketLeagueData(BATSMAN_DATA);
            String sortedStrickRateOfFoursAndSixs = cricketAnalyzer.getSortedStrikeRateOfFoursAndSixs();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(sortedStrickRateOfFoursAndSixs, BatsMansCSVFile[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[0].player);
        } catch (CricketAnalyzerException e) {
        }
    }

    @Test
    public void GivenCricketLeagueData_whenSorted_ShouldReturnAverageWithBestSR() throws CricketAnalyzerException {
        try {
            CricketAnalyzer cricketAnalyzer = new CricketAnalyzer();
            cricketAnalyzer.loadCricketLeagueData(BATSMAN_DATA);
            String battingaverage = cricketAnalyzer.getSortedDataAverageWithBestSR();
            BatsMansCSVFile[] iplRunCSV = new Gson().fromJson(battingaverage, BatsMansCSVFile[].class);
            Assert.assertEquals("MS Dhoni", iplRunCSV[0].player);
        } catch (CricketAnalyzerException e) {
        }
    }
}