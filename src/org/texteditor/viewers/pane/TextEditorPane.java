package org.texteditor.viewers.pane;

import javafx.scene.layout.BorderPane;
import org.texteditor.controllers.EventController;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.TabController;
import org.texteditor.controllers.TextFileController;

/**
 * The TextEditorPane class represents the main pane for the text editor application,
 * containing a MenuBar and a WriterPane, and is responsible for configuring the layout.
 */
public class TextEditorPane extends BorderPane {

    private final EventController eventController;

    public TextEditorPane(EventController eventController) {
        super();
        this.eventController = eventController;
    }

    /**
     * Configures the layout and components of the TextEditorPane.
     * Sets and ID, creates a UtilitiesPane and WriterPane, and adds them to the BorderPane.
     */
    public void configure() {
        setId("texteditor-borderpane");

        UtilitiesPane utilitiesPane = createUtilitiesPane();
        WriterPane writerPane = createWriterPane();

        setTop(utilitiesPane);
        setCenter(writerPane);
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

    private UtilitiesPane createUtilitiesPane() {
        UtilitiesPane utilitiesPane = new UtilitiesPane(eventController);
        utilitiesPane.configure();
        return utilitiesPane;
    }
}
