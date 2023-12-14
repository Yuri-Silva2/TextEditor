package org.texteditor.controller;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BorderController {

    private static final String BORDERPANE_ID = "#texteditor-borderpane";

    private final Stage stage;

    public BorderController(Stage stage) {
        this.stage = stage;
    }

    public BorderPane lookupBorderPane() {
        Scene scene = stage.getScene();
        return (BorderPane) scene.lookup(BORDERPANE_ID);
    }
}
