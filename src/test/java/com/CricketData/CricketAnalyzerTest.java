package com.CricketData;

import org.junit.Assert;
import org.junit.Test;

public class CricketAnalyzerTest {
    private static final String BATSMAN_DATA = "C:\\Users\\Windows 10\\Desktop\\Indian_Primier_League\\src\\test\\resources\\IPL2019FactsheetMostRuns.csv";
    private static final String BATSMAN_DATA_INCORRECT_FILE = "C:\\Users\\Windows 10\\Desktop\\Indian_Primier_League\\src\\test\\resources\\IPL2019FactsheetMostWkts";
    private static final String BATSMAN_DATA_EMPTY_FILE = "C:\\Users\\Windows 10\\Desktop\\Indian_Primier_League\\src\\test\\resources\\IPL2019FactsheetMostRunsEmpty";


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
            Assert.assertEquals(100, numOfRecords);
        } catch (CricketAnalyzerException e) {
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

}
