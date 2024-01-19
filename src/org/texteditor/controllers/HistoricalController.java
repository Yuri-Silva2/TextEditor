package org.texteditor.controllers;

import java.util.HashMap;
import java.util.Map;

public class HistoricalController {

    private final static Map<String, String> historicalMap = new HashMap<>();

    public void updateHistoricContent(String id, String newContent) {
        String oldContent = getHistoric(id);
        historicalMap.replace(id, oldContent, newContent);
    }

    public void insertHistoric(String id, String content) {
        historicalMap.putIfAbsent(id, content);
    }

    public void removeHistoric(String id) {
        historicalMap.remove(id);
    }

    private String getHistoric(String id) {
        return historicalMap.get(id);
    }
}
