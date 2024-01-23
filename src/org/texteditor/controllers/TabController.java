package org.texteditor.controllers;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.texteditor.TextEditorApplication;
import org.texteditor.models.TextFile;

/**
 * Controller class for managing tabs in the text editor.
 */
public class TabController {

    private final String WRITER_TABPANE_ID = "#writer-tabpane";

    private final Stage stage;

    public TabController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates a new tab with the specified name, ID, and content.
     *
     * @param tabName The name of the tab.
     * @param id      The ID of the tab.
     * @param content The initial content of the tab.
     * @return The created tab.
     */
    public Tab createNewTab(TextFile textFile, String tabName, String id, String content) {
        Tab tab = new Tab(tabName);
        tab.setContent(new TextArea(content));
        tab.setId(id);
        configureCloseEvent(tab, textFile);
        return tab;
    }

    /**
     * @param tab
     */
    public void configureCloseEvent(Tab tab, TextFile textFile) {
        tab.setOnCloseRequest(event -> {
            String tabId = tab.getId();
            if (!textFile.saved()) {
                event.consume();
                TextEditorApplication.createAlertPane(lookupTabPane());
            }
        });
    }

    /**
     * Adds a tab to the list of tabs.
     *
     * @param tab     The tab to be added.
     * @param tabPane The TabPane to which the tab will be added.
     */
    public void addTab(Tab tab, TabPane tabPane) {
        tabPane.getTabs().add(tab);
    }

    /**
     * Select and requests focus on its content.
     *
     * @param tab     The tab to be added.
     * @param tabPane The TabPane to which the tab will be added.
     */
    public void selectedAndFocusTab(Tab tab, TabPane tabPane) {
        tabPane.getSelectionModel().select(tab);
        Node contentNode = tab.getContent();
        if (contentNode instanceof TextArea) contentNode.requestFocus();
    }

    /**
     * Looks up and returns TabPane from the current scene.
     *
     * @return The TabPane if found, null otherwise.
     */
    public TabPane lookupTabPane() {
        Scene scene = stage.getScene();
        return (TabPane) scene.lookup(WRITER_TABPANE_ID);
    }
}
