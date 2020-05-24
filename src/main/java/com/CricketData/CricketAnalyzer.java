package com.CricketData;

import com.OpenCsv.CSVBuilderException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyzer {
    List<IPLDAO> csvList = null;
    public enum IPLSheet {RUNS, WICKETS};
    private IPLSheet iplSheet;
    Map<String, IPLDAO> IPLMap = null;

    public CricketAnalyzer() {
    }
    public CricketAnalyzer(IPLSheet iplSheet){
        this.iplSheet=iplSheet;
    }

    public int loadIPLData(IPLSheet iplSheet,String... csvFilePath) throws CricketAnalyzerException, CSVBuilderException {
        IPLMap = IPLAdapterFactory.getIPLData(iplSheet, csvFilePath);
        return IPLMap.size();
    }

    public String getSortFunction(Comparator<IPLDAO> sortWicketsComparator) throws CricketAnalyzerException {
        if (IPLMap.size() == 0 || IPLMap == null)
            throw new CricketAnalyzerException("No Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.average);
        List<IPLDAO> csvFileList=IPLMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sort(csvFileList,runsComparator);
        String sortedData = new Gson().toJson(csvFileList);
        return sortedData;
    }

    public String getBattingAverageWiseSorted() throws CricketAnalyzerException {
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.average);
        return getSortFunction(runsComparator);
    }

    public String getTopStrikeRate() throws CricketAnalyzerException {
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.strikeRate);
        return getSortFunction(runnerComparator);
    }
    public String getMaximumSixesInMatch() throws CricketAnalyzerException {
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.six);
        return getSortFunction(runnerComparator);
    }

    public String getMaximumFoursInMatch() throws CricketAnalyzerException {
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.four);
        return getSortFunction(runnerComparator);

    }
    public String getSortedStrikeRateOfFoursAndSixs() throws CricketAnalyzerException {
        Comparator<IPLDAO> sortStrikeComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<IPLDAO> sortSixComparator = sortStrikeComparator.thenComparing(census -> census.strikeRate);
        Comparator<IPLDAO> sortFourCompartor=sortSixComparator.thenComparing(census->census.four);
        Comparator<IPLDAO> avgComparator=Comparator.comparing(census -> census.strikeRate);
        return getSortFunction(avgComparator);

    }

    public String getSortedDataAverageWithBestStrikeRate() throws CricketAnalyzerException {
        Comparator<IPLDAO> sortStrikeComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<IPLDAO> avgComparator=Comparator.comparing(census -> census.average);
        return getSortFunction(avgComparator);

    }

    public String getSortedRunsWithBestAverage() throws CricketAnalyzerException {
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.runs);
        Comparator<IPLDAO> avgComparator=runnerComparator.thenComparing(census -> census.average);
        return getSortFunction(avgComparator);

    }

    public String getSortedWicketsAverageData() throws CricketAnalyzerException {
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.average);
        return getSortFunction(runsComparator);

    }
    public String getSortedByBowlingStrikeRate() throws CricketAnalyzerException {
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.strikeRate);
        return getSortFunction(runsComparator);

    }
    public String getSortedByEconomyRate() throws CricketAnalyzerException {
        if(IPLMap.size()==0 || IPLMap ==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.economyRate);
        return getSortFunction(runsComparator);
    }

    public String getBowlingStrikeRateWiseSortingWith5wAnd4wOnData() throws CricketAnalyzerException {
        Comparator<IPLDAO> sortStrikeRateComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<IPLDAO> sort4WComparator = sortStrikeRateComparator.thenComparing(census -> census.fourWickets);
        Comparator<IPLDAO> sort5WCompartor = sort4WComparator.thenComparing(census -> census.fiveWickets);
       return getSortFunction(sort5WCompartor);
    }

    public String getWicketsWiseSortDataWithBestAvgerage() throws CricketAnalyzerException {
        Comparator<IPLDAO> sortStrikeRateComparator = Comparator.comparing(census -> census.wickets);
        Comparator<IPLDAO> sort4WComparator = sortStrikeRateComparator.thenComparing(census -> census.average);
        return getSortFunction(sort4WComparator);

    }
    public String getSortingOnBattingAndBowlingWithBestAverage() throws CricketAnalyzerException {
        Comparator<IPLDAO> sortAvgerageComparator = Comparator.comparing(census -> census.average);
        return getSortFunction(sortAvgerageComparator);
    }
    public String getSortingOnMostRunsAndWicketsOnData() throws CricketAnalyzerException {
        Comparator<IPLDAO> sortRunsComparator = Comparator.comparing(census -> census.runs);
        Comparator<IPLDAO> sortWicketsComparator = sortRunsComparator.thenComparing(census -> census.wickets);
        return getSortFunction(sortWicketsComparator);
    }

    private void sort(List<IPLDAO>csvFileList,Comparator<IPLDAO> iplComparator) {
        for (int i = 0; i < csvFileList.size() - 1; i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                IPLDAO census1 = csvFileList.get(j);
                IPLDAO census2 = csvFileList.get(j + 1);
                if (iplComparator.compare(census1, census2) < 0) {
                    csvFileList.set(j, census2);
                    csvFileList.set(j + 1, census1);
                }
            }
        }
    }

}
