package org.texteditor.viewers.menu;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.ModelController;
import org.texteditor.controllers.TabController;
import org.texteditor.models.TextFile;
import org.texteditor.viewers.pane.AlertPane;

import java.io.File;
import java.util.UUID;

/**
 * A menu class responsible for handling file-related operations in the text editor.
 * This class encapsulates functionality related to creating, opening, saving and quiting tabs.
 */
public class FileMenu extends Menu {

    private final FileController fileController;
    private final TabController tabController;

    public FileMenu(TabController tabController, FileController fileController) {
        super("Arquivo");
        this.tabController = tabController;
        this.fileController = fileController;
    }

    /**
     * Configures the menu items and their associated actions.
     */
    public void configure() {
        setId("file-menu");

        MenuItem newItem = createNewItem("Nova guia", this::onNewTabEvent);
        MenuItem openItem = createNewItem("Abrir", this::onOpenEvent);
        MenuItem saveItem = createNewItem("Salvar", this::onSaveEvent);
        MenuItem saveAsItem = createNewItem("Salvar como", this::onSaveAsEvent);
        MenuItem saveAllItem = createNewItem("Salvar tudo", this::onSaveAllEvent);
        MenuItem quitItem = createNewItem("Sair", this::onQuitEvent);

        addComponents(newItem, openItem, saveItem,
                saveAsItem, saveAllItem, quitItem);
    }

    /**
     * Handles the event when the user attempts to quit the application.
     * Exits the application by calling Platform.exit().
     */
    private void onQuitEvent() {
        Platform.exit();
    }

    /**
     * Handles the event when the user wants to save all open tabs.
     * Iterates through all tabs in the TabPane and saves each file using saveFile method.
     */
    private void onSaveAllEvent() {
        TabPane tabPane = tabController.lookupTabPane();
        tabPane.getTabs().forEach(this::saveFile);
    }

    /**
     * Handles the event when the user wants to save the currently selected tab with a new name.
     * Checks if there is a selected tab in the TabPane and calls saveFileAs method for the selected tab.
     */
    private void onSaveAsEvent() {
        TabPane tabPane = tabController.lookupTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        if (selectedTab != null) saveFileAs(selectedTab);
    }

    /**
     * Handles the event when the user wants to save the currently selected tab.
     * Checks if there is a selected tab in the TabPane and calls saveFile method for the selected tab.
     */
    private void onSaveEvent() {
        TabPane tabPane = tabController.lookupTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        if (selectedTab != null) saveFile(selectedTab);
    }

    /**
     * Handles the event when the user wants to open a file.
     * Opens a file dialog to let the user chooser a file, then calls openFile method with the selected file.
     */
    private void onOpenEvent() {
        File selectedFile = fileController.createFileChooserAndGetFile(
                "Selecionar Arquivo");

        if (selectedFile != null) {
            openFile(selectedFile);
        }
    }

    /**
     * Handles the event when the user wants to create a new tab.
     * Creates a new tab in the TabPane with a default name and index, then calls createNewTav method.
     */
    private void onNewTabEvent() {
        TabPane tabPane = tabController.lookupTabPane();

        int number = tabPane.getTabs().size() + 1;
        String name = "Sem título (" + number + ")";

        createNewTab(name);
    }

    /**
     * Creates a MenuItem for creating a new item.
     *
     * @return MenuItem for creating a new item
     */
    private MenuItem createNewItem(String content, Runnable eventHandler) {
        MenuItem newItem = new MenuItem(content);
        newItem.setOnAction(actionEvent -> eventHandler.run());
        return newItem;
    }

    /**
     * Adds an array of MenuItems to the menu.
     *
     * @param menuItems MenuItems to be added to the menu
     */
    private void addComponents(MenuItem... menuItems) {
        getItems().addAll(menuItems);
    }

    /**
     * Opens the specified file, creates a new tab, and displays its content.
     *
     * @param selectedFile File to be opened
     */
    private void openFile(File selectedFile) {
        String content = fileController.readFile(selectedFile);
        createTab(selectedFile.getName(), selectedFile.getPath(), content);
    }

    /**
     * Saves the content of the specified tab.
     *
     * @param tab Tab to be saved
     */
    private void saveFile(Tab tab) {
        saveFileInternal(tab, false);
    }

    /**
     * Saves the content of the specified tab with a new name or location.
     *
     * @param tab Tab to be saved as
     */
    private void saveFileAs(Tab tab) {
        saveFileInternal(tab, true);
    }

    /**
     * Internally handles the saving of a file, considering whether it is a new save or a "Save As" operation.
     *
     * @param tab       Tab associated with the file to be saved
     * @param saveAs    Indicates whether it is a "Save As" operation
     */
    private void saveFileInternal(Tab tab, boolean saveAs) {
        TextArea textArea = (TextArea) tab.getContent();
        String tabId = tab.getId();

        TextFile textFile = ModelController.requestTextFile(tabId);

        if (textFile.saved() && !saveAs) {
            ModelController.updateTextFile(textFile.uuid().toString(), textArea.getText());
            fileController.writeFile(textFile.filePath(), textArea.getText());

        } else {
            File selectedFile = saveAs ? fileController.createFileChooserAndSaveFile("Salvar arquivo") :
                    new File(textFile.filePath());

            if (selectedFile != null) {
                ModelController.updateTextFile(tabId, selectedFile.getPath(), textArea.getText());
                fileController.writeFile(selectedFile.getPath(), textArea.getText());
            }
        }
    }

    /**
     * Creates a new tab with the specified name file path, and content.
     *
     * @param name      Name of the new tab
     * @param filePath  File path associated with the new tab
     * @param content   Content to be displayed in the new tab
     */
    private void createTab(String name, String filePath, String content) {
        TextFile textFile = new TextFile(UUID.randomUUID(), name, filePath, content, true);

        ModelController.addTextFile(textFile);

        Tab newTab = tabController.createNewTab(textFile.name(), textFile.uuid().toString(),
                textFile.text());

        newTab.setOnCloseRequest(event -> handleTabCloseRequest(newTab));

        TabPane tabPane = tabController.lookupTabPane();
        tabController.addTab(newTab, tabPane);
        tabController.selectedAndFocusTab(newTab, tabPane);
    }

    /**
     * Creates a new untitled tab and displays it.
     *
     * @param tabName Name to be given to the new tab
     */
    private void createNewTab(String tabName) {
        TextFile textFile = new TextFile(UUID.randomUUID(), tabName, null, "",
                false);
        ModelController.addTextFile(textFile);

        Tab newTab = tabController.createNewTab(textFile.name(), textFile.uuid().toString(),
                textFile.text());

        configureTabCloseHandler(newTab);
        TabPane tabPane = tabController.lookupTabPane();
        tabController.addTab(newTab, tabPane);
        tabController.selectedAndFocusTab(newTab, tabPane);
    }

    /**
     * Configures the close handler for a given tab.
     *
     * @param tab Tab for which the close handler is configured
     */
    private void configureTabCloseHandler(Tab tab) {
        tab.setOnCloseRequest(event -> handleTabCloseRequest(tab));
    }

    /**
     * Handles the close request for a given tab.
     *
     * @param tab Tab for which the close request is handled
     */
    private void handleTabCloseRequest(Tab tab) {
        String tabId = tab.getId();
        TextFile textFile = ModelController.requestTextFile(tabId);

        if (!textFile.saved()) showUnsavedChangesDialog(tab);
    }

    /**
     * Shows an alert dialog for unsaved changes when closing a tab.
     *
     * @param tab Tab associated with the unsaved changes
     */
    private void showUnsavedChangesDialog(Tab tab) {
        Stage newStage = new Stage();
        AlertPane alertPane = new AlertPane(newStage, new FileController(newStage),
                tabController);
        alertPane.configure();

        Scene scene = new Scene(alertPane, 300, 180);

        newStage.setScene(scene);
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.show();
    }
}
