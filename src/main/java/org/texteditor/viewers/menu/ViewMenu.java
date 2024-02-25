package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import org.texteditor.controllers.EventController;

import static org.texteditor.Main.createIcon;
import static org.texteditor.viewers.menu.MenuItemBuild.createMenuItem;

/**
 * Represents the View Menu in a text editor application, responsible for visual-related operations.
 * Extends the JavaFX Menu class and implements the CustomMenu interface.
 */
public class ViewMenu extends Menu implements CustomMenu {

    private final EventController eventController;

    /**
     * Constructor for the ViewMenu class.
     *
     * @param eventController The EventController responsible for handling menu-related events.
     */
    public ViewMenu(EventController eventController) {
        super("Visualizar");
        this.eventController = eventController;
    }

    /**
     * Configures the menu items and associates them with their corresponding actions.
     */
    @Override
    public void configure() {
        configureLargeMenuItem();
        configureReduceMenuItem();
        configureRestoreDefaultZoomMenuItem();
    }

    /**
     * Configures the menu item for enlarging the view.
     */
    private void configureLargeMenuItem() {
        MenuItem enLargeItem = createMenuItem("Ampliar", eventController::onEnlargeEvent,
                KeyCode.ADD, KeyCombination.CONTROL_DOWN);
        enLargeItem.setGraphic(createIcon("zoom_in.png"));
        addComponents(enLargeItem);
    }

    /**
     * Configures the menu item for reducing the view.
     */
    private void configureReduceMenuItem() {
        MenuItem reduceItem = createMenuItem("Reduzir", eventController::onReduceEvent,
                KeyCode.SUBTRACT, KeyCombination.CONTROL_DOWN);
        reduceItem.setGraphic(createIcon("zoom_out.png"));
        addComponents(reduceItem);
    }

    /**
     * Configures the menu item for restoring the default zoom level.
     */
    private void configureRestoreDefaultZoomMenuItem() {
        MenuItem restoreDefaultZoomItem = createMenuItem("Restaurar o zoom padrão", eventController::onRestoreDefaultZoomEvent,
                KeyCode.SLASH, KeyCombination.CONTROL_DOWN);
        restoreDefaultZoomItem.setGraphic(createIcon("zoom_default.png"));
        addComponents(restoreDefaultZoomItem);
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
