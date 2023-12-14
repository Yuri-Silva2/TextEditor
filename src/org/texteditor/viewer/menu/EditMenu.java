package org.texteditor.viewer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class EditMenu extends Menu {

    public EditMenu() {
        super("Editar");
    }

    public void configure() {
        setId("edit-menu");

        MenuItem cutItem = createCutItem();
        MenuItem copyItem = createCopyItem();
        MenuItem pasteItem = createPasteItem();
        MenuItem duplicateItem = createDuplicateItem();
        MenuItem deleteItem = createDeleteItem();

        addComponents(cutItem, copyItem, pasteItem,
                duplicateItem, deleteItem);
    }

    private MenuItem createCutItem() {
        return new MenuItem("Recortar");
    }

    private MenuItem createCopyItem() {
        return new MenuItem("Copiar");
    }

    private MenuItem createPasteItem() {
        return new MenuItem("Colar");
    }

    private MenuItem createDuplicateItem() {
        return new MenuItem("Duplicar");
    }

    private MenuItem createDeleteItem() {
        return new MenuItem("Deletar");
    }

    private void addComponents(MenuItem... menuItems) {
        getItems().addAll(menuItems);
    }
}
