package org.texteditor.viewers.pane;

import javafx.scene.control.TabPane;

/**
 * The WriterPane class extends TabPane and is responsible for managing multiple tabs,
 * each containing distinct text content in the text editor application.
 */
public class WriterPane extends TabPane {

    /**
     * Constructor for WriterPane.
     * Creates a new instance of TabPane.
     */
    public WriterPane() {
        super();
    }

    /**
     * Configures the WriterPane by setting a unique ID.
     * This ID can be used for styling and identification purposes in the application.
     */
    public void configure() {
        setId("writer-tabpane");
    }
}
