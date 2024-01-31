package org.texteditor.controllers;

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

/**
 * Controller class for managing file operations in the text editor.
 */
public class FileController {

    private final String TEMP_FILES_PATH = System.getProperty("user.home") + "\\Documents\\RedTextEditor\\temp-files\\";

    private final FileChooser.ExtensionFilter FILES_EXTENSION =
            new FileChooser.ExtensionFilter("Text Files", "*.txt");

    private static final Logger logger = Logger.getLogger(FileController.class.getName());

    private final Stage stage;

    public FileController(Stage stage) {
        this.stage = stage;
    }

    public void createDefaultFolder() {
        File file = new File(TEMP_FILES_PATH);
        if (file.exists()) return;
        file.mkdirs();
    }

    /**
     * Checks if there is an unsaved file in the temporary files' directory.
     *
     * @return True if there is as unsaved file, false otherwise.
     */
    public boolean thereIsAnUnsavedFile() {
        File path = new File(TEMP_FILES_PATH);
        return path.exists() &&
                Arrays.stream(Objects.requireNonNull(path.listFiles())).toList().isEmpty();
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
            logger.log(Level.WARNING, e.toString());
        }
    }

    /**
     * Writes the given text to a file with a specified id, extension, and content.
     *
     * @param id        The identifier for the file.
     * @param extension The file extension.
     * @param fileText  The text to be written to the file.
     */
    public void writeFile(String id, String extension, String fileText) {
        File file = new File(TEMP_FILES_PATH + id + extension);

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(fileText);

        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString());
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
            logger.log(Level.WARNING, e.toString());
        }

        return "";
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

    // Placeholder method for reading multiple files (not implemented)
    public Map<String, String> readFiles() {
        return null;
    }
}
