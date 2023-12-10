package org.texteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.texteditor.controller.TextFileController;
import org.texteditor.model.TextFile;
import org.texteditor.viewer.pane.TextEditorPane;

import java.util.UUID;

public class TextEditorApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Editor de texto");

            TextEditorPane textEditorPane = new TextEditorPane();
            textEditorPane.defineStage(primaryStage);
            textEditorPane.configure();

            Scene scene = new Scene(textEditorPane, 1320, 860);
            primaryStage.setScene(scene);

            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.show();

            TextFileController tfc = new TextFileController();

            if (tfc.thereIsAnUnsavedFile()) {
                TabPane tabPane = tfc.lookupTabpane(primaryStage);

                int number = tabPane.getTabs().size() + 1;
                String name = "Sem título (" + number + ")";

                TextFile textFile = new TextFile(UUID.randomUUID(),
                        name, null, "sasas", false);

                TextFileController.addFile(textFile);
                tfc.createNewTab(textFile, tabPane);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

/*
https://stackoverflow.com/questions/10966776/reading-and-writing-json-file-java
https://stackoverflow.com/questions/15786129/converting-java-objects-to-json-with-jackson
https://github.com/JavaCodeJunkie/javafx/blob/f5d7cd29fb0570846cbdb5f898998f54e758f748/WindowEvents/src/com/javacodejunkie/MainView.java
https://stackoverflow.com/questions/30276935/how-to-get-the-selected-tab-from-the-tabpane-with-javafx#:~:text=As%20with%20many%20JavaFX%20controls,index%20of%20the%20selected%20tab.
https://github.com/JavaCodeJunkie/javafx/blob/master/FileChooserDemo/src/com/javacodejunkie/MainView.java
 */
