package org.texteditor.viewer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.controller.EventController;

public class FileMenu extends Menu {

    private final EventController eventController;

    public FileMenu(EventController eventController) {
        super("Arquivo");
        this.eventController = eventController;
    }

    public void configure() {
        setId("file-menu");

        MenuItem newItem = createNewItem();
        MenuItem openItem = createOpenItem();
        MenuItem saveItem = createSaveItem();
        MenuItem saveAsItem = createSaveAsItem();
        MenuItem saveAllItem = createSaveAllItem();
        MenuItem quitItem = createQuitItem();

        addComponents(newItem, openItem, saveItem,
                saveAsItem, saveAllItem, quitItem);
    }

    private MenuItem createNewItem() {
        MenuItem newItem = new MenuItem("Nova guia");
        eventController.onNewTabEvent(newItem);
        return newItem;
    }

    private MenuItem createOpenItem() {
        MenuItem openItem = new MenuItem("Abrir");
        eventController.onOpenEvent(openItem);
        return openItem;
    }

    private MenuItem createSaveItem() {
        MenuItem saveItem = new MenuItem("Salvar");
        eventController.onSaveEvent(saveItem);
        return saveItem;
    }

    private MenuItem createSaveAsItem() {
        MenuItem saveAsItem = new MenuItem("Salvar como");
        eventController.onSaveAsEvent(saveAsItem);
        return saveAsItem;
    }

    private MenuItem createSaveAllItem() {
        MenuItem saveAllItem = new MenuItem("Salvar tudo");
        eventController.onSaveAllEvent(saveAllItem);
        return saveAllItem;
    }

    private MenuItem createQuitItem() {
        MenuItem quitItem = new MenuItem("Sair");
        eventController.onQuitEvent(quitItem);
        return quitItem;
    }

    private void addComponents(MenuItem... menuItems) {
        getItems().addAll(menuItems);
    }
}
