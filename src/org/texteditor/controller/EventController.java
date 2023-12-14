package org.texteditor.controller;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.stage.*;
import org.texteditor.model.TextFile;

import java.io.File;
import java.util.UUID;

public class EventController {

    private final Stage stage;
    private final TabController tabController;
    private final FileController fileController;

    public EventController(Stage stage, TabController tabController, FileController fileController) {
        this.stage = stage;
        this.tabController = tabController;
        this.fileController = fileController;
    }

    public void onNewTabEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {
            TabPane tabPane = tabController.lookupTabPane();

            int number = tabPane.getTabs().size() + 1;
            String name = "Sem título (" + number + ")";

            TextFile textFile = new TextFile(UUID.randomUUID(),
                    name, null, "", false);

            ModelController.addTextFile(textFile);

            Tab newTab = tabController.createNewTab(textFile.name(),
                    textFile.uuid().toString(), textFile.text());

            tabController.addTabInListAndRequestFocus(newTab, tabPane);
        });
    }

    public void onOpenEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {
            File selectedFile = fileController.createFileChooserAndGetFile(stage,
                    "Selecionar Arquivo");

            if (selectedFile == null) return;

            String content = fileController.readFile(selectedFile);

            TextFile textFile = new TextFile(UUID.randomUUID(),
                    selectedFile.getName(), selectedFile.getPath(),
                    content, true);

            ModelController.addTextFile(textFile);

            Tab newTab = tabController.createNewTab(textFile.name(),
                    textFile.uuid().toString(), textFile.text());

            TabPane tabPane = tabController.lookupTabPane();

            tabController.addTabInListAndRequestFocus(newTab, tabPane);
        });
    }

    public void onSaveEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {
            TabPane tabPane =  tabController.lookupTabPane();
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

            TextArea textArea = (TextArea) selectedTab.getContent();
            String tabId = selectedTab.getId();

            TextFile textFile = ModelController.requestTextFile(tabId);

            if (textFile.saved()) {
                ModelController.updateTextFile(textFile.uuid().toString(),
                        textArea.getText());

                fileController.writeFile(textFile.filePath(),
                        textArea.getText());

            } else {
//                Stage alertStage = new Stage();
//
//                AlertPane alertPane = new AlertPane();
//                alertPane.defineStage(alertStage);
//                alertPane.configure();
//
//                Scene scene = new Scene(alertPane, 300, 180);
//                alertStage.setScene(scene);
//
//                alertStage.initStyle(StageStyle.UNDECORATED);
//                alertStage.initModality(Modality.APPLICATION_MODAL);
//                alertStage.show();
            }
        });
    }

    public void onSaveAsEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {

        });
    }

    public void onSaveAllEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {

        });
    }

    public void onQuitEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> Platform.exit());
    }

    public void onAlertPaneSaveEvent(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("Salvar Arquivo");
//            fileChooser.getExtensionFilters().add(extension);
//
//            File selectedFile = fileChooser.showSaveDialog(stage);
//            if (selectedFile == null) return;
//
//            TextFileController.updateTextFile(id, selectedFile.getPath(), textArea.getText());
//            tfc.writeFile(TextFileController.requestTextFile(id));
        });
    }

    public void onAlertPaneDoNotSaveEvent(Button button) {
        button.setOnMouseClicked(mouseEvent -> {

        });
    }

    public void onAlertPaneCancelEvent(Button button) {
        button.setOnMouseClicked(e -> {
            stage.close();
        });
    }
}
