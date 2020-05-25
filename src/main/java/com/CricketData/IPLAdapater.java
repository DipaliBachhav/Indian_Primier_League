package com.CricketData;

import com.OpenCsv.CSVBuilderException;
import com.OpenCsv.CSVBuilderFactory;
import com.OpenCsv.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapater {
    public abstract Map<String, IPLDAO> loadIPLData(String... csvFilePath) throws CricketAnalyzerException, CSVBuilderException;

    public <E> Map<String, IPLDAO> loadIPLData(Class<E> iplCSVClass, String csvFilePath) throws CricketAnalyzerException, CSVBuilderException {
        Map<String, IPLDAO> IPLMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, iplCSVClass);
            Iterable<E> csvIterable = () -> csvFileIterator;
            if (iplCSVClass.getName().equals("com.CricketData.BatsMansCSVFile")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(BatsMansCSVFile.class::cast)
                        .forEach(iplCSV -> IPLMap.put(iplCSV.player, new IPLDAO(iplCSV)));
            } else if (iplCSVClass.getName().equals("com.CricketData.IPLWicketDataCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLWicketDataCSV.class::cast)
                        .forEach(iplCSV -> IPLMap.put(iplCSV.player, new IPLDAO(iplCSV)));
            }
            return IPLMap;
        }  catch(IOException | CSVBuilderException e){
            throw new CricketAnalyzerException(e.getMessage(),
                    CricketAnalyzerException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }

}
