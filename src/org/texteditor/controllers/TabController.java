package org.texteditor.controllers;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Controller class for managing tabs in the text editor.
 */
public class TabController {

    private static final String WRITER_TABPANE_ID = "#writer-tabpane";

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
    public Tab createNewTab(String tabName, String id, String content) {
        Tab tab = new Tab(tabName);
        tab.setContent(new TextArea(content));
        tab.setId(id);
        return tab;
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
