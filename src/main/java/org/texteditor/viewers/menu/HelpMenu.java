package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.controllers.EventController;

import static org.texteditor.Main.createIcon;
import static org.texteditor.viewers.menu.MenuItemBuild.createMenuItem;

public class HelpMenu extends Menu implements CustomMenu {

    private final EventController eventController;

    public HelpMenu(EventController eventController) {
        super("?");
        this.eventController = eventController;
    }

    @Override
    public void configure() {
        setId("help-menu");
        configureUpdateMenuItem();
    }

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
