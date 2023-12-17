package org.texteditor.viewers.pane;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.ModelController;
import org.texteditor.controllers.TabController;
import org.texteditor.models.TextFile;

import java.io.File;

/**
 * The AlertPane class represents a custom dialog for handling alerts.
 */
public class AlertPane extends BorderPane {

    private final FileController fileController;
    private final TabController tabController;
    private final Stage stage;

    public AlertPane(Stage stage, FileController fileController, TabController tabController) {
        super();
        this.stage = stage;
        this.fileController = fileController;
        this.tabController = tabController;
    }

    /**
     * Configures the layout of the AlertPane.
     */
    public void configure() {
        String fontName = Font.getDefault().getName();

        AnchorPane anchorPaneTop = createTopPane(fontName);
        AnchorPane anchorPaneCenter = createCenterPane(fontName);
        AnchorPane anchorPaneBottom = createBottomPane();

        setTop(anchorPaneTop);
        setCenter(anchorPaneCenter);
        setBottom(anchorPaneBottom);
    }

    /**
     * Creates the top pane with a title label.
     *
     * @param fontName The font name to be used
     * @return The created top AnchorPane
     */
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

    /**
     * Creates the center pane with a message label.
     *
     * @param fontName The font name to be used
     * @return The created center AnchorPane
     */
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

    /**
     * Creates the bottom pane with buttons.
     *
     * @return The created bottom AnchorPane
     */
    private AnchorPane createBottomPane() {
        AnchorPane anchorPaneBottom = new AnchorPane();
        anchorPaneBottom.setPrefWidth(300.0);
        anchorPaneBottom.setPrefHeight(74.0);

        Button saveButton = createButton("Salvar", 14, this::onAlertPaneSaveEvent);
        Button doNotSaveButton = createButton("Não salvar", 114, this::onAlertPaneDoNotSaveEvent);
        Button cancelButton = createButton("Cancelar", 214, this::onAlertPaneCancelEvent);

        anchorPaneBottom.getChildren().addAll(saveButton, doNotSaveButton, cancelButton);

        return anchorPaneBottom;
    }

    /**
     * Handles the cancel event.
     */
    private void onAlertPaneCancelEvent() {
        stage.close();
    }

    /**
     * Handles the "Do Not Save" event.
     */
    private void onAlertPaneDoNotSaveEvent() {
        TabPane tabPane = tabController.lookupTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        stage.close();

        if (selectedTab.getTabPane().getTabs().size() == 1) {
            Platform.exit();

        } else {
            selectedTab.getTabPane().getTabs().remove(selectedTab);
        }
    }

    /**
     * Handles the save event.
     */
    private void onAlertPaneSaveEvent() {
        File selectedFile = fileController.createFileChooserAndSaveFile(
                "Salvar arquivo");

        if (selectedFile == null) return;

        TabPane tabPane = tabController.lookupTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        TextArea textArea = (TextArea) selectedTab.getContent();
        String tabId = selectedTab.getId();

        TextFile textFile = ModelController.requestTextFile(tabId);

        if (!textFile.saved()) {
            ModelController.updateTextFile(tabId, selectedFile.getPath(),
                    textArea.getText());

            fileController.writeFile(selectedFile.getPath(),
                    textArea.getText());
        }

        stage.close();

        if (selectedTab.getTabPane().getTabs().size() == 1) {
            Platform.exit();

        } else {
            selectedTab.getTabPane().getTabs().remove(selectedTab);
        }
    }

    /**
     * Creates a button with specified text, layoutX, and event handler.
     *
     * @param text          The text for the button
     * @param layoutX       The layout X position
     * @param eventHandler  The event handler for the button
     * @return              The created button
     */
    private Button createButton(String text, double layoutX, Runnable eventHandler) {
        Button button = new Button(text);
        button.setLayoutX(layoutX);
        button.setLayoutY(27);
        button.setPrefWidth(72);
        button.setPrefHeight(25);
        button.setOnMouseClicked(mouseEvent -> eventHandler.run());
        return button;
    }
}
