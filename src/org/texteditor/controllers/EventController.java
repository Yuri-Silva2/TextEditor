package org.texteditor.controllers;

import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import org.texteditor.models.TextFile;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.UUID;

/**
 * The EventController class handles various events in the text editor application,
 * such as undo, redo, cut, copy, paste, delete, and file-related operations.
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
     *  Handles the vent when the user wants to undo the last action in the text area.
     */
    public void onUndoEvent() {
        Tab selectedTab = getCurrentSelectTab();
        if (selectedTab == null) return;

        TextArea textArea = (TextArea) selectedTab.getContent();
        textArea.undo();
    }

    /**
     * Handles the event when the user wants to redo the last undone action in the text area.
     */
    public void onRemakeEvent() {
        Tab selectedTab = getCurrentSelectTab();
        if (selectedTab == null) return;

        TextArea textArea = (TextArea) selectedTab.getContent();
        textArea.redo();
    }

    /**
     * Handles the event when the user wants to cut the selected text and copy it to the clipboard.
     */
    public void onCutEvent() {
        Tab selectedTab = getCurrentSelectTab();
        if (selectedTab == null) return;

        TextArea textArea = (TextArea) selectedTab.getContent();
        String selectedText = textArea.getSelectedText();

        textArea.setText(textArea.getText()
                .replace(selectedText, ""));

        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit()
                .getSystemClipboard();
        StringSelection stringSelection = new StringSelection(selectedText);
        clipboard.setContents(stringSelection, stringSelection);
    }

    /**
     * Handles the event when the user wants to copy the selected text to the clipboard.
     */
    public void onCopyEvent() {
        Tab selectedTab = getCurrentSelectTab();
        if (selectedTab == null) return;

        TextArea textArea = (TextArea) selectedTab.getContent();
        String selectedText = textArea.getSelectedText();

        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit()
                .getSystemClipboard();
        StringSelection stringSelection = new StringSelection(selectedText);
        clipboard.setContents(stringSelection, stringSelection);
    }

    /**
     * Handles the event when the user wants to paste text from the clipboard into the text area.
     */
    public void onPasteEvent() {
        Clipboard clipboard = Clipboard.getSystemClipboard();

        Tab selectedTab = getCurrentSelectTab();
        if (selectedTab == null) return;

        TextArea textArea = (TextArea) selectedTab.getContent();

        if (clipboard.hasString()) {
            String clipboardText = clipboard.getString();
            String text = textArea.getText();
            int position = textArea.getCaretPosition();

            StringBuilder stringBuilder = new StringBuilder(text);
            stringBuilder.insert(position, clipboardText);

            textArea.setText(stringBuilder.toString());
            textArea.positionCaret(position);
        }
    }

    /**
     * Handles the event when the user wants to delete the selected text in the text area.
     */
    public void onDeleteEvent() {
        Tab selectedTab = getCurrentSelectTab();
        if (selectedTab == null) return;

        TextArea textArea = (TextArea) selectedTab.getContent();
        String selectedText = textArea.getSelectedText();

        textArea.setText(textArea.getText()
                .replace(selectedText, ""));
    }

    /**
     * Handles the event when the user wants to select all text in the text area.
     */
    public void onSelectAllEvent() {
        Tab selectedTab = getCurrentSelectTab();

        if (selectedTab != null)
            ((TextArea) selectedTab.getContent()).selectAll();
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

        int number = tabPane.getTabs().size() + 1;
        String name = "Sem título (" + number + ")";

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
     * Internally handles the saving of a file, considering whether it is a new save or a "Save As" operation.
     *
     * @param tab    Tab associated with the file to be saved
     * @param saveAs Indicates whether it is a "Save As" operation
     */
    private void saveFileInternal(Tab tab, boolean saveAs) {
        TextArea textArea = (TextArea) tab.getContent();
        String tabId = tab.getId();

        TextFile textFile = textFileController.requestTextFile(tabId);

        if (textFile.saved() && !saveAs) {
            textFileController.updateTextFile(textFile.uuid().toString(), textArea.getText());
            fileController.writeFile(textFile.filePath(), textArea.getText());

        } else {
            File selectedFile = fileController.createFileChooserAndSaveFile("Salvar arquivo");

            if (selectedFile != null) {
                textFileController.updateTextFile(tabId, selectedFile.getPath(), textArea.getText());
                fileController.writeFile(selectedFile.getPath(), textArea.getText());
            }
        }
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

    public void onPreferencesEvent() {

    }

    public void onShortcutMapEvent() {

    }

    public void onCodificationANSIEvent() {

    }

    public void onCodificationUTF8Event() {

    }

    public void onConvertANSIEvent() {

    }

    public void onConvertUTF8Event() {

    }

    public void onUpdateEvent() {

    }

    public void onLocateEvent() {

    }

    public void onFindInFilesEvent() {

    }

    public void onFindNearbyEvent() {

    }

    public void onFindPreviousEvent() {

    }

    public void onFindAndReplaceEvent() {

    }

    public void onEnlargeEvent() {

    }

    public void onReduceEvent() {

    }

    public void onRestoreDefaultZoomEvent() {

    }

    public void onFileInformationEvent() {

    }

    public void onLineStyleEvent() {

    }
}
