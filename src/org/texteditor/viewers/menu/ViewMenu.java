package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import org.texteditor.TextEditorUtils;

public class ViewMenu extends Menu implements CustomMenu {

    public ViewMenu() {
        super("Visualizar");
    }

    @Override
    public void configure() {
        setId("view-menu");

        MenuItem enlargeItem = createNewItem("Ampliar    ", this::onEnlargeEvent);
        enlargeItem.setAccelerator(new KeyCodeCombination(KeyCode.ADD,
                KeyCombination.CONTROL_DOWN));
        enlargeItem.setGraphic(TextEditorUtils.createIcon("media/zoom_in.png"));

        MenuItem reduceItem = createNewItem("Reduzir    ", this::onReduceEvent);
        reduceItem.setAccelerator(new KeyCodeCombination(KeyCode.SUBTRACT,
                KeyCombination.CONTROL_DOWN));
        reduceItem.setGraphic(TextEditorUtils.createIcon("media/zoom_out.png"));

        MenuItem restoreDefaultZoomItem = createNewItem("Restaurar o zoom padrão    ", this::onRestoreDefaultZoomEvent);
        restoreDefaultZoomItem.setAccelerator(new KeyCodeCombination(KeyCode.SLASH,
                KeyCombination.CONTROL_DOWN));
        restoreDefaultZoomItem.setGraphic(TextEditorUtils.createIcon("media/zoom_default.png"));

        MenuItem fileInformationItem = createNewItem("Informações sobre o arquivo...    ", this::onFileInformationEvent);
        restoreDefaultZoomItem.setGraphic(TextEditorUtils.createIcon("media/informations.png"));

        addComponents(enlargeItem, reduceItem, restoreDefaultZoomItem,
                fileInformationItem);
    }

    private void onEnlargeEvent() {

    }

    private void onReduceEvent() {

    }

    private void onRestoreDefaultZoomEvent() {

    }

    private void onFileInformationEvent() {

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
