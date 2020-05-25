package com.CricketData;

import javafx.scene.control.ComboBox;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortData {
    static Map<IPLData, Comparator> sortComparator = new HashMap<>();

    public enum IPLData {
        BATSMAN_AVERAGE, STRIKE_RATE, FOURS, SIX,STRIKE_RATE_AVERAGE_SIX_FOURS,
        AVERAGE_BEST_STRIKE_RATE, MAX_RUN_WITH_BEST_AVERAGE,WICKET_BOWLING_AVERAGE,WICKET_STRIKE_RATE,
        ECONOMY_RATE,FIVE_FOUR_WICKETS_STRIKE_RATE, WICKET_BEST_BOWLING_AVERAGE_STRIKE_RATE,WICKET_AVERAGE,
        BEST_BOWLING_BATTING_AVERAGE, ALL_ROUNDER;

    }
    public static Comparator getData(IPLData iplData) {

        Comparator<IPLDAO> comparatorForBatsmanAverage = Comparator.comparing(cricket -> cricket.average);
        Comparator<IPLDAO> comparatorForStrikeRate = Comparator.comparing(cricket -> cricket.strikeRate);
        Comparator<IPLDAO> comparatorForFour = Comparator.comparing(cricket -> cricket.four);
        Comparator<IPLDAO> comparatorForSix = Comparator.comparing(cricket -> cricket.six);
        Comparator<IPLDAO> comparatorForBatsmanRun = Comparator.comparing(cricket -> cricket.runs);
        Comparator<IPLDAO> comparatorForWicketBowlingAverage = Comparator.comparing(cricket -> cricket.average);
        Comparator<IPLDAO> comparatorForWicketStrikeRate = Comparator.comparing(cricket -> cricket.strikeRate);
        Comparator<IPLDAO> comparatorForEconomy = Comparator.comparing(cricket -> cricket.economyRate);
        Comparator<IPLDAO> comparatorForFiveWickets = Comparator.comparing(cricket -> cricket.fiveWickets);
        Comparator<IPLDAO> comparatorForWicketsAverage = Comparator.comparing(cricket -> cricket.wickets);

        sortComparator.put(iplData.BATSMAN_AVERAGE, comparatorForBatsmanAverage);
        sortComparator.put(iplData.STRIKE_RATE, comparatorForStrikeRate);
        sortComparator.put(iplData.FOURS, comparatorForFour);
        sortComparator.put(iplData.SIX, comparatorForSix);
        sortComparator.put(iplData.STRIKE_RATE_AVERAGE_SIX_FOURS, comparatorForStrikeRate
                                                    .thenComparing(comparatorForSix)
                                                    .thenComparing(comparatorForFour));
        sortComparator.put(iplData.AVERAGE_BEST_STRIKE_RATE, comparatorForBatsmanAverage
                                                    .thenComparing(comparatorForStrikeRate));
        sortComparator.put(iplData.MAX_RUN_WITH_BEST_AVERAGE, comparatorForBatsmanRun
                                                    .thenComparing(comparatorForBatsmanAverage));
        sortComparator.put(iplData.WICKET_BOWLING_AVERAGE, comparatorForWicketBowlingAverage);
        sortComparator.put(iplData.WICKET_STRIKE_RATE,comparatorForWicketStrikeRate);
        sortComparator.put(iplData.ECONOMY_RATE, comparatorForEconomy);
        sortComparator.put(IPLData.FIVE_FOUR_WICKETS_STRIKE_RATE, comparatorForFour
                                                    .thenComparing(comparatorForFiveWickets)
                                                    .thenComparing(comparatorForStrikeRate));
        sortComparator.put(IPLData.WICKET_BEST_BOWLING_AVERAGE_STRIKE_RATE, comparatorForWicketBowlingAverage
                                                    .thenComparing(comparatorForWicketStrikeRate));
        sortComparator.put(IPLData.WICKET_AVERAGE,comparatorForWicketsAverage);
        sortComparator.put(IPLData.BEST_BOWLING_BATTING_AVERAGE, comparatorForWicketBowlingAverage
                .thenComparing(comparatorForBatsmanAverage));
        sortComparator.put(IPLData.ALL_ROUNDER, comparatorForBatsmanRun
                .thenComparing(comparatorForWicketsAverage));

        Comparator<IPLDAO> comparator = sortComparator.get(iplData);
        return comparator;
    }
}
