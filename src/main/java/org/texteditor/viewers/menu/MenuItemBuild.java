package org.texteditor.viewers.menu;

import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * The MenuItemBuild class provides static methods for creating MenuItems with specific properties,
 * such as labels, event handlers, and keyboard shortcuts.
 */
public class MenuItemBuild {

    /**
     * Creates a MenuItem with the specified label, event handler and keyboard shortcut.
     *
     * @param content      The label for the menu item.
     * @param eventHandler The event handler associated with the menu item.
     * @param keyCode      The primary key code for the keyboard shortcut.
     * @param modifiers    The modifier keys for the keyboard shortcut.
     * @return MenuItem with the specified properties.
     */
    protected static MenuItem createMenuItem(String content, Runnable eventHandler, KeyCode keyCode, KeyCombination.Modifier... modifiers) {
        MenuItem menuItem = new MenuItem(content);
        menuItem.setId(content.toLowerCase().replace(" ", "-") + "-item");
        menuItem.setOnAction(actionEvent -> eventHandler.run());
        menuItem.setAccelerator(new KeyCodeCombination(keyCode, modifiers));
        return menuItem;
    }

    /**
     * Creates a MenuItem with the specified label, event handler and keyboard shortcut.
     *
     * @param content      The label for the menu item.
     * @param eventHandler The event handler associated with the menu item.
     * @return MenuItem with the specified properties.
     */
    protected static MenuItem createMenuItem(String content, Runnable eventHandler) {
        MenuItem menuItem = new MenuItem(content);
        menuItem.setId(content.toLowerCase().replace(" ", "-") + "-item");
        menuItem.setOnAction(actionEvent -> eventHandler.run());
        return menuItem;
    }
}
