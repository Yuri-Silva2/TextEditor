package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import org.texteditor.TextEditorUtils;

public class LocateMenu extends Menu implements CustomMenu {

    public LocateMenu() {
        super("Localizar");
    }

    /**
     * Configures the menu items and their associated actions.
     */
    @Override
    public void configure() {
        setId("locate-menu");

        MenuItem toLocateItem = createNewItem("Localizar...    ", this::onLocateEvent);
        toLocateItem.setAccelerator(new KeyCodeCombination(KeyCode.F,
                KeyCombination.CONTROL_DOWN));
        toLocateItem.setGraphic(TextEditorUtils.createIcon("media/find.png"));

        MenuItem findInFilesItem = createNewItem("Localizar em arquivos    ", this::onFindInFilesEvent);
        findInFilesItem.setAccelerator(new KeyCodeCombination(KeyCode.F,
                KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
        findInFilesItem.setGraphic(TextEditorUtils.createIcon("media/find_in_file.png"));

        MenuItem findNearbyItem = createNewItem("Localizar próximo    ", this::onFindNearbyEvent);
        findNearbyItem.setAccelerator(new KeyCodeCombination(KeyCode.F3));
        findNearbyItem.setGraphic(TextEditorUtils.createIcon("media/find.png"));

        MenuItem findPreviousItem = createNewItem("Localizar anterior    ", this::onFindPreviousEvent);
        findPreviousItem.setAccelerator(new KeyCodeCombination(KeyCode.F,
                KeyCombination.SHIFT_DOWN));
        findPreviousItem.setGraphic(TextEditorUtils.createIcon("media/find.png"));

        MenuItem findAndReplaceItem = createNewItem("Substituir    ", this::onFindAndReplaceEvent);
        findAndReplaceItem.setAccelerator(new KeyCodeCombination(KeyCode.H,
                KeyCombination.CONTROL_DOWN));
        findAndReplaceItem.setGraphic(TextEditorUtils.createIcon("media/find_replace.png"));

        addComponents(toLocateItem, findInFilesItem,
                findNearbyItem, findPreviousItem, findAndReplaceItem);
    }

    private void onLocateEvent() {

    }

    private void onFindInFilesEvent() {

    }

    private void onFindNearbyEvent() {

    }

    private void onFindPreviousEvent() {

    }

    private void onFindAndReplaceEvent() {

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
