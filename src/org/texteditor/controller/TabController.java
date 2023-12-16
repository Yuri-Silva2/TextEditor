package org.texteditor.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TabController {

    private static final String WRITER_TABPANE_ID = "#writer-tabpane";

    private final Stage stage;

    public TabController(Stage stage) {
        this.stage = stage;
    }

    public Tab createNewTab(String tabName, String id, String content) {
        Tab tab = new Tab(tabName);
        tab.setContent(new TextArea(content));
        tab.setId(id);
        return tab;
    }

    public void addTabInListAndRequestFocus(Tab tab, TabPane tabPane) {
        ObservableList<Tab> tabs = tabPane.getTabs();
        tabs.add(tab);
        tabPane.getSelectionModel().select(tab);
        Node contentNode = tab.getContent();
        if (contentNode instanceof TextArea) contentNode.requestFocus();
    }

    public TabPane lookupTabPane() {
        Scene scene = stage.getScene();
        return (TabPane) scene.lookup(WRITER_TABPANE_ID);
    }
}
