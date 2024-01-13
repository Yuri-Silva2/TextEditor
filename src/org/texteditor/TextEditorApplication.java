package org.texteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.ModelController;
import org.texteditor.controllers.TabController;
import org.texteditor.models.TextFile;
import org.texteditor.viewers.pane.TextEditorPane;

import java.util.Objects;
import java.util.UUID;

import static org.texteditor.TextEditorUtils.createAlertPane;

public class TextEditorApplication extends Application {

    private static final String APPLICATION_TITLE = "Editor de texto";
    private static final int INITIAL_SCENE_WIDTH = 1320;
    private static final int INITIAL_SCENE_HEIGHT = 860;

    private TabController tabController;
    private FileController fileController;

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args Command-line arguments (not used in this case)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Overrides thew star() method of the Application class.
     *
     * @param primaryStage The main stage (window) of the application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            initializeControllers(primaryStage);

            TextEditorPane textEditorPane = new TextEditorPane(tabController,
                    fileController);
            textEditorPane.configure();

            Scene scene = new Scene(textEditorPane, INITIAL_SCENE_WIDTH, INITIAL_SCENE_HEIGHT);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles/style.css")).toExternalForm());
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
        tabController = new TabController(stage);
        fileController = new FileController(stage);
    }

    /**
     * Checks the file situation and takes appropriate actions.
     */
    private void checkSituation() {
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

        ModelController.addTextFile(textFile);

        Tab newTab = tabController.createNewTab(textFile.name(),
                textFile.uuid().toString(), textFile.text());
        configureCloseEvent(newTab, tabPane);

        tabController.addTab(newTab, tabPane);
        tabController.selectedAndFocusTab(newTab, tabPane);
    }

    /**
     * Configures the close event for a Tab, prompting the user to save unsaved changes.
     *
     * @param tab     The tab to configure the close event for
     * @param tabPane The TabPane to get tab
     */
    private void configureCloseEvent(Tab tab, TabPane tabPane) {
        tab.setOnCloseRequest(event -> {
            Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
            String tabId = selectedTab.getId();

            TextFile textFile = ModelController.requestTextFile(tabId);

            if (!textFile.saved()) {
                event.consume();
                showUnsavedChangesAlert();
            }
        });
    }

    /**
     * Displays an alert for unsaved changes.
     */
    private void showUnsavedChangesAlert() {
        createAlertPane(tabController);
    }
}