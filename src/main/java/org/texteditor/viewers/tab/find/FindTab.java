package org.texteditor.viewers.tab.find;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.texteditor.controllers.EventController;
import org.texteditor.viewers.tab.CustomTab;

import static org.texteditor.viewers.tab.find.FindTabBuild.*;

/**
 * Represents a tab for locating text within the text editor.
 */
public class FindTab extends Tab implements CustomTab {

    private final EventController eventController;

    private final TextField textField;

    private final CheckBox matchWholeWordCheckBox;
    private final CheckBox differentiateUppercaseOrLowercaseLetters;

    private final Button findNextButton;
    private final Button countButton;
    private final Button closeButton;

    /**
     * Constructs a new FindTab object.
     *
     * @param eventController The event controller for handling tab events.
     */
    public FindTab(EventController eventController) {
        super("Localizar");
        this.eventController = eventController;
        this.textField = createTextField(28.0);
        this.matchWholeWordCheckBox = createCheckBox("Coincidir palavra inteira", 144.0);
        this.differentiateUppercaseOrLowercaseLetters = createCheckBox("Diferenciar maísculas/mínusculas", 178.0);
        this.findNextButton = createButton("Localizar próximo", 28.0);
        this.countButton = createButton("Contar", 86.0);
        this.closeButton = createButton("Fechar", 140.0);
    }

    /**
     * Configures the appearance and behavior of the FindTab.
     */
    @Override
    public void configure() {
        setId("find-tabpane-section");
        configureComponents();
        configureActions();
    }

    /**
     * Configures the UI components within the FindTab.
     */
    private void configureComponents() {
        textField.setId("find-textfield-id");
        matchWholeWordCheckBox.setId("find-checkbox1-id");
        differentiateUppercaseOrLowercaseLetters.setId("find-checkbox2-id");
        Text text = createText("Localizar :", 63.0, 45.0);
        AnchorPane pane = createAnchorPane(textField, text, findNextButton,
                countButton, closeButton, matchWholeWordCheckBox, differentiateUppercaseOrLowercaseLetters);
        setContent(pane);
    }

    /**
     * Configures the actions (event handlers) for UI components within the FindTab.
     */
    private void configureActions() {
        findNextButton.setOnAction(event -> onNextFindButtonClick());
        countButton.setOnAction(event -> onCountButtonClick());
        closeButton.setOnAction(event -> onCloseButtonClick());
    }

    /**
     * Event handler for the nextFindButton click event. Find next occurrence of text in the editor.
     */
    private void onNextFindButtonClick() {
        eventController.onNextFindButtonEvent("find-textfield-id",
                "find-checkbox1-id", "find-checkbox2-id");
    }

    /**
     * Event handler for the countButton click event. Counts occurrence of text in the editor.
     */
    private void onCountButtonClick() {
        eventController.onCountEvent("find-textfield-id",
                "find-checkbox1-id", "find-checkbox2-id");
    }

    /**
     * Event handler for the closeButton click event. Closes the find pane.
     */
    private void onCloseButtonClick() {
        eventController.onCloseFindPaneEvent();
    }
}
