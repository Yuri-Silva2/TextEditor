package org.texteditor.controller;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileController {

    private final String TEMP_FILES_PATH = System.getenv("ProgramFiles") + "\\RedTextEditor\\temp-files\\";
    private final FileChooser.ExtensionFilter FILES_EXTENSION =
            new FileChooser.ExtensionFilter("Text Files", "*.txt");

    private static final Logger logger = Logger.getLogger(FileController.class.getName());

    public boolean thereIsAnUnsavedFile() {
        File path = new File(TEMP_FILES_PATH);
        return path.exists() &&
                Arrays.stream(Objects.requireNonNull(path.listFiles())).toList().isEmpty();
    }

    public void writeFile(String filePath, String fileText) {
        File file = new File(filePath);

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(fileText);
//            file.setReadOnly();
//            Acesso negado

        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }

    public void writeFile(String id, String extension, String fileText) {
        File file = new File(TEMP_FILES_PATH + id + extension);

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(fileText);
//            file.setReadOnly();
//              Acesso negado

        } catch (IOException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }

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

    public File createFileChooserAndGetFile(Stage stage, String content) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(content);
        fileChooser.getExtensionFilters().add(FILES_EXTENSION);

        return fileChooser.showOpenDialog(stage);
    }

    public Map<String, String> readFiles() {
        return null;
    }
}

        /*
        return new TextFile(UUID.randomUUID(), file.getName(),
                file.getPath(), builder.toString(), true);
         */
