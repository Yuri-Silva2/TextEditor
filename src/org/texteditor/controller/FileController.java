package org.texteditor.controller;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    Controller class for managing file operations in the text editor.
 */

public class FileController {

    // Path for temporary files directory
    private final String TEMP_FILES_PATH = System.getenv("ProgramFiles") + "\\RedTextEditor\\temp-files\\";

    // Extension filter for file chooser
    private final FileChooser.ExtensionFilter FILES_EXTENSION =
            new FileChooser.ExtensionFilter("Text Files", "*.txt");

    // Logger for logging warnings
    private static final Logger logger = Logger.getLogger(FileController.class.getName());

    // Reference to the JavaFX stage
    private final Stage stage;

    // Constructor for FileController
    public FileController(Stage stage) {
        this.stage = stage;
    }

    // Check if there is an unsaved file in the temporary files directory
    public boolean thereIsAnUnsavedFile() {
        File path = new File(TEMP_FILES_PATH);
        return path.exists() &&
                Arrays.stream(Objects.requireNonNull(path.listFiles())).toList().isEmpty();
    }

    // Write text to a file specified by the filePath
    public void writeFile(String filePath, String fileText) {
        File file = new File(filePath);

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(fileText);

        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }

    // Write text to a file with a specified id, extension, and content
    public void writeFile(String id, String extension, String fileText) {
        File file = new File(TEMP_FILES_PATH + id + extension);

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(fileText);

        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }

    // Read the contents of a file and return as a string
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
            logger.log(Level.WARNING, e.toString());
        }

        return "";
    }

    // Create a file chooser dialog for opening files and return the selected file
    public File createFileChooserAndGetFile(String content) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(content);
        fileChooser.getExtensionFilters().add(FILES_EXTENSION);

        return fileChooser.showOpenDialog(stage);
    }

    // Create a file chooser dialog for opening files and return the selected file
    public File createFileChooserAndSaveFile(String content) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(content);
        fileChooser.getExtensionFilters().add(FILES_EXTENSION);

        return fileChooser.showSaveDialog(stage);
    }

    // Placeholder method for reading multiple files (not implemented)
    public Map<String, String> readFiles() {
        return null;
    }
}
