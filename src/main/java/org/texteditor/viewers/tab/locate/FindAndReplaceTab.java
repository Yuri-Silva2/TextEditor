package org.texteditor.viewers.tab.locate;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.texteditor.controllers.EventController;
import org.texteditor.viewers.tab.CustomTab;

import static org.texteditor.viewers.tab.locate.LocateTabBuild.*;

/**
 * Represents a tab for find and replace text within the text editor.
 */
public class FindAndReplaceTab extends Tab implements CustomTab {

    private final EventController eventController;

    private final TextField textField1;
    private final TextField textField2;

    private final CheckBox matchWholeWordCheckBox;
    private final CheckBox differentiateUppercaseOrLowercaseLetters;

    private final Button findAndReplaceAllButton;
    private final Button findAndReplaceButton;
    private final Button nextLocateButton;
    private final Button closeButton;

    /**
     * Constructs a new FindAndReplaceTab.
     *
     * @param eventController The event controller for handling tab events.
     */
    public FindAndReplaceTab(EventController eventController) {
        super("Substituir");
        this.eventController = eventController;
        this.textField1 = createTextField(28.0);
        this.textField2 = createTextField(61.0);
        this.matchWholeWordCheckBox = createCheckBox("Coincidir palavra inteira", 144.0);
        this.differentiateUppercaseOrLowercaseLetters = createCheckBox("Diferenciar maísculas/mínusculas", 178.0);
        this.nextLocateButton = createButton("Localizar próximo", 28.0);
        this.findAndReplaceButton = createButton("Substituir", 61.0);
        this.findAndReplaceAllButton = createButton("Substituir todos", 93.0);
        this.closeButton = createButton("Fechar", 132.0);
    }

    /**
     * Configures the appearance and behavior of the FindAndReplaceTab.
     */
    @Override
    public void configure() {
        setId("findandreplace-tabpane-section");
        configureComponents();
        configureActions();
    }

    /**
     * Configures the UI components within the FindAndReplaceTab.
     */
    private void configureComponents() {
        textField1.setId("findandreplace-textfield1-id");
        textField2.setId("findandreplace-textfield2-id");
        matchWholeWordCheckBox.setId("findandreplace-checkbox1-id");
        differentiateUppercaseOrLowercaseLetters.setId("findandreplace-checkbox2-id");
        Text text1 = createText("Localizar :", 63.0, 45.0);
        Text text2 = createText("Substituir por :", 35.0, 78.0);
        AnchorPane pane = createAnchorPane(textField1, textField2, text1, text2, nextLocateButton,
                findAndReplaceButton, findAndReplaceAllButton, closeButton,
                matchWholeWordCheckBox, differentiateUppercaseOrLowercaseLetters);
        setContent(pane);
    }

    /**
     * Configures the actions (event handlers) for UI components within the FindAndReplaceTab.
     */
    private void configureActions() {
        nextLocateButton.setOnAction(event -> onNextLocateButtonClick());
        findAndReplaceButton.setOnAction(event -> onFindAndReplaceButtonClick());
        findAndReplaceAllButton.setOnAction(event -> onFindAndReplaceAllButtonClick());
        closeButton.setOnAction(event -> onCloseButtonClick());
    }

    /**
     * Event handler for the nextLocateButton click event. Find next occurrence of text in the editor.
     */
    private void onNextLocateButtonClick() {
        eventController.onNextLocateButtonEvent("findandreplace-textfield1-id",
                "findandreplace-checkbox1-id", "findandreplace-checkbox2-id");
    }

    /**
     * Event handler for the findAndReplaceButton click event. Find next and replace occurrence of text in the editor.
     */
    private void onFindAndReplaceButtonClick() {
        eventController.onFindAndReplaceButtonEvent("findandreplace-textfield1-id", "findandreplace-textfield2-id",
                "findandreplace-checkbox1-id", "findandreplace-checkbox2-id");
    }

    /**
     * Event handler for the findAndReplaceAllButton click event. Find next and replace occurrence of text in the editor.
     */
    private void onFindAndReplaceAllButtonClick() {
        eventController.onFindAndReplaceAllButtonEvent("findandreplace-textfield1-id", "findandreplace-textfield2-id",
                "findandreplace-checkbox1-id", "findandreplace-checkbox2-id");
    }

    /**
     * Event handler for the closeButton click event. Closes the locate pane.
     */
    private void onCloseButtonClick() {
        eventController.onCloseLocatePaneEvent();
    }
}
