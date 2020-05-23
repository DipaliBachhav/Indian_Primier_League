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

    public String getBattingAverageWiseSorted() throws CricketAnalyzerException {
        if (IPLMap.size() == 0 || IPLMap == null)
            throw new CricketAnalyzerException("No Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.average);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,runsComparator);
        String sortedCensusJson = new Gson().toJson(csvFileList);
        return sortedCensusJson;
    }

    public String getTopStrikeRate() throws CricketAnalyzerException {
        if(IPLMap.size()==0 || IPLMap ==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.strikeRate);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,runnerComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }
    public String getMaximumSixesInMatch() throws CricketAnalyzerException {
        if(IPLMap.size()==0 || IPLMap ==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.six);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,runnerComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }

    public String getMaximumFoursInMatch() throws CricketAnalyzerException {
        if(IPLMap.size()==0 || IPLMap==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.four);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,runnerComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }
    public String getSortedStrikeRateOfFoursAndSixs() throws CricketAnalyzerException {
        if(IPLMap.size()==0 || IPLMap ==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> sortStrikeComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<IPLDAO> sortSixComparator = sortStrikeComparator.thenComparing(census -> census.strikeRate);
        Comparator<IPLDAO> sortFourCompartor=sortSixComparator.thenComparing(census->census.four);
        Comparator<IPLDAO> avgComparator=Comparator.comparing(census -> census.strikeRate);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,sortFourCompartor);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }

    public String getSortedDataAverageWithBestStrikeRate() throws CricketAnalyzerException {
        if(IPLMap.size()==0 || IPLMap ==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> sortStrikeComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<IPLDAO> avgComparator=Comparator.comparing(census -> census.average);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,avgComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }

    public String getSortedRunsWithBestAverage() throws CricketAnalyzerException {
        if(IPLMap.size()==0 || IPLMap ==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.runs);
        Comparator<IPLDAO> avgComparator=runnerComparator.thenComparing(census -> census.average);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,avgComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }

    public String getSortedWicketsAverageData() throws CricketAnalyzerException {
        if(IPLMap.size()==0 || IPLMap ==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.average);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,runsComparator);
        String sortedCensusJson = new Gson().toJson(csvFileList);
        return sortedCensusJson;
    }
    public String getSortedByBowlingStrikeRate() throws CricketAnalyzerException {
        if(IPLMap.size()==0 || IPLMap ==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.strikeRate);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,runsComparator);
        String sortedCensusJson = new Gson().toJson(csvFileList);
        return sortedCensusJson;
    }
    public String getSortedByEconomyRate() throws CricketAnalyzerException {
        if(IPLMap.size()==0 || IPLMap ==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.economyRate);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,runsComparator);
        String sortedCensusJson = new Gson().toJson(csvFileList);
        return sortedCensusJson;
    }

    public String getBowlingStrikeRateWiseSortingWith5wAnd4wOnData() throws CricketAnalyzerException {
        if (IPLMap == null || IPLMap.size() == 0) {
            throw new CricketAnalyzerException("No Census Data", CricketAnalyzerException.ExceptionType.NO_DATA);
        }
        Comparator<IPLDAO> sortStrikeRateComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<IPLDAO> sort4WComparator = sortStrikeRateComparator.thenComparing(census -> census.fourWickets);
        Comparator<IPLDAO> sort5WCompartor = sort4WComparator.thenComparing(census -> census.fiveWickets);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,sort5WCompartor);
        String sorted4sData = new Gson().toJson(csvFileList);
        return sorted4sData;
    }

    public String getWicketsWiseSortDataWithBestAvgerage() throws CricketAnalyzerException {
        if (IPLMap == null || IPLMap.size() == 0) {
            throw new CricketAnalyzerException("No Census Data", CricketAnalyzerException.ExceptionType.NO_DATA);
        }
        Comparator<IPLDAO> sortStrikeRateComparator = Comparator.comparing(census -> census.wickets);
        Comparator<IPLDAO> sort4WComparator = sortStrikeRateComparator.thenComparing(census -> census.average);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,sort4WComparator);
        String sorted4sData = new Gson().toJson(csvFileList);
        return sorted4sData;
    }
    public String getSortingOnBattingAndBowlingWithBestAverage() throws CricketAnalyzerException {
        if (IPLMap == null || IPLMap.size() == 0) {
            throw new CricketAnalyzerException("No Census Data", CricketAnalyzerException.ExceptionType.NO_DATA);
        }
        Comparator<IPLDAO> sortAvgerageComparator = Comparator.comparing(census -> census.average);
        List<IPLDAO> csvFileList=IPLMap.values().stream().collect(Collectors.toList());
        this.sort(csvFileList,sortAvgerageComparator);
        String sorted4sData = new Gson().toJson(csvFileList);
        return sorted4sData;
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
