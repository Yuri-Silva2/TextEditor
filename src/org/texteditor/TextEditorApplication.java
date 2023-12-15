package org.texteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.texteditor.controller.*;
import org.texteditor.model.TextFile;
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
            primaryStage.setScene(scene);

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

        tabController.addTabInListAndRequestFocus(newTab, tabPane);
    }
}

/*
https://stackoverflow.com/questions/10966776/reading-and-writing-json-file-java
https://stackoverflow.com/questions/15786129/converting-java-objects-to-json-with-jackson
https://github.com/JavaCodeJunkie/javafx/blob/f5d7cd29fb0570846cbdb5f898998f54e758f748/WindowEvents/src/com/javacodejunkie/MainView.java
https://stackoverflow.com/questions/30276935/how-to-get-the-selected-tab-from-the-tabpane-with-javafx#:~:text=As%20with%20many%20JavaFX%20controls,index%20of%20the%20selected%20tab.
https://github.com/JavaCodeJunkie/javafx/blob/master/FileChooserDemo/src/com/javacodejunkie/MainView.java
https://docs.oracle.com/javafx/2/events/filters.htm
 */
