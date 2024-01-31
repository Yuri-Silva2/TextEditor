package org.texteditor.viewers.pane;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.texteditor.controllers.EventController;

public class LocatePane extends BorderPane implements CustomPane {

    private final EventController eventController;

    private final Stage stage;

    public LocatePane(Stage stage, EventController eventController) {
        super();
        this.stage = stage;
        this.eventController = eventController;
    }

    @Override
    public void configure() {

    }

    private void createLocateTab() {

    }

    private void createFindAndReplaceTab() {

    }

    private void createFindInFiles() {

    }
}
