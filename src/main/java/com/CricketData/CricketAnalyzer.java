package com.CricketData;

import com.OpenCsv.CSVBuilderException;
import com.OpenCsv.CSVBuilderFactory;
import com.OpenCsv.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketAnalyzer {
    List<IPLDAO> csvFileList = null;
    Map<String, IPLDAO> IPLMap = null;


    public CricketAnalyzer() {
        IPLMap = new HashMap<String, IPLDAO>();
        csvFileList = new ArrayList<>();
    }

    public int loadCricketLeagueData(String csvFilePath) throws CricketAnalyzerException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<BatsMansCSVFile> csvFileIterator = csvBuilder
                    .getCSVFileIterator(reader, BatsMansCSVFile.class);
            Iterable<BatsMansCSVFile> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplDataCsv -> {
                        IPLMap.put(iplDataCsv.player, new IPLDAO(iplDataCsv));
                        csvFileList.add(new IPLDAO(iplDataCsv));
                    });
            return this.IPLMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CricketAnalyzerException(e.getMessage(),
                    CricketAnalyzerException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }


    public int loadWicketData(String csvFilePath) throws CricketAnalyzerException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLWicketDataCSV> csvFileIterator = csvBuilder
                    .getCSVFileIterator(reader, IPLWicketDataCSV.class);
            Iterable<IPLWicketDataCSV> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplDataCsv -> {
                        IPLMap.put(iplDataCsv.player, new IPLDAO(iplDataCsv));
                        csvFileList.add(new IPLDAO(iplDataCsv));
                    });
            return this.IPLMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CricketAnalyzerException(e.getMessage(),
                    CricketAnalyzerException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }


    public String getBattingAverageWiseSorted() throws CricketAnalyzerException {
        if (csvFileList.size() == 0 || csvFileList == null)
            throw new CricketAnalyzerException("No Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.average);
        this.sort(runsComparator);
        String sortedCensusJson = new Gson().toJson(csvFileList);
        return sortedCensusJson;
    }

    public String getTopStrikeRate() throws CricketAnalyzerException {
        if(csvFileList.size()==0 || csvFileList==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.strikeRate);
        this.sort(runnerComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }
    public String getMaximumSixesInMatch() throws CricketAnalyzerException {
        if(csvFileList.size()==0 || csvFileList==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.six);
        this.sort(runnerComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }

    public String getMaximumFoursInMatch() throws CricketAnalyzerException {
        if(csvFileList.size()==0 || csvFileList==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.four);
        this.sort(runnerComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }
    public String getSortedStrikeRateOfFoursAndSixs() throws CricketAnalyzerException {
        if(csvFileList.size()==0 || csvFileList==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> sortStrikeComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<IPLDAO> sortSixComparator = sortStrikeComparator.thenComparing(census -> census.strikeRate);
        Comparator<IPLDAO> sortFourCompartor=sortSixComparator.thenComparing(census->census.four);
        Comparator<IPLDAO> avgComparator=Comparator.comparing(census -> census.strikeRate);
        this.sort(avgComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }

    public String getSortedDataAverageWithBestSR() throws CricketAnalyzerException {
        if(csvFileList.size()==0 || csvFileList==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> sortStrikeComparator = Comparator.comparing(census -> census.strikeRate);
        Comparator<IPLDAO> avgComparator=Comparator.comparing(census -> census.average);
        this.sort(avgComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }

    public String getSortedRunsWithBestAverage() throws CricketAnalyzerException {
        if(csvFileList.size()==0 || csvFileList==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> avgComparator=Comparator.comparing(census -> census.average);
        Comparator<IPLDAO> runnerComparator=Comparator.comparing(census -> census.runs);
        this.sort(runnerComparator);
        String sortedDataJson=new Gson().toJson(csvFileList);
        return sortedDataJson;
    }

    public String getSortedWicketsAverageData() throws CricketAnalyzerException {
        if(csvFileList.size()==0 || csvFileList==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.average);
        this.sort(runsComparator);
        String sortedCensusJson = new Gson().toJson(csvFileList);
        return sortedCensusJson;
    }
    public String getSortedByBowlingStrikeRate() throws CricketAnalyzerException {
        if(csvFileList.size()==0 || csvFileList==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.strikeRate);
        this.sort(runsComparator);
        String sortedCensusJson = new Gson().toJson(csvFileList);
        return sortedCensusJson;
    }
    public String getSortedByEconomyRate() throws CricketAnalyzerException {
        if(csvFileList.size()==0 || csvFileList==null)
            throw new CricketAnalyzerException("NO Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<IPLDAO> runsComparator = Comparator.comparing( census-> census.economyRate);
        this.sort(runsComparator);
        String sortedCensusJson = new Gson().toJson(csvFileList);
        return sortedCensusJson;
    }

    private void sort(Comparator<IPLDAO> iplComparator) {
        for (int i = 0; i < csvFileList.size() - 1; i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                IPLDAO census1 = csvFileList.get(j);
                IPLDAO census2 =csvFileList.get(j + 1);
                if (iplComparator.compare(census1, census2) < 0) {
                    csvFileList.set(j, census2);
                    csvFileList.set(j + 1, census1);
                }
            }
        }
    }



}
