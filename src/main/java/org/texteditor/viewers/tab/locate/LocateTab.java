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
 * Represents a tab for locating text within the text editor.
 */
public class LocateTab extends Tab implements CustomTab {

    private final EventController eventController;

    private final TextField textField;

    private final CheckBox matchWholeWordCheckBox;
    private final CheckBox differentiateUppercaseOrLowercaseLetters;

    private final Button nextLocateButton;
    private final Button countButton;
    private final Button closeButton;

    /**
     * Constructs a new LocateTab object.
     *
     * @param eventController The event controller for handling tab events.
     */
    public LocateTab(EventController eventController) {
        super("Localizar");
        this.eventController = eventController;
        this.textField = createTextField(28.0);
        this.matchWholeWordCheckBox = createCheckBox("Coincidir palavra inteira", 144.0);
        this.differentiateUppercaseOrLowercaseLetters = createCheckBox("Diferenciar maísculas/mínusculas", 178.0);
        this.nextLocateButton = createButton("Localizar próximo", 28.0);
        this.countButton = createButton("Contar", 86.0);
        this.closeButton = createButton("Fechar", 140.0);
    }

    /**
     * Configures the appearance and behavior of the LocateTab.
     */
    @Override
    public void configure() {
        setId("locate-tabpane-section");
        configureComponents();
        configureActions();
    }

    /**
     * Configures the UI components within the LocateTab.
     */
    private void configureComponents() {
        textField.setId("locate-textfield-id");
        matchWholeWordCheckBox.setId("locate-checkbox1-id");
        differentiateUppercaseOrLowercaseLetters.setId("locate-checkbox2-id");
        Text text = createText("Localizar :", 63.0, 45.0);
        AnchorPane pane = createAnchorPane(textField, text, nextLocateButton,
                countButton, closeButton, matchWholeWordCheckBox, differentiateUppercaseOrLowercaseLetters);
        setContent(pane);
    }

    /**
     * Configures the actions (event handlers) for UI components within the LocateTab.
     */
    private void configureActions() {
        nextLocateButton.setOnAction(event -> onNextLocateButtonClick());
        countButton.setOnAction(event -> onCountButtonClick());
        closeButton.setOnAction(event -> onCloseButtonClick());
    }

    /**
     * Event handler for the nextLocateButton click event. Find next occurrence of text in the editor.
     */
    private void onNextLocateButtonClick() {
        eventController.onNextLocateButtonEvent("locate-textfield-id",
                "locate-checkbox1-id", "locate-checkbox2-id");
    }

    /**
     * Event handler for the countButton click event. Counts occurrence of text in the editor.
     */
    private void onCountButtonClick() {
        eventController.onCountEvent("locate-textfield-id",
                "locate-checkbox1-id", "locate-checkbox2-id");
    }

    /**
     * Event handler for the closeButton click event. Closes the locate pane.
     */
    private void onCloseButtonClick() {
        eventController.onCloseLocatePaneEvent();
    }
}
