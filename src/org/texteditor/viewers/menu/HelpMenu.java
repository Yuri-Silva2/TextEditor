package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.TextEditorUtils;

public class HelpMenu extends Menu implements CustomMenu {

    public HelpMenu() {
        super("?");
    }

    @Override
    public void configure() {
        setId("help-menu");

        MenuItem updateItem = createNewItem("Atualizar...    ", this::onUpdateEvent);
        updateItem.setGraphic(TextEditorUtils.createIcon("media/update.png"));

        addComponents(updateItem);
    }

    private void onUpdateEvent() {

    }

    /**
     * Creates a MenuItem for creating a new item.
     *
     * @return MenuItem for creating a new item
     */
    private MenuItem createNewItem(String content, Runnable eventHandler) {
        MenuItem newItem = new MenuItem(content);
        newItem.setId("new-item");
        newItem.setOnAction(actionEvent -> eventHandler.run());
        return newItem;
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
