package org.texteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.texteditor.controllers.EventController;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.TabController;
import org.texteditor.controllers.TextFileController;
import org.texteditor.model.TextFile;
import org.texteditor.viewers.pane.AlertPane;
import org.texteditor.viewers.pane.TextEditorPane;

import java.io.InputStream;
import java.util.UUID;

public class Main extends Application {

    private static final String APPLICATION_TITLE = "Editor de texto";
    private static final int INITIAL_SCENE_WIDTH = 1320;
    private static final int INITIAL_SCENE_HEIGHT = 860;

    private static TextFileController textFileController;
    private static EventController eventController;
    private FileController fileController;
    private TabController tabController;

    private static Stage primaryStage;

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args Command-line arguments (not used in this case)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Overrides the start() method of the Application class.
     *
     * @param primaryStage The main stage (window) of the application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Main.primaryStage = primaryStage;
            initializeControllers(primaryStage);

            TextEditorPane textEditorPane = new TextEditorPane(eventController);
            textEditorPane.configure();

            Scene scene = new Scene(textEditorPane, INITIAL_SCENE_WIDTH, INITIAL_SCENE_HEIGHT);

            primaryStage.setTitle(APPLICATION_TITLE);
            primaryStage.setScene(scene);

            primaryStage.centerOnScreen();
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.show();

            checkSituation();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the controllers used by the application.
     *
     * @param stage The main stage of the application
     */
    private void initializeControllers(Stage stage) {
        textFileController = new TextFileController();
        tabController = new TabController(stage);
        fileController = new FileController(stage);
        eventController = new EventController(textFileController,
                tabController, fileController);
    }

    /**
     * Checks the file situation and takes appropriate actions.
     */
    private void checkSituation() {
        fileController.createDefaultFolder();
        if (fileController.thereIsAnUnsavedFile())
            addNewTypingArea();
    }

    /**
     * Adds a new typing area (tab) to the TabPane.
     */
    private void addNewTypingArea() {
        TabPane tabPane = tabController.lookupTabPane();

        int number = tabPane.getTabs().size() + 1;
        String name = "Sem título (" + number + ")";

        TextFile textFile = new TextFile(UUID.randomUUID(),
                name, null, "", false);

        textFileController.addTextFile(textFile);

        Tab newTab = tabController.createNewTab(textFile, textFile.name(),
                textFile.uuid().toString(), textFile.text());

        tabController.addTab(newTab, tabPane);
        tabController.selectedAndFocusTab(newTab, tabPane);
    }

    /**
     * Creates an alert pane for handling specific situations.
     */
    public static void createAlertPane() {
        Stage newStage = new Stage();

        AlertPane alertPane = new AlertPane(primaryStage, newStage, eventController);
        alertPane.configure();

        Scene scene = new Scene(alertPane, 300, 165);

        newStage.setScene(scene);
        newStage.initOwner(primaryStage);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.initStyle(StageStyle.UTILITY);
        newStage.setTitle("Salvar");
        newStage.show();
    }

    /**
     * Creates an ImageView for an icon based on the specified FileName.
     *
     * @param fileName The FileName of the icon image
     * @return ImageView for the specified icon
     */
    public static ImageView createIcon(String fileName) {
        try {
            InputStream input = Main.class.getClassLoader().getResourceAsStream(fileName);

            if (input == null) return new ImageView();

            Image icon = new Image(input);
            ImageView iconView = new ImageView(icon);

            iconView.setFitHeight(17.0);
            iconView.setFitWidth(17.0);

            return iconView;

        } catch (Exception e) {
            return new ImageView();
        }
    }
}