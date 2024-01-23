package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.controllers.EventController;

import static org.texteditor.TextEditorApplication.createIcon;
import static org.texteditor.viewers.menu.MenuItemCreator.createMenuItem;

/**
 * The FormatMenu class represents a menu for handling text formatting operations in the text editor.
 */
public class FormatMenu extends Menu implements CustomMenu {

    private final EventController eventController;

    /**
     * Constructs a FormatMenu with the specified EventController.
     *
     * @param eventController The EventController for handling text formatting events.
     */
    public FormatMenu(EventController eventController) {
        super("Formatar");
        this.eventController = eventController;
    }

    /**
     * Configures the menu items and their associated actions.
     */
    @Override
    public void configure() {
        setId("format-menu");
        configureCodificationANSIMenuItem();
        configureCodificationUTF8MenuItem();
        configureConvertANSIMenuItem();
        configureConvertUTF8MenuItem();
    }

    /**
     * Configures the Codification ANSI menu item and its associated action.
     */
    private void configureCodificationANSIMenuItem() {
        MenuItem codificationANSIItem = createMenuItem("Codificação em ANSI", eventController::onCodificationANSIEvent);
        codificationANSIItem.setGraphic(createIcon("media/type.png"));
        addComponents(codificationANSIItem);
    }

    /**
     * Configures the Codification UTF-8 menu item and its associated action.
     */
    private void configureCodificationUTF8MenuItem() {
        MenuItem codificationUTF8Item = createMenuItem("Codificação em UTF-8", eventController::onCodificationUTF8Event);
        codificationUTF8Item.setGraphic(createIcon("media/type.png"));
        addComponents(codificationUTF8Item);
    }

    /**
     * Configures the Convert ANSI menu item and its associated action.
     */
    private void configureConvertANSIMenuItem() {
        MenuItem convertANSIItem = createMenuItem("Converter para ANSI", eventController::onConvertANSIEvent);
        convertANSIItem.setGraphic(createIcon("media/type.png"));
        addComponents(convertANSIItem);
    }

    /**
     * Configures the Convert UTF-8 menu item and its associated action.
     */
    private void configureConvertUTF8MenuItem() {
        MenuItem convertUTF8Item = createMenuItem("Converter para UTF-8", eventController::onConvertUTF8Event);
        convertUTF8Item.setGraphic(createIcon("media/type.png"));
        addComponents(convertUTF8Item);
    }

    /**
     * Adds an array of MenuItems to the menu.
     *
     * @param menuItems MenuItems to be added to the menu
     */
    private void addComponents(MenuItem... menuItems) {
        getItems().addAll(menuItems);
    }
}
