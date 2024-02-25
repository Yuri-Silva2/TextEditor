package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.controllers.EventController;

import static org.texteditor.Main.createIcon;
import static org.texteditor.viewers.menu.MenuItemBuild.createMenuItem;

/**
 * The HelpMenu class represents a custom menu for displaying help-related options.
 * It provides a method to configure the menu with specific items.
 */
public class HelpMenu extends Menu implements CustomMenu {

    private final EventController eventController;

    /**
     * Constructs a new HelpMenu with the specified event controller.
     *
     * @param eventController The EventController instance to use for handling events.
     */
    public HelpMenu(EventController eventController) {
        super("?");
        this.eventController = eventController;
    }

    /**
     * Configures the HelpMenu by setting an ID and adding menu items.
     */
    @Override
    public void configure() {
        configureUpdateMenuItem();
    }

    /**
     * Configures the update menu item with an icon and event handler.
     */
    private void configureUpdateMenuItem() {
        MenuItem updateItem = createMenuItem("Atualizar...", eventController::onUpdateEvent);
        updateItem.setGraphic(createIcon("update.png"));
        addComponents(updateItem);
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
