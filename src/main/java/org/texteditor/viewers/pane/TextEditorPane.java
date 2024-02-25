package org.texteditor.viewers.pane;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.texteditor.controllers.EventController;

/**
 * The TextEditorPane class represents the main pane for the text editor application,
 * containing a MenuBar and a WriterPane, and is responsible for configuring the layout.
 */
public class TextEditorPane extends BorderPane {

    private final EventController eventController;

    /**
     * Constructs a new TextEditorPane with the specified event controller.
     *
     * @param eventController The EventController instance to use for handling events.
     */
    public TextEditorPane(EventController eventController) {
        super();
        this.eventController = eventController;
    }

    /**
     * Configures the layout and components of the TextEditorPane.
     * Sets and ID, creates a UtilitiesPane, ScrollBar, WriterPane, and adds them to the BorderPane.
     */
    public void configure() {
        UtilitiesPane utilitiesPane = createUtilitiesPane();
        WriterPane writerPane = createWriterPane();

        HBox infoPane = createHBoxPane();

        setTop(utilitiesPane);
        setCenter(writerPane);
        setBottom(infoPane);
    }

    /**
     * Creates and returns a new HBox, configured with default settings.
     *
     * @return The created HBox
     */
    private HBox createHBoxPane() {
        HBox infoPane = new HBox();
        Label infoLabel = new Label("Paragraph: 0   |   Words: 0   |   Characters:   0");
        infoLabel.setId("info-label");
        infoPane.getChildren().add(infoLabel);
        infoPane.setPrefHeight(18.0);
        return infoPane;
    }

    /**
     * Creates and returns a new WriterPane, configured with default settings.
     *
     * @return The created WriterPane
     */
    private WriterPane createWriterPane() {
        WriterPane writerPane = new WriterPane();
        writerPane.configure();
        return writerPane;
    }

    /**
     * Creates and returns a new UtilitiesPane, configured with default settings.
     *
     * @return The created UtilitiesPane
     */
    private UtilitiesPane createUtilitiesPane() {
        UtilitiesPane utilitiesPane = new UtilitiesPane(eventController);
        utilitiesPane.configure();
        return utilitiesPane;
    }
}
