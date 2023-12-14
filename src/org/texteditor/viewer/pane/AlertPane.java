package org.texteditor.viewer.pane;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AlertPane extends BorderPane {

    public AlertPane() {
        super();
    }

    public void configure() {
        String fontName = Font.getDefault().getName();

        AnchorPane anchorPaneTop = createTopPane(fontName);
        AnchorPane anchorPaneCenter = createCenterPane(fontName);
        AnchorPane anchorPaneBottom = createBottomPane();

        setTop(anchorPaneTop);
        setCenter(anchorPaneCenter);
        setBottom(anchorPaneBottom);
    }

    private AnchorPane createTopPane(String fontName) {
        AnchorPane anchorPaneTop = new AnchorPane();
        anchorPaneTop.setPrefWidth(300.0);
        anchorPaneTop.setPrefHeight(45.0);

        Text titleLabel = new Text("Editor de texto");
        titleLabel.setFont(Font.font(fontName, FontWeight.BOLD, 13));
        titleLabel.setLayoutX(14);
        titleLabel.setLayoutY(29);

        anchorPaneTop.getChildren().add(titleLabel);

        return anchorPaneTop;
    }

    private AnchorPane createCenterPane(String fontName) {
        AnchorPane anchorPaneCenter = new AnchorPane();
        anchorPaneCenter.setPrefWidth(300.0);
        anchorPaneCenter.setPrefHeight(61.0);

        Text messageLabel = new Text("Deseja salvar as alterações?");
        messageLabel.setFont(Font.font(fontName, FontWeight.NORMAL, 13));
        messageLabel.setLayoutX(71);
        messageLabel.setLayoutY(33);

        anchorPaneCenter.getChildren().add(messageLabel);

        return anchorPaneCenter;
    }

    private AnchorPane createBottomPane() {
        AnchorPane anchorPaneBottom = new AnchorPane();
        anchorPaneBottom.setPrefWidth(300.0);
        anchorPaneBottom.setPrefHeight(74.0);

        Button saveButton = createButton("Salvar", 14);
        Button doNotSaveButton = createButton("Não salvar", 114);
        Button cancelButton = createButton("Cancelar", 214);
        onAlertPaneSaveEvent(saveButton);
        onAlertPaneDoNotSaveEvent(doNotSaveButton);
        onAlertPaneCancelEvent(cancelButton);

        anchorPaneBottom.getChildren().addAll(saveButton, doNotSaveButton, cancelButton);

        return anchorPaneBottom;
    }

    private Button createButton(String text, double layoutX) {
        Button button = new Button(text);
        button.setLayoutX(layoutX);
        button.setLayoutY(27);
        button.setPrefWidth(72);
        button.setPrefHeight(25);
        return button;
    }

    private void onAlertPaneSaveEvent(Button button) {

    }

    private void onAlertPaneDoNotSaveEvent(Button button) {

    }

    private void onAlertPaneCancelEvent(Button button) {

    }
}
