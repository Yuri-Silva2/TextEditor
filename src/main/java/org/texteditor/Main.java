package org.texteditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.texteditor.controllers.EventController;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.TabController;
import org.texteditor.controllers.TextFileController;
import org.texteditor.enums.Codification;
import org.texteditor.model.TextFile;
import org.texteditor.viewers.pane.AlertPane;
import org.texteditor.viewers.pane.LocatePane;
import org.texteditor.viewers.pane.TextEditorPane;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Main extends Application {

    private static final Map<Double, Double> sizeToWidth = new HashMap<>() {{
        put(22.50, 42.30);
        put(23.75, 44.60);
        put(24.94, 46.90);
        put(26.19, 49.20);
        put(27.50, 51.50);
        put(28.87, 53.80);
        put(30.32, 56.10);
        put(33.43, 60.70);
        put(35.10, 63.00);
        put(36.85, 65.30);
        put(38.70, 67.60);
        put(40.63, 69.90);
    }};

    private static final String APPLICATION_TITLE = "Editor de texto";
    private static final int INITIAL_SCENE_WIDTH = 1320;
    private static final int INITIAL_SCENE_HEIGHT = 860;

    private static TextFileController textFileController;
    private static EventController eventController;
    private FileController fileController;
    private TabController tabController;

    private static Stage primaryStage;
    private static Stage alertPaneStage;
    private static Stage locatePaneStage;

    private static Codification codification;

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
            scene.getStylesheets().add(getMainCSSFile());

            primaryStage.setTitle(APPLICATION_TITLE);
            primaryStage.setScene(scene);

            primaryStage.centerOnScreen();
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.show();

            checkSituation();

            initializeSubPanes();
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
     * Initializes and creates the sub-panes, including the alert pane and locate pane.
     * This method is responsible for setting up the necessary components and configurations
     * to create and display the alert pane and locate pane within the application.
     */
    private void initializeSubPanes() {
        createAlertPane();
        createLocatePane();
    }

    /**
     * Checks the file situation and takes appropriate actions.
     */
    private void checkSituation() {
        changeCodification(Codification.UTF_8);
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
     * Get the CSS File from the resource folder.
     *
     * @return The CSS File.
     */
    private String getMainCSSFile() {
        return Objects.requireNonNull(Main.class.getClassLoader()
                .getResource("style.css")).toExternalForm();
    }

    /**
     * Get the CSS File from the resource folder.
     *
     * @return The CSS File.
     */
    private static String getLocateCSSFile() {
        return Objects.requireNonNull(Main.class.getClassLoader()
                .getResource("locatestyle.css")).toExternalForm();
    }

    /**
     * Creates and configures an alert pane, associating it with the primary stage and event controller.
     * The alert pane is used for displaying alerts and prompts related to user actions.
     */
    private static void createAlertPane() {
        alertPaneStage = new Stage();

        AlertPane alertPane = new AlertPane(primaryStage, alertPaneStage, eventController);
        alertPane.configure();

        Scene scene = new Scene(alertPane, 300, 165);

        alertPaneStage.setScene(scene);
        alertPaneStage.initOwner(primaryStage);
        alertPaneStage.initModality(Modality.APPLICATION_MODAL);
        alertPaneStage.initStyle(StageStyle.UTILITY);
        alertPaneStage.setTitle("Salvar");
    }

    /**
     * Creates and configures a locate pane, associating it with the event controller.
     * The locate pane is used for displaying options related to locating items or information.
     */
    private static void createLocatePane() {
        locatePaneStage = new Stage();

        LocatePane locatePane = new LocatePane(eventController);
        locatePane.configure();

        Scene scene = new Scene(locatePane, 525, 280);
        scene.getStylesheets().add(getLocateCSSFile());

        locatePaneStage.setScene(scene);
        locatePaneStage.setResizable(false);
        locatePaneStage.setTitle("Localizar");
        locatePaneStage.initOwner(primaryStage);
        locatePaneStage.initStyle(StageStyle.UTILITY);

        locatePane.getTabPane().getSelectionModel().selectedItemProperty().addListener(
                (ov, previousTab, nextTab) -> locatePaneStage.setTitle(nextTab.getText())
        );
    }

    /**
     * Displays the configured alert pane stage, making it visible to the user.
     */
    public static void showAlertPane() {
        alertPaneStage.show();
    }

    /**
     * Displays the configured locate pane stage, making it visible to the user.
     */
    public static void showLocatePane() {
        locatePaneStage.show();
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

    /**
     * Finds the preferred width for a VBox based on a given font size.
     * Uses a predefined map of font sizes to widths, and returns the width that corresponds
     * to the largest font size that is less than or equal to the given font size.
     *
     * @param newSize The font size for which to find the corresponding width.
     * @param vBox    The VBox for which to find the preferred width if no match is found in the map.
     * @return The preferred width for the VBox based on the font size.
     */
    public static double findSizeToWidth(double newSize, VBox vBox) {
        return sizeToWidth.entrySet()
                .stream()
                .filter(entry -> newSize >= entry.getKey())
                .map(Map.Entry::getValue)
                .reduce((first, second) -> second)
                .orElse(vBox.getPrefWidth());
    }

    /**
     * Converts text between UTF-8 and ANSI encodings based on the provided {@link Codification}.
     *
     * @param text         The text to convert.
     * @param codification The target {@link Codification}.
     * @return The converted text.
     */
    public static String convertText(String text, Codification codification) {
        return switch (codification) {
            case ANSI -> new String(text.getBytes(StandardCharsets.UTF_8),
                    StandardCharsets.ISO_8859_1);
            case UTF_8 -> new String(text.getBytes(StandardCharsets.UTF_8),
                    StandardCharsets.UTF_8);
        };
    }

    /**
     * Gets the current {@link Codification}.
     *
     * @return The current {@link Codification}.
     */
    public static Codification getCodification() {
        return codification;
    }

    /**
     * Changes the current {@link Codification}.
     *
     * @param codification The new {@link Codification} to set.
     */
    public static void changeCodification(Codification codification) {
        Main.codification = codification;
    }
}