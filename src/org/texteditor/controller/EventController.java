package org.texteditor.controller;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.texteditor.model.TextFile;

import java.io.File;
import java.util.UUID;

public class EventController {

    private final Stage stage;

    public EventController(Stage stage) {
        this.stage = stage;
    }

    public void setNewGuideEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {
            TextFileController tfc = new TextFileController();
            TabPane tabPane = tfc.lookupTabpane(stage);

            int number = tabPane.getTabs().size() + 1;
            String name = "Sem título (" + number + ")";

            TextFile textFile = new TextFile(UUID.randomUUID(),
                    name, null, "", false);

            TextFileController.addFile(textFile);
            tfc.createNewTab(textFile, tabPane);
        });
    }

    public void openEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecionar Arquivos");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                TextFileController tfc = new TextFileController();
                TabPane tabPane = tfc.lookupTabpane(stage);

                TextFile textFile = tfc.readFile(file);

                TextFileController.addFile(textFile);
                tfc.createNewTab(textFile, tabPane);
            }
        });
    }

    public void saveEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {
            TextFileController tfc = new TextFileController();
            TabPane tabPane =  tfc.lookupTabpane(stage);

            Tab tab = tabPane.getSelectionModel().getSelectedItem();
            TextFile textFile = TextFileController.requestTextFile(tab.getId());

            if (textFile.saved()) {

            } else {

            }
        });
    }

    public void saveAsEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {

        });
    }

    public void saveAllEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {

        });
    }

    public void setQuitEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> Platform.exit());
    }
}
