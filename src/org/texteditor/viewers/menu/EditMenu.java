package org.texteditor.viewers.menu;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import org.texteditor.TextEditorUtils;
import org.texteditor.controllers.TabController;

public class EditMenu extends Menu implements CustomMenu {

    private final TabController tabController;

    public EditMenu(TabController tabController) {
        super("Editar");
        this.tabController = tabController;
    }

    /**
     * Configures the menu items and their associated actions.
     */
    @Override
    public void configure() {
        setId("edit-menu");

        MenuItem undoItem = createNewItem("Desfazer    ", this::onUndoEvent);
        undoItem.setAccelerator(new KeyCodeCombination(KeyCode.Z,
                KeyCombination.CONTROL_DOWN));
        undoItem.setGraphic(TextEditorUtils.createIcon("media/undo.png"));

        MenuItem remakeItem = createNewItem("Refazer    ", this::onRemakeEvent);
        remakeItem.setAccelerator(new KeyCodeCombination(KeyCode.Y,
                KeyCombination.CONTROL_DOWN));
        remakeItem.setGraphic(TextEditorUtils.createIcon("media/remake.png"));

        MenuItem cutItem = createNewItem("Recortar    ", this::onCutEvent);
        cutItem.setAccelerator(new KeyCodeCombination(KeyCode.X,
                KeyCombination.CONTROL_DOWN));
        cutItem.setGraphic(TextEditorUtils.createIcon("media/cut.png"));

        MenuItem copyItem = createNewItem("Copiar    ", this::onCopyEvent);
        copyItem.setAccelerator(new KeyCodeCombination(KeyCode.C,
                KeyCombination.CONTROL_DOWN));
        copyItem.setGraphic(TextEditorUtils.createIcon("media/copy.png"));

        MenuItem pasteItem = createNewItem("Colar    ", this::onPasteEvent);
        pasteItem.setAccelerator(new KeyCodeCombination(KeyCode.V,
                KeyCombination.CONTROL_DOWN));
        pasteItem.setGraphic(TextEditorUtils.createIcon("media/paste.png"));

        MenuItem deleteItem = createNewItem("Apagar    ", this::onDeleteEvent);
        deleteItem.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
        deleteItem.setGraphic(TextEditorUtils.createIcon("media/delete.png"));

        MenuItem selectAllItem = createNewItem("Selecionar tudo    ", this::onSelectAllEvent);
        selectAllItem.setAccelerator(new KeyCodeCombination(KeyCode.A,
                KeyCombination.CONTROL_DOWN));
        selectAllItem.setGraphic(TextEditorUtils.createIcon("media/select_all.png"));

        addComponents(undoItem, remakeItem, cutItem, copyItem,
                pasteItem, deleteItem, selectAllItem);
    }

    private void onUndoEvent() {

    }

    private void onRemakeEvent() {

    }

    private void onCutEvent() {

    }

    private void onCopyEvent() {

    }

    private void onPasteEvent() {

    }

    private void onDeleteEvent() {

    }

    private void onSelectAllEvent() {
        TabPane tabPane = tabController.lookupTabPane();
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        if (selectedTab != null)
            ((TextArea) selectedTab.getContent()).selectAll();
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
