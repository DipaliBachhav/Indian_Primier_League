package com.CricketData;

import com.OpenCsv.CSVBuilderException;

import java.util.Map;

public class WicketAdapter extends IPLAdapater {
    @Override
    public Map<String, IPLDAO> loadIPLData(String... csvFilePath) throws CricketAnalyzerException, CSVBuilderException {
        return super.loadIPLData(IPLWicketDataCSV.class, csvFilePath[0]);
    }
}
