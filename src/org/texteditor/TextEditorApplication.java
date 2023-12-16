package org.texteditor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.texteditor.controller.FileController;
import org.texteditor.controller.ModelController;
import org.texteditor.controller.TabController;
import org.texteditor.model.TextFile;
import org.texteditor.viewer.pane.AlertPane;
import org.texteditor.viewer.pane.TextEditorPane;

import java.util.UUID;

public class TextEditorApplication extends Application {

    private static final String APPLICATION_TITLE = "Editor de texto";
    private static final int INITIAL_SCENE_WIDTH = 1320;
    private static final int INITIAL_SCENE_HEIGHT = 860;

    private TabController tabController;
    private FileController fileController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle(APPLICATION_TITLE);

            tabController = new TabController(primaryStage);
            fileController = new FileController(primaryStage);

            TextEditorPane textEditorPane = new TextEditorPane(tabController,
                    fileController);
            textEditorPane.configure();

            Scene scene = new Scene(textEditorPane, INITIAL_SCENE_WIDTH, INITIAL_SCENE_HEIGHT);
            scene.getStylesheets().add("style.css");
            primaryStage.setScene(scene);

            primaryStage.centerOnScreen();
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.show();

            checkSituation(primaryStage);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void checkSituation(Stage stage) {
        if (fileController.thereIsAnUnsavedFile()) {
            addNewTypingArea(stage);
        }
    }

    private void addNewTypingArea(Stage stage) {
        TabPane tabPane = tabController.lookupTabPane();

        int number = tabPane.getTabs().size() + 1;
        String name = "Sem título (" + number + ")";

        TextFile textFile = new TextFile(UUID.randomUUID(),
                name, null, "", false);

        ModelController.addTextFile(textFile);

        Tab newTab = tabController.createNewTab(textFile.name(),
                textFile.uuid().toString(), textFile.text());

        newTab.setOnCloseRequest(event -> {
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            String tabId = selectedTab.getId();

            TextFile tf = ModelController.requestTextFile(tabId);

            if (!tf.saved()) {
                event.consume();

                Stage newStage = new Stage();

                AlertPane alertPane = new AlertPane(newStage,
                        new FileController(newStage),
                        tabController);
                alertPane.configure();

                Scene scene = new Scene(alertPane, 300, 180);

                newStage.setScene(scene);
                newStage.initStyle(StageStyle.UNDECORATED);
                newStage.show();
            }
        });

        tabController.addTabInListAndRequestFocus(newTab, tabPane);
    }
}