package org.texteditor.viewer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.controller.EventController;

public class FileMenu extends Menu {

    private final EventController eventController;

    public FileMenu(EventController eventController) {
        super("Arquivo");
        this.eventController = eventController;
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
                alertController.showAlertPane();
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

    private void addComponents(MenuItem... menuItems) {
        getItems().addAll(menuItems);
    }
}
