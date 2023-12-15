package org.texteditor.viewer.menu;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.texteditor.controller.FileController;
import org.texteditor.controller.ModelController;
import org.texteditor.controller.TabController;
import org.texteditor.model.TextFile;
import org.texteditor.viewer.pane.AlertPane;

import java.io.File;
import java.util.UUID;

public class FileMenu extends Menu {

    private final FileController fileController;
    private final TabController tabController;

    public FileMenu(TabController tabController, FileController fileController) {
        super("Arquivo");
        this.tabController = tabController;
        this.fileController = fileController;
    }

    public void configure() {
        setId("file-menu");

        MenuItem newItem = createNewItem();
        MenuItem openItem = createOpenItem();
        MenuItem saveItem = createSaveItem();
        MenuItem saveAsItem = createSaveAsItem();
        MenuItem saveAllItem = createSaveAllItem();
        MenuItem quitItem = createQuitItem();

        addComponents(newItem, openItem, saveItem,
                saveAsItem, saveAllItem, quitItem);
    }

    private MenuItem createNewItem() {
        MenuItem newItem = new MenuItem("Nova guia");
        onNewTabEvent(newItem);
        return newItem;
    }

    private MenuItem createOpenItem() {
        MenuItem openItem = new MenuItem("Abrir");
        onOpenEvent(openItem);
        return openItem;
    }

    private MenuItem createSaveItem() {
        MenuItem saveItem = new MenuItem("Salvar");
        onSaveEvent(saveItem);
        return saveItem;
    }

    private MenuItem createSaveAsItem() {
        MenuItem saveAsItem = new MenuItem("Salvar como");
        onSaveAsEvent(saveAsItem);
        return saveAsItem;
    }

    private MenuItem createSaveAllItem() {
        MenuItem saveAllItem = new MenuItem("Salvar tudo");
        onSaveAllEvent(saveAllItem);
        return saveAllItem;
    }

    private MenuItem createQuitItem() {
        MenuItem quitItem = new MenuItem("Sair");
        onQuitEvent(quitItem);
        return quitItem;
    }

    private void onNewTabEvent(MenuItem menuItem) {
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

    private void onOpenEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {
            File selectedFile = fileController.createFileChooserAndGetFile(
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

    private void onSaveEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {
            TabPane tabPane =  tabController.lookupTabPane();
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

            TextArea textArea = (TextArea) selectedTab.getContent();
            String tabId = selectedTab.getId();

            TextFile textFile = ModelController.requestTextFile(tabId);

            if (textFile.saved()) {
                ModelController.updateTextFile(textFile.uuid().toString(),
                        textArea.getText());

                System.out.println(textFile.filePath());
                fileController.writeFile(textFile.filePath(),
                        textArea.getText());

            } else {
                Stage stage = new Stage();

                AlertPane alertPane = new AlertPane(stage);
                alertPane.configure();

                Scene scene = new Scene(alertPane, 300, 180);

                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        });
    }

    private void onSaveAsEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {

        });
    }

    private void onSaveAllEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> {

        });
    }

    private void onQuitEvent(MenuItem menuItem) {
        menuItem.setOnAction(e -> Platform.exit());
    }

    private void addComponents(MenuItem... menuItems) {
        getItems().addAll(menuItems);
    }
}
