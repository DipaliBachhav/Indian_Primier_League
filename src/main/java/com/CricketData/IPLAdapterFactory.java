package com.CricketData;

import com.OpenCsv.CSVBuilderException;

import java.util.Map;

public class IPLAdapterFactory {
    public static Map<String, IPLDAO> getIPLData(CricketAnalyzer.IPLSheet iplSheet,
                                                       String... csvFilePath) throws CricketAnalyzerException, CSVBuilderException {

        if(iplSheet.equals(CricketAnalyzer.IPLSheet.RUNS))
            return new RunAdapater().loadIPLData(csvFilePath);
        else
        if(iplSheet.equals(CricketAnalyzer.IPLSheet.WICKETS))
            return new WicketAdapter().loadIPLData(csvFilePath);
        throw new CricketAnalyzerException("Unknown Data",CricketAnalyzerException.ExceptionType.INVALID_DATA);
    }
}

