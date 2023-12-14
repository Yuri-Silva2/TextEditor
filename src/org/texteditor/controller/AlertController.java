package org.texteditor.controller;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.texteditor.viewer.pane.AlertPane;

public class AlertController {

    private final Stage stage;

    public AlertController(Stage stage) {
        this.stage = stage;
    }

    public void showAlertPane() {
        AlertPane alertPane = new AlertPane();
        alertPane.configure();

        Scene scene = new Scene(alertPane, 300, 180);

        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
