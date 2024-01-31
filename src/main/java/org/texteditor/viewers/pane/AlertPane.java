package org.texteditor.viewers.pane;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.texteditor.controllers.EventController;

/**
 * The AlertPane class represents a custom dialog for handling alerts.
 */
public class AlertPane extends BorderPane implements CustomPane {

    private final EventController eventController;

    private final Stage cStage;

    private final Stage stage;

    public AlertPane(Stage cStage, Stage stage, EventController eventController) {
        super();
        this.cStage = cStage;
        this.stage = stage;
        this.eventController = eventController;
    }

    /**
     * Configures the layout of the AlertPane.
     */
    @Override
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

        String WRITER_TABPANE_ID = "#writer-tabpane";
        TabPane tabPane = (TabPane) cStage.getScene().lookup(WRITER_TABPANE_ID);

        Button saveButton = createSaveButton(tabPane);
        Button doNotSaveButton = createDoNotSaveButton(tabPane);
        Button cancelButton = createCancelButton(tabPane);

        anchorPaneBottom.getChildren().addAll(saveButton, doNotSaveButton, cancelButton);

        return anchorPaneBottom;
    }

    /**
     * Creates and returns a "Save" button for an alert pane.
     *
     * @param tabPane The TabPane associated with the alert pane.
     * @return A Button with the label "Salvar" (Save) and event handler
     * set to invoke the onAlertPaneSaveEvent method.
     */
    private Button createSaveButton(TabPane tabPane) {
        Button button = new Button("Salvar");
        button.setLayoutX(14);
        button.setLayoutY(27);
        button.setPrefWidth(72);
        button.setPrefHeight(25);
        button.setOnMouseClicked(event -> {
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            if (selectedTab == null) return;

            boolean saveFile = eventController.saveFileInternal();

            stage.close();

            if (selectedTab.getTabPane().getTabs().size() == 1)
                Platform.exit();
            else if (saveFile)
                selectedTab.getTabPane().getTabs().remove(selectedTab);
        });
        return button;
    }

    /**
     * Creates and returns a "Do Not Save" button for an alert pane.
     *
     * @param tabPane The TabPane associated with the alert pane.
     * @return A Button with the label "Não salvar" (Do Not Save) and event handler
     * set to invoke the onAlertPaneDoNotSaveEvent method.
     */
    private Button createDoNotSaveButton(TabPane tabPane) {
        Button button = new Button("Não salvar");
        button.setLayoutX(114);
        button.setLayoutY(27);
        button.setPrefWidth(72);
        button.setPrefHeight(25);
        button.setOnMouseClicked(event -> {
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

            stage.close();

            if (selectedTab.getTabPane().getTabs().size() == 1)
                Platform.exit();
            else
                selectedTab.getTabPane().getTabs().remove(selectedTab);
        });
        return button;
    }

    /**
     * Creates and returns a "Cancel" button for an alert pane.
     *
     * @param tabPane The TabPane associated with the alert pane.
     * @return A Button with the label "Cancelar" (Cancel) and event handler
     * set to invoke the onAlertPaneCancelEvent method.
     */
    private Button createCancelButton(TabPane tabPane) {
        Button button = new Button("Cancelar");
        button.setLayoutX(214);
        button.setLayoutY(27);
        button.setPrefWidth(72);
        button.setPrefHeight(25);
        button.setOnMouseClicked(event -> stage.close());
        return button;
    }
}
