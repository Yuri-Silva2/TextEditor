package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.TextEditorUtils;

public class ConfigMenu extends Menu implements CustomMenu {

    public ConfigMenu() {
        super("Configurações");
    }

    @Override
    public void configure() {
        setId("config-menu");

        MenuItem preferencesItem = createNewItem("Preferências...    ", this::onPreferencesEvent);
        preferencesItem.setGraphic(TextEditorUtils.createIcon("media/preferences.png"));

        MenuItem shortCutMapItem = createNewItem("Mapa de atalhos    ", this::onShortCutMapEvent);
        shortCutMapItem.setGraphic(TextEditorUtils.createIcon("media/shortcut.png"));

        addComponents(preferencesItem, shortCutMapItem);
    }

    private void onPreferencesEvent() {

    }

    private void onShortCutMapEvent() {

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
