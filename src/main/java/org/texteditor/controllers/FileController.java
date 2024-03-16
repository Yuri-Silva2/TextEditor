package org.texteditor.controllers;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Controller class for managing file operations in the text editor.
 */
public class FileController {

    private final FileChooser.ExtensionFilter FILES_EXTENSION =
            new FileChooser.ExtensionFilter("Text Files", "*.txt");

    private final Stage stage;

    public FileController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Writes the given text to a file specified by the filePath.
     *
     * @param filePath The path of the file to write.
     * @param fileText The text to be written to the file.
     */
    public void writeFile(String filePath, String fileText) {
        File file = new File(filePath);

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(fileText);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the contents of a file and returns them as a string.
     *
     * @param file The file to read.
     * @return The contents of the file as a string.
     */
    public String readFile(File file) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(Paths.get(file.getPath()));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                stringBuilder.append(line)
                        .append(System.lineSeparator());
            }

            scanner.close();
            return stringBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a file chooser dialog for opening files and returns the selected file.
     *
     * @param content The title/content of the file chooser dialog.
     * @return The selected file or null if no file is chosen.
     */
    public File createFileChooserAndGetFile(String content) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(content);
        fileChooser.getExtensionFilters().add(FILES_EXTENSION);

        return fileChooser.showOpenDialog(stage);
    }

    /**
     * Creates a file chooser dialog for saving files and returns the selected file.
     *
     * @param content The title/content of the file chooser dialog.
     * @return The selected file or null if no file is chosen.
     */
    public File createFileChooserAndSaveFile(String content) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(content);
        fileChooser.getExtensionFilters().add(FILES_EXTENSION);

        return fileChooser.showSaveDialog(stage);
    }
}
