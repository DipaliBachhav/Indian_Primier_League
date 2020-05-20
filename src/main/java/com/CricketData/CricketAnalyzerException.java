package com.CricketData;

public class CricketAnalyzerException extends Exception{
    enum ExceptionType {
        IPL_FILE_PROBLEM,UNABLE_TO_PARSE, NO_DATA
    }

    CricketAnalyzerException.ExceptionType type;

    public CricketAnalyzerException(String message, CricketAnalyzerException.ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CricketAnalyzerException(String message, String name) {
        super(message);
        this.type = CricketAnalyzerException.ExceptionType.valueOf(name);
    }
}
