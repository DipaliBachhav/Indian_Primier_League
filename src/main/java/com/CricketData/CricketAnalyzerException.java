package com.CricketData;

public class CricketAnalyzerException extends Exception{
    enum ExceptionType {
        IPL_FILE_PROBLEM,UNABLE_TO_PARSE, NO_DATA,INVALID_DATA
    }

    ExceptionType type;

    public CricketAnalyzerException(String message, String name) {
        super(message);
        this.type=ExceptionType.valueOf(name);

    }

    public CricketAnalyzerException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    /*public CricketAnalyzerException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }*/
}


