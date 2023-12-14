package org.texteditor.controller;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.codehaus.jackson.map.ObjectMapper;
import org.texteditor.model.TextFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class TextFileController {

    private final static Map<UUID, TextFile> temporarilyOpenedFiles = new HashMap<>();

    public boolean thereIsAnUnsavedFile() {
        File path = new File(System.getenv("ProgramFiles") + "\\RedTextEditor\\temp-files\\");
        return path.exists() && Arrays.stream(Objects.requireNonNull(path.listFiles())).toList().isEmpty();
    }

    public void writeTempJSON(TextFile textFile) {
        try (FileWriter fw = new FileWriter(textFile.uuid()+ ".json")) {
            ObjectMapper objectMapper = new ObjectMapper();

            fw.write(objectMapper.writeValueAsString(textFile));
            fw.flush();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readTempJSON(TextFile textFile) {

    }

    public void createNewTab(TextFile textFile, TabPane tabPane) {
        ObservableList<Tab> tabs = tabPane.getTabs();
        Tab tab = new Tab(textFile.name());

        tab.setContent(new TextArea(textFile.text()));
        tab.setId(textFile.uuid());
        tabs.add(tab);

        tabPane.getSelectionModel().select(tab);
        Node contentNode = tab.getContent();

        if (contentNode instanceof TextArea) {
            contentNode.requestFocus();
        }
    }

    public TabPane lookupTabpane(Stage stage) {
        Scene scene = stage.getScene();
        return (TabPane) scene.lookup("#writer-tabpane");
    }

    public TextFile readFile(File file) {
        StringBuilder builder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(Paths.get(file.getPath()));

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                builder.append(line)
                        .append(System.lineSeparator());
            }
            scanner.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new TextFile(UUID.randomUUID(), file.getName(), file.getPath(), builder.toString(), true);
    }

    public static void addFile(TextFile textFile) {
        temporarilyOpenedFiles.put(textFile.uuid(), textFile);
    }

    public static TextFile requestTextFile(String id) {
        return temporarilyOpenedFiles.get(id);
    }
}
