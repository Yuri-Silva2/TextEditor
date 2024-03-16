package org.texteditor.controllers;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.lang3.StringUtils;
import org.texteditor.Main;
import org.texteditor.model.TextFile;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The EventController class handles various events in the text editor application.
 */
public class EventController {

    private final TextFileController textFileController;
    private final TabController tabController;
    private final FileController fileController;

    /**
     * Constructs an EventController with necessary controllers.
     *
     * @param textFileController The TextFileController for managing text files.
     * @param tabController      The TabController for managing tabs.
     * @param fileController     The FileController for managing file operations.
     */
    public EventController(TextFileController textFileController, TabController tabController,
                           FileController fileController) {
        this.textFileController = textFileController;
        this.tabController = tabController;
        this.fileController = fileController;
    }

    /**
     * Handles the vent when the user wants to undo the last action in the text area.
     */
    public void onUndoEvent() {
        handleUndoRedoEvent(true);
    }

    /**
     * Handles the event when the user wants to redo the last undone action in the text area.
     */
    public void onRemakeEvent() {
        handleUndoRedoEvent(false);
    }

    /**
     * Handles undo or redo events based on the specified boolean parameter.
     *
     * @param isUndo A boolean indicating whether to perform an undo operation (true) or redo operation (false).
     */
    private void handleUndoRedoEvent(boolean isUndo) {
        Tab selectedTab = getCurrentSelectTab();
        if (selectedTab == null) return;

        BorderPane borderPane = (BorderPane) selectedTab.getContent();
        TextArea textArea = (TextArea) borderPane.getCenter();

        if (isUndo)
            textArea.undo();
        else
            textArea.redo();
    }

    /**
     * Cuts or copies the selected text from the current tab's TextArea to the system clipboard.
     *
     * @param isCutOperation A boolean indicating whether to perform a cut operation (true) or copy operation (false).
     */
    private void cutOrCopyToClipboard(boolean isCutOperation) {
        Tab selectedTab = getCurrentSelectTab();
        if (selectedTab == null) return;

        BorderPane borderPane = (BorderPane) selectedTab.getContent();
        TextArea textArea = (TextArea) borderPane.getCenter();
        String selectedText = textArea.getSelectedText();

        if (isCutOperation)
            textArea.setText(textArea.getText().replace(selectedText, ""));

        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(selectedText);
        clipboard.setContents(stringSelection, stringSelection);
    }

    /**
     * Handles the event when the user wants to cut the selected text and copy it to the clipboard.
     */
    public void onCutEvent() {
        cutOrCopyToClipboard(true);
    }

    /**
     * Handles the event when the user wants to copy the selected text to the clipboard.
     */
    public void onCopyEvent() {
        cutOrCopyToClipboard(false);
    }

    /**
     * Handles the event when the user wants to paste text from the clipboard into the text area.
     */
    public void onPasteEvent() {
        Clipboard clipboard = Clipboard.getSystemClipboard();

        Tab selectedTab = getCurrentSelectTab();
        if (selectedTab == null) return;

        BorderPane borderPane = (BorderPane) selectedTab.getContent();
        TextArea textArea = (TextArea) borderPane.getCenter();

        if (!clipboard.hasString()) return;

        String clipboardText = clipboard.getString();
        String text = textArea.getText();
        int position = textArea.getCaretPosition();

        StringBuilder stringBuilder = new StringBuilder(text);
        stringBuilder.insert(position, clipboardText);

        textArea.setText(stringBuilder.toString());
        textArea.positionCaret(position);
    }

    /**
     * Handles the event when the user wants to delete the selected text in the text area.
     */
    public void onDeleteEvent() {
        Tab selectedTab = getCurrentSelectTab();
        if (selectedTab == null) return;

        BorderPane borderPane = (BorderPane) selectedTab.getContent();
        TextArea textArea = (TextArea) borderPane.getCenter();

        String selectedText = textArea.getSelectedText();

        textArea.setText(textArea.getText().replace(selectedText, ""));
    }

    /**
     * Handles the event when the user wants to select all text in the text area.
     */
    public void onSelectAllEvent() {
        Tab selectedTab = getCurrentSelectTab();

        if (selectedTab == null) return;

        BorderPane borderPane = (BorderPane) selectedTab.getContent();
        ((TextArea) borderPane.getCenter()).selectAll();
    }

    /**
     * Returns the currently selected tab in the TabPane.
     *
     * @return The currently selected tab or null if none is selected.
     */
    private Tab getCurrentSelectTab() {
        TabPane tabPane = tabController.lookupTabPane();
        return tabPane.getSelectionModel().getSelectedItem();
    }

    /**
     * Handles the event when the user attempts to quit the application.
     * Exits the application by calling Platform.exit().
     */
    public void onQuitEvent() {
        Platform.exit();
    }

    /**
     * Handles the event when the user wants to save all open tabs.
     * Iterates through all tabs in the TabPane and saves each file using saveFile method.
     */
    public void onSaveAllEvent() {
        TabPane tabPane = tabController.lookupTabPane();
        tabPane.getTabs().forEach(tab -> {
            if (tab != null)
                saveFile(tab);
        });
    }

    /**
     * Handles the event when the user wants to save the currently selected tab with a new name.
     * Checks if there is a selected tab in the TabPane and calls saveFileAs method for the selected tab.
     */
    public void onSaveAsEvent() {
        TabPane tabPane = tabController.lookupTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab == null) return;

        saveFileAs(selectedTab);
    }

    /**
     * Handles the event when the user wants to save the currently selected tab.
     * Checks if there is a selected tab in the TabPane and calls saveFile method for the selected tab.
     */
    public void onSaveEvent() {
        TabPane tabPane = tabController.lookupTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab == null) return;

        saveFile(selectedTab);
    }

    /**
     * Handles the event when the user wants to open a file.
     * Opens a file dialog to let the user chooser a file, then calls openFile method with the selected file.
     */
    public void onOpenEvent() {
        File selectedFile = fileController.createFileChooserAndGetFile(
                "Selecionar Arquivo");

        if (selectedFile != null)
            openFile(selectedFile);
    }

    /**
     * Handles the event when the user wants to create a new tab.
     * Creates a new tab in the TabPane with a default name and index, then calls createNewTav method.
     */
    public void onNewTabEvent() {
        TabPane tabPane = tabController.lookupTabPane();

        int numberOfTabs, numberPage;
        numberOfTabs = tabPane.getTabs().size() - 1;

        numberPage = numberOfTabs + 1;

        String name = "Sem título (" + numberPage + ")";

        createNewTab(name);
    }

    /**
     * Opens the specified file, creates a new tab, and displays its content.
     *
     * @param selectedFile File to be opened
     */
    private void openFile(File selectedFile) {
        String content = fileController.readFile(selectedFile);
        createTab(selectedFile.getName(), selectedFile.getPath(), content);
    }

    /**
     * Saves the content of the specified tab.
     *
     * @param tab Tab to be saved
     */
    private void saveFile(Tab tab) {
        saveFileInternal(tab, false);
    }

    /**
     * Saves the content of the specified tab with a new name or location.
     *
     * @param tab Tab to be saved as
     */
    private void saveFileAs(Tab tab) {
        saveFileInternal(tab, true);
    }

    /**
     * Saves the content of a TextArea associated with a specific Tab.
     *
     * @param tab    The Tab containing the TextArea to be saved.
     * @param saveAs A boolean indicating whether to perform a "Save As" operation.
     */
    private void saveFileInternal(Tab tab, boolean saveAs) {
        BorderPane borderPane = (BorderPane) tab.getContent();
        TextArea textArea = (TextArea) borderPane.getCenter();

        String tabId = tab.getId();

        TextFile textFile = textFileController.requestTextFile(tabId);

        if (textFile.saved() && !saveAs)
            saveExistingFile(textArea, textFile);
        else if (textFile.saved())
            saveFileAsOperation(textArea, tab);
        else if (saveAs)
            saveFileAsOperation(textArea, tab);
    }

    /**
     * Saves changes to an existing TextFile.
     *
     * @param textArea The TextArea containing the modified text.
     * @param textFile The TextFile to be updated.
     */
    private void saveExistingFile(TextArea textArea, TextFile textFile) {
        textFileController.updateTextFile(textFile.uuid().toString(), textArea.getText());
        fileController.writeFile(textFile.filePath(), textArea.getText());
    }

    /**
     * Saves the content of the TextArea to a new file and updates associated components.
     *
     * @param textArea The TextArea component containing the text content.
     * @param tab      The Tab associated with the file being edited.
     */
    private void saveFileAsOperation(TextArea textArea, Tab tab) {
        File selectedFile = fileController.createFileChooserAndSaveFile("Salvar arquivo");

        if (selectedFile == null) return;

        textFileController.updateTextFile(tab.getId(), selectedFile.getPath(), textArea.getText());
        fileController.writeFile(selectedFile.getPath(), textArea.getText());
        tab.setText(selectedFile.getName());
    }

    /**
     * Saves the content of the currently selected tab in the internal application context.
     *
     * @return true if the file is successfully saved, false otherwise.
     */
    public boolean saveFileInternal() {
        TabPane tabPane = tabController.lookupTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        if (selectedTab == null) return false;

        String tabId = selectedTab.getId();

        BorderPane borderPane = (BorderPane) selectedTab.getContent();
        TextArea textArea = (TextArea) borderPane.getCenter();

        TextFile textFile = textFileController.requestTextFile(tabId);

        if (textFile.saved()) {
            textFileController.updateTextFile(textFile.uuid().toString(), textArea.getText());
            fileController.writeFile(textFile.filePath(), textArea.getText());

        } else {
            File selectedFile = fileController.createFileChooserAndSaveFile("Salvar arquivo");

            if (selectedFile == null) return false;

            textFileController.updateTextFile(tabId, selectedFile.getPath(), textArea.getText());
            fileController.writeFile(selectedFile.getPath(), textArea.getText());
        }
        return true;
    }

    /**
     * Creates a new tab with the specified name file path, and content.
     *
     * @param name     Name of the new tab
     * @param filePath File path associated with the new tab
     * @param content  Content to be displayed in the new tab
     */
    private void createTab(String name, String filePath, String content) {
        TextFile textFile = new TextFile(UUID.randomUUID(), name, filePath, content, true);

        textFileController.addTextFile(textFile);

        Tab newTab = tabController.createNewTab(textFile, textFile.name(), textFile.uuid().toString(),
                textFile.text());

        TabPane tabPane = tabController.lookupTabPane();
        tabController.addTab(newTab, tabPane);
        tabController.selectedAndFocusTab(newTab, tabPane);
    }

    /**
     * Creates a new untitled tab and displays it.
     *
     * @param tabName Name to be given to the new tab
     */
    private void createNewTab(String tabName) {
        TextFile textFile = new TextFile(UUID.randomUUID(), tabName, null, "",
                false);
        textFileController.addTextFile(textFile);

        Tab newTab = tabController.createNewTab(textFile, textFile.name(), textFile.uuid().toString(),
                textFile.text());

        TabPane tabPane = tabController.lookupTabPane();
        tabController.addTab(newTab, tabPane);
        tabController.selectedAndFocusTab(newTab, tabPane);
    }

    public void onUpdateEvent() {

    }

    /**
     * Triggered when a find event occurs. Shows the find pane.
     */
    public void onFindEvent() {
        Main.showFindPane();
    }

    /**
     * Triggered when a find nearby event occurs. Shows the find pane.
     */
    public void onFindNearbyEvent() {
        Main.showFindPane();
    }

    /**
     * Triggered when a find and replace event occurs. Shows the find pane.
     */
    public void onFindAndReplaceEvent() {
        Main.showFindPane();
    }

    /**
     * Increases the font size of the text area and adjusts the width of the VBox to fit the new font size.
     */
    public void onEnlargeEvent() {
        BorderPane borderPane = getCurrentBorderPane();
        if (borderPane == null) return;

        VBox vBox = (VBox) borderPane.getLeft();
        TextArea textArea = (TextArea) borderPane.getCenter();

        Font font = textArea.getFont();
        double newSize = font.getSize();

        newSize = newSize *= 1.05;

        vBox.setPrefWidth(Main.findSizeToWidth(newSize, vBox));

        textArea.setStyle("-fx-font-size: " + newSize + "px;");
        vBox.setStyle("-fx-font-size: " + newSize + "px;");
    }

    /**
     * Decreases the font size of the text area and adjusts the width of the VBox to fit the new font size.
     */
    public void onReduceEvent() {
        BorderPane borderPane = getCurrentBorderPane();
        if (borderPane == null) return;

        VBox vBox = (VBox) borderPane.getLeft();
        TextArea textArea = (TextArea) borderPane.getCenter();

        Font font = textArea.getFont();
        double newSize = font.getSize();

        newSize = newSize /= 1.05;

        vBox.setPrefWidth(Main.findSizeToWidth(newSize, vBox));

        textArea.setStyle("-fx-font-size: " + newSize + "px;");
        vBox.setStyle("-fx-font-size: " + newSize + "px;");
    }

    /**
     * Restores the font size of the text area and the width of the VBox to their default values.
     */
    public void onRestoreDefaultZoomEvent() {
        BorderPane borderPane = getCurrentBorderPane();
        if (borderPane == null) return;

        VBox vBox = (VBox) borderPane.getLeft();
        TextArea textArea = (TextArea) borderPane.getCenter();

        vBox.setPrefWidth(40.0);

        textArea.setStyle("-fx-font-size: 12px;");
        vBox.setStyle("-fx-font-size: 12px;");
    }

    /**
     * Handles the event when the "Find" button is clicked.
     *
     * @param findTextId       The ID of the text to find.
     * @param matchWholeWordId The ID of the checkbox indicating whether to match whole words.
     * @param caseSensitiveId  The ID of the checkbox indicating whether the search is case-sensitive.
     */
    public void onNextFindButtonEvent(String findTextId, String matchWholeWordId, String caseSensitiveId) {
        searchWord(findTextId, "", matchWholeWordId, caseSensitiveId, false, false);
    }

    /**
     * Handles the event when the "Find and Replace" button is clicked.
     *
     * @param findTextId       The ID of the text to find.
     * @param replaceTextId    The ID of the text to replace.
     * @param matchWholeWordId The ID of the checkbox indicating whether to match whole words.
     * @param caseSensitiveId  The ID of the checkbox indicating whether the search is case-sensitive.
     */
    public void onFindAndReplaceButtonEvent(String findTextId, String replaceTextId, String matchWholeWordId, String caseSensitiveId) {
        searchWord(findTextId, replaceTextId, matchWholeWordId, caseSensitiveId, false, true);
    }

    /**
     * Handles the event when the "Find and Replace All" button is clicked.
     *
     * @param findTextId       The ID of the text to find.
     * @param replaceTextId    The ID of the text to replace.
     * @param matchWholeWordId The ID of the checkbox indicating whether to match whole words.
     * @param caseSensitiveId  The ID of the checkbox indicating whether the search is case-sensitive.
     */
    public void onFindAndReplaceAllButtonEvent(String findTextId, String replaceTextId, String matchWholeWordId, String caseSensitiveId) {
        searchWord(findTextId, replaceTextId, matchWholeWordId, caseSensitiveId, true, true);
    }

    /**
     * Searches for the specified word in the text area and optionally replaces it.
     *
     * @param findTextId          The ID of the text to find.
     * @param replaceTextId       The ID of the text to replace.
     * @param matchWholeWordId    The ID of the checkbox indicating whether to match whole words.
     * @param caseSensitiveId     The ID of the checkbox indicating whether the search is case-sensitive.
     * @param searchAndReplaceAll Indicates whether to search and replace all occurrences.
     * @param searchAndReplace    Indicates whether to perform a search and replace operation.
     */
    private void searchWord(String findTextId, String replaceTextId, String matchWholeWordId, String caseSensitiveId, boolean searchAndReplaceAll, boolean searchAndReplace) {
        Stage findStage = getFindStage();
        if (findStage == null) return;

        TextArea textArea = getCurrentTextArea();
        if (textArea == null) return;

        int caretPosition = textArea.getCaretPosition();

        String findText = getFindText(findStage, findTextId);
        String replaceText = "";

        if (searchAndReplace) replaceText = getReplaceText(findStage, replaceTextId);

        boolean matchWholeWord = isMatchWholeWord(findStage, matchWholeWordId);
        boolean caseSensitive = isCaseSensitiveSearchRequested(findStage, caseSensitiveId);

        if (!searchAndReplaceAll)
            performSearch(textArea, findText, replaceText, caretPosition, caseSensitive, matchWholeWord);
        else
            performSearch(textArea, findText, replaceText, caseSensitive, matchWholeWord);
    }

    /**
     * Handles the event when the "Count" button is clicked to count occurrences of a word.
     *
     * @param findTextId       The ID of the text to find.
     * @param matchWholeWordId The ID of the checkbox indicating whether to match whole words.
     * @param caseSensitiveId  The ID of the checkbox indicating whether the search is case-sensitive.
     */
    public void onCountEvent(String findTextId, String matchWholeWordId, String caseSensitiveId) {
        Stage findStage = getFindStage();
        if (findStage == null) return;

        Label label = getFindLabel(findStage);
        TextArea textArea = getCurrentTextArea();
        if (textArea == null) return;

        String text = textArea.getText();
        String findText = getFindText(findStage, findTextId);

        if (findText.isEmpty()) {
            label.setText("");
            return;
        }

        boolean matchWholeWord = isMatchWholeWord(findStage, matchWholeWordId);
        boolean caseSensitive = isCaseSensitiveSearchRequested(findStage, caseSensitiveId);

        int count = performSearch(text, findText, caseSensitive, matchWholeWord);

        label.setText("Total: " + count + " correspondência no arquivo.");
    }

    /**
     * Performs a search and optional replacement operation on the given text.
     *
     * @param textArea       The text area in which to perform the search and replacement.
     * @param findText       The text to find.
     * @param replaceText    The text to replace if a match is found.
     * @param caseSensitive  Indicates whether the search is case-sensitive.
     * @param matchWholeWord Indicates whether to match whole words only.
     */
    private void performSearch(TextArea textArea, String findText, String replaceText, boolean caseSensitive, boolean matchWholeWord) {
        if (textArea == null || findText == null || findText.isEmpty()) {
            return;
        }

        String text = textArea.getText();
        String[] splitText = text.split("\\s");

        for (int i = 0; i < splitText.length; i++) {
            splitText[i] = replaceIfMatch(splitText[i], findText, replaceText, caseSensitive, matchWholeWord);
        }

        String newText = String.join(" ", splitText);
        textArea.setText(newText);
    }

    /**
     * Replaces a word if it matches the specified criteria.
     *
     * @param word           The word to check for a match.
     * @param findText       The text to find.
     * @param replaceText    The text to replace if a match is found.
     * @param caseSensitive  Indicates whether the comparison should be case-sensitive.
     * @param matchWholeWord Indicates whether to match whole words only.
     * @return The original word if no match is found, or the replacement text if a match is found.
     */
    private String replaceIfMatch(String word, String findText, String replaceText, boolean caseSensitive, boolean matchWholeWord) {
        if (caseSensitive && matchWholeWord && word.equals(findText))
            return replaceText;

        if (caseSensitive && !matchWholeWord && StringUtils.contains(word, findText))
            return replaceText;

        if (!caseSensitive && matchWholeWord && word.equalsIgnoreCase(findText))
            return replaceText;

        if (!caseSensitive && !matchWholeWord && StringUtils.containsIgnoreCase(word, findText))
            return replaceText;

        return word;
    }

    /**
     * Performs a search for the specified word in the text area and optionally replaces it.
     *
     * @param textArea       The text area in which to perform the search and replacement.
     * @param findText       The text to find.
     * @param replaceText    The text to replace if a match is found.
     * @param caretPosition  The position of the caret in the text area.
     * @param caseSensitive  Indicates whether the search is case-sensitive.
     * @param matchWholeWord Indicates whether to match whole words only.
     */
    private void performSearch(TextArea textArea, String findText, String replaceText, int caretPosition, boolean caseSensitive, boolean matchWholeWord) {
        String text = textArea.getText(caretPosition, textArea.getText().length());
        Pattern regex = createRegexPattern(findText, caseSensitive, matchWholeWord);
        Matcher matcher = regex.matcher(text);

        if (caretPosition == textArea.getText().length()) {
            Platform.runLater(() -> textArea.positionCaret(0));
            return;
        }

        if (matcher.find()) {
            if (!replaceText.isEmpty())
                textArea.replaceText(caretPosition + matcher.start(), caretPosition + matcher.end(),
                        replaceText);

            textArea.selectRange(caretPosition + matcher.start(), caretPosition + matcher.end());
        } else {
            Platform.runLater(() -> textArea.positionCaret(0));
        }
    }

    /**
     * Performs a search for the specified word in the given text and returns the count of occurrences.
     *
     * @param text           The text in which to perform the search.
     * @param findText       The text to find.
     * @param caseSensitive  Indicates whether the search is case-sensitive.
     * @param matchWholeWord Indicates whether to match whole words only.
     * @return The count of occurrences of the word in the text.
     */
    private int performSearch(String text, String findText, boolean caseSensitive, boolean matchWholeWord) {
        Pattern regex = createRegexPattern(findText, caseSensitive, matchWholeWord);
        Matcher matcher = regex.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Retrieves the find information label from the specified stage.
     *
     * @param stage The stage containing the find information label.
     * @return The find information label.
     */
    private Label getFindLabel(Stage stage) {
        return (Label) stage.getScene().lookup("#find-info-label");
    }

    /**
     * Determines whether the "Match Whole Word" checkbox is selected in the specified stage.
     *
     * @param stage The stage containing the checkbox.
     * @param id    The ID of the checkbox.
     * @return True if the checkbox is selected, false otherwise.
     */
    private boolean isMatchWholeWord(Stage stage, String id) {
        CheckBox checkBox = (CheckBox) stage.getScene().lookup("#" + id);
        return checkBox.isSelected();
    }

    /**
     * Determines whether the "Case Sensitive" checkbox is selected in the specified stage.
     *
     * @param stage The stage containing the checkbox.
     * @param id    The ID of the checkbox.
     * @return True if the checkbox is selected, false otherwise.
     */
    private boolean isCaseSensitiveSearchRequested(Stage stage, String id) {
        CheckBox checkBox = (CheckBox) stage.getScene().lookup("#" + id);
        return checkBox.isSelected();
    }

    /**
     * Retrieves the text from the specified text field in the given stage.
     *
     * @param stage The stage containing the text field.
     * @param id    The ID of the text field.
     * @return The text from the text field.
     */
    private String getFindText(Stage stage, String id) {
        TextField textField = (TextField) stage.getScene().lookup("#" + id);
        return textField.getText();
    }

    /**
     * Retrieves the replacement text from the specified text field in the given stage.
     *
     * @param stage The stage containing the text field.
     * @param id    The ID of the text field.
     * @return The replacement text from the text field.
     */
    private String getReplaceText(Stage stage, String id) {
        TextField textField = (TextField) stage.getScene().lookup("#" + id);
        return textField.getText();
    }

    /**
     * Creates a regular expression pattern for the specified find text, case sensitivity, and whole word matching.
     *
     * @param findText       The text to find.
     * @param caseSensitive  Indicates whether the search is case-sensitive.
     * @param matchWholeWord Indicates whether to match whole words only.
     * @return The regular expression pattern.
     */
    private Pattern createRegexPattern(String findText, boolean caseSensitive, boolean matchWholeWord) {
        String regexString = Pattern.quote(findText);
        if (matchWholeWord) regexString = "\\b" + regexString + "\\b";
        if (!caseSensitive) regexString = "(?i)" + regexString;
        return Pattern.compile(regexString);
    }

    /**
     * Closes the find stage if it is open.
     */
    public void onCloseFindPaneEvent() {
        Stage findStage = getFindStage();
        if (findStage == null) return;
        findStage.close();
    }

    /**
     * Retrieves the find stage containing the "Find" or "Find and Replace" functionality.
     *
     * @return The find stage, or null if not found.
     */
    private Stage getFindStage() {
        Stage stage = null;

        for (Window window : Window.getWindows()) {
            String title = ((Stage) window).getTitle();

            if (title.equalsIgnoreCase("Localizar") ||
                    title.equalsIgnoreCase("Substituir")) {
                stage = (Stage) window;
                break;
            }
        }

        return stage;
    }

    /**
     * Retrieves the text area from the currently selected tab.
     *
     * @return The text area from the currently selected tab, or null if no tab is selected.
     */
    private TextArea getCurrentTextArea() {
        TabPane tabPane = tabController.lookupTabPane();

        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        return selectedTab != null ? ((TextArea) ((BorderPane) selectedTab.getContent()).getCenter()) : null;
    }

    /**
     * Retrieves the BorderPane from the currently selected tab's content in the TabPane.
     *
     * @return The BorderPane from the selected tab's content, or null if no tab is selected.
     */
    private BorderPane getCurrentBorderPane() {
        TabPane tabPane = tabController.lookupTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        return selectedTab != null ? (BorderPane) selectedTab.getContent() : null;
    }
}
