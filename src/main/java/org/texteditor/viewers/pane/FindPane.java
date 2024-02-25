package org.texteditor.viewers.pane;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.texteditor.controllers.EventController;
import org.texteditor.viewers.tab.find.FindAndReplaceTab;
import org.texteditor.viewers.tab.find.FindTab;

/**
 * The FindPane class represents a custom BorderPane used for displaying find and find-and-replace tabs.
 * It provides methods to configure the pane with tabs and additional components.
 */
public class FindPane extends BorderPane implements CustomPane {

    private final EventController eventController;

    private TabPane tabPane;

    /**
     * Constructs a new FindPane with the specified event controller.
     *
     * @param eventController The EventController instance to use for handling events.
     */
    public FindPane(EventController eventController) {
        super();
        this.eventController = eventController;
    }

    /**
     * Configures the FindPane by creating and adding find and find-and-replace tabs, and setting the layout.
     */
    @Override
    public void configure() {
        tabPane = createTabPane();

        FindTab findTab = new FindTab(eventController);
        findTab.configure();

        FindAndReplaceTab findAndReplaceTab = new FindAndReplaceTab(eventController);
        findAndReplaceTab.configure();

        addComponents(tabPane, findTab, findAndReplaceTab);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        HBox findInfoPane = createHBoxPane();

        setCenter(tabPane);
        setBottom(findInfoPane);
    }

    /**
     * Creates and returns a new HBox, configured with default settings.
     *
     * @return The created HBox
     */
    private HBox createHBoxPane() {
        HBox infoPane = new HBox();
        Label label = new Label("");
        label.setId("find-info-label");
        infoPane.getChildren().add(label);
        infoPane.setPrefHeight(20.0);
        return infoPane;
    }

    /**
     * Creates a new TabPane with custom styling for find tabs.
     *
     * @return The created TabPane for find tabs.
     */
    private TabPane createTabPane() {
        return new TabPane();
    }

    /**
     * Adds the specified tabs to the TabPane.
     *
     * @param tabPane The TabPane to add the tabs to.
     * @param tabs    The tabs to add to the TabPane.
     */
    private void addComponents(TabPane tabPane, Tab... tabs) {
        tabPane.getTabs().addAll(tabs);
    }

    /**
     * Retrieves the TabPane used in the FindPane;
     *
     * @return The TabPane used in the FindPane.
     */
    public TabPane getTabPane() {
        return tabPane;
    }
}
