package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.controllers.EventController;

import static org.texteditor.Main.createIcon;
import static org.texteditor.viewers.menu.MenuItemBuild.createMenuItem;

/**
 * The ConfigMenu class represents a menu for handling configuration-related operations in the text editor.
 */
public class ConfigMenu extends Menu implements CustomMenu {

    private final EventController eventController;

    /**
     * Constructs a ConfigMenu with the specified EventController.
     *
     * @param eventController The EventController for handling configuration events.
     */
    public ConfigMenu(EventController eventController) {
        super("Configurações");
        this.eventController = eventController;
    }

    /**
     * Configures the menu items and their associated actions.
     */
    @Override
    public void configure() {
        setId("config-menu");
        configurePreferencesMenuItem();
        configureShortcutMapMenuItem();
    }

    /**
     * Configures the Preferences menu item and its associated action.
     */
    private void configurePreferencesMenuItem() {
        MenuItem preferencesItem = createMenuItem("Preferências...", eventController::onPreferencesEvent);
        preferencesItem.setGraphic(createIcon("preferences.png"));
        addComponents(preferencesItem);
    }

    /**
     * Configures the Shortcut Map menu item and its associated action.
     */
    private void configureShortcutMapMenuItem() {
        MenuItem shortcutMapItem = createMenuItem("Mapa de atalhos", eventController::onShortcutMapEvent);
        shortcutMapItem.setGraphic(createIcon("shortcut.png"));
        addComponents(shortcutMapItem);
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
