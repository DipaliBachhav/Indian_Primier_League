package com.CricketData;

import com.OpenCsv.CSVBuilderException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketAnalyzer {
    List<IPLDAO> csvList = null;

    public CricketAnalyzer() {
    }
    public enum IPLSheet {RUNS, WICKETS};
    private IPLSheet iplSheet;
    Map<String, IPLDAO> IPLMap = null;

    public CricketAnalyzer(IPLSheet iplSheet){
        this.iplSheet=iplSheet;
    }

    public int loadIPLData(IPLSheet iplSheet,String... csvFilePath) throws CricketAnalyzerException, CSVBuilderException {
        IPLMap = IPLAdapterFactory.getIPLData(iplSheet, csvFilePath);
        return IPLMap.size();
    }

  public String getSortData(SortData.IPLData iplData) {
      Comparator<IPLDAO> iplComparator;
      iplComparator = SortData.getData(iplData);
      List sortedData = IPLMap.values().stream().
              sorted(iplComparator).collect(Collectors.toList());
      return new Gson().toJson(sortedData);
  }

}
