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
    static Map<String, CricketAnalyzerDAO> IPLMap = null;
    List<BatsMansCSVFile> csvFileList = null;

    public CricketAnalyzer() {

        IPLMap = new HashMap<>();
    }

    public  int loadCricketLeagueData(String csvFilePath) throws CricketAnalyzerException {
        try ( Reader reader = Files.newBufferedReader(Paths.get(csvFilePath)))
        {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<BatsMansCSVFile> csvFileIterator = csvBuilder.getCSVFileIterator(reader, BatsMansCSVFile.class);
            Iterable<BatsMansCSVFile> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(),false).
                    forEach(iplDataCsv -> IPLMap.put(iplDataCsv.player,new CricketAnalyzerDAO(iplDataCsv)));
            if(IPLMap.size() == 0)
                throw new CricketAnalyzerException("NO_DATA_FOUND",
                        CricketAnalyzerException.ExceptionType.IPL_FILE_PROBLEM);
            return this.IPLMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new CricketAnalyzerException(e.getMessage(),
                    CricketAnalyzerException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }
    public  String bestBattingAverage() throws CricketAnalyzerException {
        if (csvFileList.size() == 0 || csvFileList == null)
            throw new CricketAnalyzerException("No Data",CricketAnalyzerException.ExceptionType.NO_DATA);
        Comparator<BatsMansCSVFile> censusCSVComparator = Comparator.comparing(census -> census.average);
        List<CricketAnalyzerDAO> cricketAnalyzerDAOList = IPLMap.values().stream().collect(Collectors.toList());
        this.sort(censusCSVComparator);
        String sortedData = new Gson().toJson(censusCSVComparator);
        return sortedData;

    }

    private void sort(Comparator<BatsMansCSVFile> iplComparator) {
        for (int i = 0; i < csvFileList.size() - 1; i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                BatsMansCSVFile census1 = csvFileList.get(j);
                BatsMansCSVFile census2 =csvFileList.get(j + 1);
                if (iplComparator.compare(census1, census2) < 0) {
                    csvFileList.set(j, census2);
                    csvFileList.set(j + 1, census1);
                }
            }
        }
    }
}
