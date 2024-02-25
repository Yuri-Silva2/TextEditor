package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import org.texteditor.controllers.EventController;

import static org.texteditor.Main.createIcon;
import static org.texteditor.viewers.menu.MenuItemBuild.createMenuItem;

/**
 * The FileMenu class represents a menu responsible for handling file-related operations in the text editor.
 * It encapsulates functionality related to creating, opening, saving, and quitting tabs.
 */
public class FileMenu extends Menu implements CustomMenu {

    private final EventController eventController;

    /**
     * Constructor for the FileMenu class.
     *
     * @param eventController The EventController instance for handling events.
     */
    public FileMenu(EventController eventController) {
        super("Arquivo");
        this.eventController = eventController;
    }

    /**
     * Configures the menu items and their associated actions.
     */
    @Override
    public void configure() {
        configureNewTabMenuItem();
        configureOpenMenuItem();
        configureSaveMenuItem();
        configureSaveAsMenuItem();
        configureSaveAllMenuItem();
        configureQuitMenuItem();
    }

    /**
     * Configures the menu item for creating a new tab.
     */
    private void configureNewTabMenuItem() {
        MenuItem newItem = createMenuItem("Nova guia", eventController::onNewTabEvent,
                KeyCode.N, KeyCombination.CONTROL_DOWN);
        newItem.setGraphic(createIcon("add.png"));
        addComponents(newItem);
    }

    /**
     * Configures the menu item for opening a file.
     */
    private void configureOpenMenuItem() {
        MenuItem openItem = createMenuItem("Abrir", eventController::onOpenEvent,
                KeyCode.O, KeyCombination.CONTROL_DOWN);
        openItem.setGraphic(createIcon("open.png"));
        addComponents(openItem);
    }

    /**
     * Configures the menu item for saving a file.
     */
    private void configureSaveMenuItem() {
        MenuItem saveItem = createMenuItem("Salvar", eventController::onSaveEvent,
                KeyCode.S, KeyCombination.CONTROL_DOWN);
        saveItem.setGraphic(createIcon("save.png"));
        addComponents(saveItem);
    }

    /**
     * Configures the menu item for saving a file with a different name.
     */
    private void configureSaveAsMenuItem() {
        MenuItem saveAsItem = createMenuItem("Salvar como", eventController::onSaveAsEvent,
                KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);
        saveAsItem.setGraphic(createIcon("save_as.png"));
        addComponents(saveAsItem);
    }

    /**
     * Configures the menu item for saving all open files.
     */
    private void configureSaveAllMenuItem() {
        MenuItem saveAllItem = createMenuItem("Salvar tudo", eventController::onSaveAllEvent,
                KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);
        saveAllItem.setGraphic(createIcon("save.png"));
        addComponents(saveAllItem);
    }

    /**
     * Configures the menu item for quitting the application.
     */
    private void configureQuitMenuItem() {
        MenuItem quitItem = createMenuItem("Sair", eventController::onQuitEvent,
                KeyCode.F4, KeyCombination.ALT_DOWN);
        quitItem.setGraphic(createIcon("quit.png"));
        addComponents(quitItem);
    }

    /**
     * Adds an array of MenuItems to the menu.
     *
     * @param menuItems MenuItems to be added to the menu.
     */
    private void addComponents(MenuItem... menuItems) {
        getItems().addAll(menuItems);
    }
}
