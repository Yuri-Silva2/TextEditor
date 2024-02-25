package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import org.texteditor.controllers.EventController;

import static org.texteditor.Main.createIcon;
import static org.texteditor.viewers.menu.MenuItemBuild.createMenuItem;

/**
 * The FindMenu class represents a menu for handling text locating and searching operations in the text editor.
 */
public class FindMenu extends Menu implements CustomMenu {

    private final EventController eventController;

    /**
     * Constructs a FindMenu with the specified EventController.
     *
     * @param eventController The EventController for handling text locating events.
     */
    public FindMenu(EventController eventController) {
        super("Localizar");
        this.eventController = eventController;
    }

    /**
     * Configures the menu items and their associated actions.
     */
    @Override
    public void configure() {
        configureFindMenuItem();
        configureFindAndReplaceMenuItem();
        configureFindNearbyMenuItem();
    }

    /**
     * Configures the Find menu item and its associated action.
     */
    private void configureFindMenuItem() {
        MenuItem findItem = createMenuItem("Localizar...", eventController::onFindEvent,
                KeyCode.F, KeyCombination.CONTROL_DOWN);
        findItem.setGraphic(createIcon("find.png"));
        addComponents(findItem);
    }

    /**
     * Configures the Find and Replace menu item and its associated action.
     */
    private void configureFindAndReplaceMenuItem() {
        MenuItem findAndReplaceItem = createMenuItem("Substituir", eventController::onFindAndReplaceEvent,
                KeyCode.H, KeyCombination.CONTROL_DOWN);
        findAndReplaceItem.setGraphic(createIcon("find_replace.png"));
        addComponents(findAndReplaceItem);
    }

    /**
     * Configures the Find Nearby menu item and its associated action.
     */
    private void configureFindNearbyMenuItem() {
        MenuItem findNearbyItem = createMenuItem("Localizar próximo", eventController::onFindNearbyEvent,
                KeyCode.F3);
        findNearbyItem.setGraphic(createIcon("find.png"));
        addComponents(findNearbyItem);
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
