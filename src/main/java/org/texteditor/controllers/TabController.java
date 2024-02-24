package org.texteditor.controllers;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.texteditor.Main;
import org.texteditor.enums.Codification;
import org.texteditor.model.TextFile;

/**
 * Controller class for managing tabs in the text editor.
 */
public class TabController {

    private final Stage stage;

    private VBox vBox;

    boolean updatingText = false;

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

        defineScrollEvent(borderPane, textArea, vBox);

        tab.setContent(borderPane);

        defineTabCloseEvent(tab, textFile);

        textArea.textProperty().addListener((obs, oldText, newText) -> {
            updateLineNumber(textArea, vBox);

            Codification codification = Main.getCodification();

            String convertedText = Main.convertText(newText, codification);

            if (!convertedText.equals(textArea.getText())) {
                if (convertedText.length() <= textArea.getLength()) {
                    textArea.replaceText(0, textArea.getLength(), convertedText);
                } else {
                    textArea.replaceText(0, textArea.getLength(), convertedText.substring(0, textArea.getLength()));
                }
            }
        });

        this.vBox = vBox;

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
        vBox.setPrefWidth(40.0);
        vBox.setId("line-box");
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
     * Handles the paste event, updating line numbers and label after pasting.
     *
     * @param textArea The TextArea for which the paste event is handled.
     */
    private void handlePaste(TextArea textArea) {
        Platform.runLater(() -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            if (clipboard.hasContent(DataFormat.PLAIN_TEXT)) {
                updateLineNumber(textArea, this.vBox);
                ObservableList<CharSequence> list = textArea.getParagraphs();
                int par = list.size();
                String[] words = textArea.getText().split("\\s+");
                updateLabel(par, words.length, textArea.getLength());
            }
        });
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
            lineNumber.getChildren().add(new Label("    " + i));
        }
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

    /**
     * Defines the key type event for a TextArea, updating the information label.
     *
     * @param textArea The TextArea for which the key type event is defined.
     */
    private void defineKeyTypeEvent(TextArea textArea) {
        textArea.setOnKeyTyped(keyEvent -> {
            ObservableList<CharSequence> list = textArea.getParagraphs();
            int paragraphCount = list.size();
            String[] words = textArea.getText().split("\\s+");
            updateLabel(paragraphCount, words.length, textArea.getLength());
        });

        textArea.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if ((keyEvent.isShortcutDown() && keyEvent.getCode() == KeyCode.V) ||
                    (keyEvent.isMetaDown() && keyEvent.getCode() == KeyCode.V)) {
                handlePaste(textArea);
            }
        });
    }

    /**
     * Defines the scroll event for the given BorderPane, TextArea, and VBox.
     * This event adjusts the font size of the TextArea and the VBox width
     * based on the scroll direction.
     *
     * @param borderPane The BorderPane to which the scroll event is applied.
     * @param textArea   The TextArea whose font size will be adjusted.
     * @param vBox       The VBox whose width will be adjusted based on font size.
     */
    private void defineScrollEvent(BorderPane borderPane, TextArea textArea, VBox vBox) {
        borderPane.setOnScroll(event -> {
            double deltaY = event.getDeltaY();
            Font font = textArea.getFont();
            double newSize = font.getSize();

            if (deltaY < 0) newSize /= 1.05;
            else newSize *= 1.05;

            vBox.setPrefWidth(Main.findSizeToWidth(newSize, vBox));

            textArea.setStyle("-fx-font-size: " + newSize + "px;");
            vBox.setStyle("-fx-font-size: " + newSize + "px;");
        });
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
                Main.showAlertPane();
            }
        });
    }
}