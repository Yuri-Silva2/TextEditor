package org.texteditor.controller;

import org.texteditor.model.TextFile;

import java.util.*;

public class ModelController {

    private final static Map<String, TextFile> temporarilyOpenedFiles = new HashMap<>();

    public static void addTextFile(TextFile textFile) {
        temporarilyOpenedFiles.put(textFile.uuid()
                .toString(), textFile);
    }

    public static TextFile requestTextFile(String id) {
        return temporarilyOpenedFiles.get(id);
    }

    public static void updateTextFile(String id, String text) {
        TextFile textFile = requestTextFile(id);

        TextFile newTextFile = new TextFile(textFile.uuid(),
                textFile.name(), textFile.filePath(), text, textFile.saved());

        temporarilyOpenedFiles.replace(id, textFile, newTextFile);
    }

    public static void updateTextFile(String id, String filePath, String text) {
        TextFile textFile = requestTextFile(id);

        TextFile newTextFile = new TextFile(textFile.uuid(),
                textFile.name(), filePath, text, true);

        temporarilyOpenedFiles.replace(id, textFile, newTextFile);
    }
}
