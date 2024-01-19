package org.texteditor.controllers;

import org.texteditor.models.TextFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for managing text file operations in the text editor.
 */
public class TextFileController {

    private final static Map<String, TextFile> openedFiles = new HashMap<>();

    /**
     * Adds a text file to the collection of temporarily opened files.
     *
     * @param textFile The text file to be added.
     */
    public static void addTextFile(TextFile textFile) {
        openedFiles.putIfAbsent(textFile.uuid().toString(),
                textFile);
    }

    /**
     * Retrieves a text file from the collection based on its ID.
     *
     * @param id The id of the text file.
     * @return The requested text file or null if not found.
     */
    public static TextFile requestTextFile(String id) {
        return openedFiles.get(id);
    }

    /**
     * Updates the content of a text file in the collection.
     *
     * @param id   The ID of the text file.
     * @param text The new content of the text file.
     */
    public static void updateTextFile(String id, String text) {
        TextFile textFile = requestTextFile(id);

        TextFile toUpdateTextFile = new TextFile(textFile.uuid(),
                textFile.name(), textFile.filePath(), text, textFile.saved());

        openedFiles.replace(id, textFile, toUpdateTextFile);
    }

    /**
     * Updates the content and file path of a text file in the collection.
     *
     * @param id       The ID of the text file.
     * @param filePath The new file path of the text file.
     * @param text     The new content of the text file.
     */
    public static void updateTextFile(String id, String filePath, String text) {
        TextFile textFile = requestTextFile(id);

        TextFile toUpdateTextFile = new TextFile(textFile.uuid(),
                textFile.name(), filePath, text, true);

        openedFiles.replace(id, textFile, toUpdateTextFile);
    }
}
