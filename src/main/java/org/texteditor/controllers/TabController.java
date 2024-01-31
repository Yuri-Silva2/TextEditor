package org.texteditor.controllers;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.texteditor.Main;
import org.texteditor.model.TextFile;

/**
 * Controller class for managing tabs in the text editor.
 */
public class TabController {

    private final Stage stage;

    public TabController(Stage stage) {
        this.stage = stage;
    }

    /**
     * Creates a new tab with the specified name, ID, and content.
     *
     * @param textFile The associated TextFile.
     * @param tabName  The name of the tab.
     * @param id       The ID of the tab.
     * @param content  The initial content of the tab.
     * @return The created tab.
     */
    public Tab createNewTab(TextFile textFile, String tabName, String id, String content) {
        Tab tab = new Tab(tabName);
        tab.setId(id);

        TextArea textArea = initializeTextArea(content);
        VBox vBox = createLineNumberVBox(textArea);

        BorderPane borderPane = createBorderPane(textArea, vBox);
        tab.setContent(borderPane);

        defineTabCloseEvent(tab, textFile);

        textArea.textProperty().addListener((obs, oldText, newText) ->
                updateLineNumber(textArea, vBox));

        return tab;
    }

    /**
     * Initializes a new TextArea with the given content.
     *
     * @param content The initial content of the TextArea.
     * @return The initialized TextArea.
     */
    private TextArea initializeTextArea(String content) {
        TextArea textArea = new TextArea(content);
        textArea.setWrapText(true);
        defineKeyTypeEvent(textArea);
        return textArea;
    }

    /**
     * Creates a VBox to display line numbers for the given TextArea.
     *
     * @param textArea The TextArea for which line numbers are displayed.
     * @return The created VBox with line numbers.
     */
    private VBox createLineNumberVBox(TextArea textArea) {
        VBox vBox = new VBox();
        vBox.setPrefWidth(19.0);
        updateLineNumber(textArea, vBox);
        return vBox;
    }

    /**
     * Creates a BorderPane with the given TextArea and VBox.
     *
     * @param textArea The TextArea to be placed in the center.
     * @param vBox     The VBox to be placed on the left.
     * @return The created BorderPane.
     */
    private BorderPane createBorderPane(TextArea textArea, VBox vBox) {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(textArea);
        borderPane.setLeft(vBox);
        return borderPane;
    }

    /**
     * Updates the line numbers displayed in the VBox based on the TextArea content.
     *
     * @param textArea   The TextArea for which line numbers are displayed.
     * @param lineNumber The VBox containing line numbers.
     */
    private void updateLineNumber(TextArea textArea, VBox lineNumber) {
        lineNumber.getChildren().clear();

        int totalLines = textArea.getText().split("\n").length;

        for (int i = 1; i <= totalLines; i++) {
            lineNumber.getChildren().add(new Label("  " + i));
        }
    }

    /**
     * Defines the close event for a tab, prompting the user if the text file is not saved.
     *
     * @param tab      The tab for which the close event is defined.
     * @param textFile The associated TextFile.
     */
    private void defineTabCloseEvent(Tab tab, TextFile textFile) {
        tab.setOnCloseRequest(event -> {
            if (!textFile.saved()) {
                event.consume();
                Main.createAlertPane();
            }
        });
    }

    /**
     * Defines the key type event for a TextArea, updating the information label.
     *
     * @param textArea The TextArea for which the key type event is defined.
     */
    private void defineKeyTypeEvent(TextArea textArea) {
        textArea.setOnKeyTyped(keyEvent -> {
            ObservableList<CharSequence> list = textArea.getParagraphs();
            int par = list.size();
            String[] words = textArea.getText().split("\\s+");
            lookupLabel().setText("Paragraph: " + par + "   |   Words: " + words.length
                    + "   |   Characters: " + textArea.getLength());
        });
    }

    /**
     * Updates the information label with paragraph count, word count, and character count.
     *
     * @param paragraphCount The number of paragraphs.
     * @param wordCount      The number of words.
     * @param charCount      The number of characters.
     */
    private void updateLabel(int paragraphCount, int wordCount, int charCount) {
        Label infoLabel = lookupLabel();
        if (infoLabel == null) return;
        infoLabel.setText("Paragraph: " + paragraphCount + "   |   Words: " + wordCount
                + "   |   Characters: " + charCount);
    }

    /**
     * Looks up and returns the information label from the current scene.
     *
     * @return The information label if found, null otherwise.
     */
    private Label lookupLabel() {
        Scene scene = stage.getScene();
        String LABEL_ID = "#info-label";
        return (Label) scene.lookup(LABEL_ID);
    }

    /**
     * Looks up and returns TabPane from the current scene.
     *
     * @return The TabPane if found, null otherwise.
     */
    public TabPane lookupTabPane() {
        Scene scene = stage.getScene();
        String WRITER_TABPANE_ID = "#writer-tabpane";
        return (TabPane) scene.lookup(WRITER_TABPANE_ID);
    }

    /**
     * Adds a tab to the list of tabs.
     *
     * @param tab     The tab to be added.
     * @param tabPane The TabPane to which the tab will be added.
     */
    public void addTab(Tab tab, TabPane tabPane) {
        tabPane.getTabs().add(tab);
    }

    /**
     * Select and requests focus on its content.
     *
     * @param tab     The tab to be added.
     * @param tabPane The TabPane to which the tab will be added.
     */
    public void selectedAndFocusTab(Tab tab, TabPane tabPane) {
        tabPane.getSelectionModel().select(tab);
        Node contentNode = tab.getContent();
        if (contentNode instanceof TextArea) contentNode.requestFocus();
    }
}