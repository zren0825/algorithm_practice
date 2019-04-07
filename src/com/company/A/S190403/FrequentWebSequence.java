package com.company.A.S190403;

import java.util.*;

class Log {
    int timestamp;
    String userID;
    String url;

    public Log(int timestamp, String userID, String url) {
        this.timestamp = timestamp;
        this.userID = userID;
        this.url = url;
    }
}

public class FrequentWebSequence {
    private static List<Log> logs;
    private static Map<String, List<String>> userToWebsitesMap;
    private static Map<String, Integer> sequenceToFrequencyMap;

    // assume logs are sorted by timestamp ascending
    private static void processLog() {
        for (Log log : logs) {
            List<String> websites = userToWebsitesMap.getOrDefault(log.userID, new LinkedList<>());
            websites.add(log.url);
            userToWebsitesMap.put(log.userID, websites);
        }
    }

    private static String getMostFrequentWebSitesSequenceFromMap() {
        String maxFrequentSequence = null;
        int maxFrequency = 0;
        for (List<String> websitesSequence : userToWebsitesMap.values()) {
            for (int i = 0; i + 2 < websitesSequence.size(); i++) {
                String threeWebSitesSequence = websitesSequence.get(i) + websitesSequence.get(i + 1) + websitesSequence.get(i + 2);
                Integer frequency = sequenceToFrequencyMap.getOrDefault(threeWebSitesSequence, 0);
                sequenceToFrequencyMap.put(threeWebSitesSequence, frequency + 1);
                if(frequency > maxFrequency) {
                    maxFrequentSequence = threeWebSitesSequence;
                }
            }
        }
        return maxFrequentSequence;
    }

    public static String getMostFrequentWebSitesSequent() {
        processLog();
        return getMostFrequentWebSitesSequenceFromMap();
    }

    public static void main(String[] args) {

    }
}
