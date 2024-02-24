package org.texteditor.viewers.pane;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.texteditor.controllers.EventController;
import org.texteditor.viewers.tab.locate.FindAndReplaceTab;
import org.texteditor.viewers.tab.locate.LocateTab;

/**
 * The LocatePane class represents a custom BorderPane used for displaying locate and find-and-replace tabs.
 * It provides methods to configure the pane with tabs and additional components.
 */
public class LocatePane extends BorderPane implements CustomPane {

    private final EventController eventController;

    private TabPane tabPane;

    /**
     * Constructs a new LocatePane with the specified event controller.
     *
     * @param eventController The EventController instance to use for handling events.
     */
    public LocatePane(EventController eventController) {
        super();
        this.eventController = eventController;
    }

    /**
     * Configures the LocatePane by creating and adding locate and find-and-replace tabs, and setting the layout.
     */
    @Override
    public void configure() {
        setId("locate-pane");

        tabPane = createTabPane();

        LocateTab locateTab = new LocateTab(eventController);
        locateTab.configure();

        FindAndReplaceTab findAndReplaceTab = new FindAndReplaceTab(eventController);
        findAndReplaceTab.configure();

        addComponents(tabPane, locateTab, findAndReplaceTab);

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        HBox locateInfoPane = createHBoxPane();

        setCenter(tabPane);
        setBottom(locateInfoPane);
    }

    /**
     * Creates and returns a new HBox, configured with default settings.
     *
     * @return The created HBox
     */
    private HBox createHBoxPane() {
        HBox infoPane = new HBox();
        Label label = new Label("");
        label.setId("locate-info-label");
        infoPane.setId("locate-info-pane");
        infoPane.getChildren().add(label);
        infoPane.setPrefHeight(20.0);
        return infoPane;
    }

    /**
     * Creates a new TabPane with custom styling for locate tabs.
     *
     * @return The created TabPane for locate tabs.
     */
    private TabPane createTabPane() {
        TabPane tabPane = new TabPane();
        tabPane.setId("locate-tabpane");
        return tabPane;
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
     * Retrieves the TabPane used in the LocatePane;
     *
     * @return The TabPane used in the LocatePane.
     */
    public TabPane getTabPane() {
        return tabPane;
    }
}
