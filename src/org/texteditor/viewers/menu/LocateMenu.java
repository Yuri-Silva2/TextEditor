package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import org.texteditor.controllers.EventController;

import static org.texteditor.TextEditorApplication.createIcon;
import static org.texteditor.viewers.menu.MenuItemCreator.createMenuItem;

/**
 * The LocateMenu class represents a menu for handling text locating and searching operations in the text editor.
 */
public class LocateMenu extends Menu implements CustomMenu {

    private final EventController eventController;

    /**
     * Constructs a LocateMenu with the specified EventController.
     *
     * @param eventController The EventController for handling text locating events.
     */
    public LocateMenu(EventController eventController) {
        super("Localizar");
        this.eventController = eventController;
    }

    /**
     * Configures the menu items and their associated actions.
     */
    @Override
    public void configure() {
        setId("locate-menu");
        configureLocateMenuItem();
        configureFindInFilesMenuItem();
        configureFindNearbyMenuItem();
        configureFindPreviousMenuItem();
        configureFindAndReplaceMenuItem();
    }

    /**
     * Configures the "Localizar..." menu item and its associated action.
     */
    private void configureLocateMenuItem() {
        MenuItem locateItem = createMenuItem("Localizar...", eventController::onLocateEvent,
                KeyCode.F, KeyCombination.CONTROL_DOWN);
        locateItem.setGraphic(createIcon("media/find.png"));
        addComponents(locateItem);
    }

    /**
     * Configures the "Localizar em arquivos" menu item and its associated action.
     */
    private void configureFindInFilesMenuItem() {
        MenuItem findInFilesItem = createMenuItem("Localizar em arquivos", eventController::onFindInFilesEvent,
                KeyCode.F, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
        findInFilesItem.setGraphic(createIcon("media/find_in_file.png"));
        addComponents(findInFilesItem);
    }

    /**
     * Configures the "Localizar próximo" menu item and its associated action.
     */
    private void configureFindNearbyMenuItem() {
        MenuItem findNearbyItem = createMenuItem("Localizar próximo", eventController::onFindNearbyEvent,
                KeyCode.F3);
        findNearbyItem.setGraphic(createIcon("media/find.png"));
        addComponents(findNearbyItem);
    }

    /**
     * Configures the "Localizar anterior" menu item and its associated action.
     */
    private void configureFindPreviousMenuItem() {
        MenuItem findPreviousItem = createMenuItem("Localizar anterior", eventController::onFindPreviousEvent,
                KeyCode.F, KeyCombination.SHIFT_DOWN);
        findPreviousItem.setGraphic(createIcon("media/find.png"));
        addComponents(findPreviousItem);
    }

    /**
     * Configures the "Substituir" menu item and its associated action.
     */
    private void configureFindAndReplaceMenuItem() {
        MenuItem findAndReplaceItem = createMenuItem("Substituir", eventController::onFindAndReplaceEvent,
                KeyCode.H, KeyCombination.CONTROL_DOWN);
        findAndReplaceItem.setGraphic(createIcon("media/find_replace.png"));
        addComponents(findAndReplaceItem);
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
