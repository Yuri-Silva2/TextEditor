package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import org.texteditor.controllers.EventController;

import static org.texteditor.TextEditorApplication.createIcon;
import static org.texteditor.viewers.menu.MenuItemCreator.createMenuItem;

/**
 * The EditMenu class represents a menu responsible for handling edit-related operations in the text editor.
 * It encapsulates functionality related to undo, redo, cut, copy, paste, delete, and select all operations.
 */
public class EditMenu extends Menu implements CustomMenu {

    private final EventController eventController;

    /**
     * Constructor for the EditMenu class.
     *
     * @param eventController The EventController instance for handling events.
     */
    public EditMenu(EventController eventController) {
        super("Editar");
        this.eventController = eventController;
    }

    /**
     * Configures the menu items and their associated actions.
     */
    @Override
    public void configure() {
        setId("edit-menu");
        configureUndoMenuItem();
        configureRemakeMenuItem();
        configureCutMenuItem();
        configureCopyMenuItem();
        configurePasteMenuItem();
        configureDeleteMenuItem();
        configureSelectAllMenuItem();
    }

    /**
     * Configures the menu item for undoing an action.
     */
    private void configureUndoMenuItem() {
        MenuItem undoItem = createMenuItem("Desfazer", eventController::onUndoEvent,
                KeyCode.Z, KeyCombination.CONTROL_DOWN);
        undoItem.setGraphic(createIcon("media/undo.png"));
        addComponents(undoItem);
    }

    /**
     * Configures the menu item for redoing an action.
     */
    private void configureRemakeMenuItem() {
        MenuItem undoItem = createMenuItem("Refazer", eventController::onRemakeEvent,
                KeyCode.Y, KeyCombination.CONTROL_DOWN);
        undoItem.setGraphic(createIcon("media/remake.png"));
        addComponents(undoItem);
    }

    /**
     * Configures the menu item for cutting selected text.
     */
    private void configureCutMenuItem() {
        MenuItem cutItem = createMenuItem("Recortar", eventController::onCutEvent,
                KeyCode.X, KeyCombination.CONTROL_DOWN);
        cutItem.setGraphic(createIcon("media/cut.png"));
        addComponents(cutItem);
    }

    /**
     * Configures the menu item for copying selected text.
     */
    private void configureCopyMenuItem() {
        MenuItem copyItem = createMenuItem("Copiar", eventController::onCopyEvent,
                KeyCode.C, KeyCombination.CONTROL_DOWN);
        copyItem.setGraphic(createIcon("media/copy.png"));
        addComponents(copyItem);
    }

    /**
     * Configures the menu item for pasting copied or cut text.
     */
    private void configurePasteMenuItem() {
        MenuItem pasteItem = createMenuItem("Colar", eventController::onPasteEvent,
                KeyCode.C, KeyCombination.CONTROL_DOWN);
        pasteItem.setGraphic(createIcon("media/paste.png"));
        addComponents(pasteItem);
    }

    /**
     * Configures the menu item for deleting selected text.
     */
    private void configureDeleteMenuItem() {
        MenuItem deleteItem = createMenuItem("Deletar", eventController::onDeleteEvent,
                KeyCode.DELETE);
        deleteItem.setGraphic(createIcon("media/delete.png"));
        addComponents(deleteItem);
    }

    /**
     * Configures the menu item for selecting all text in the editor.
     */
    private void configureSelectAllMenuItem() {
        MenuItem selectAllItem = createMenuItem("Selecionar tudo", eventController::onSelectAllEvent,
                KeyCode.A, KeyCombination.CONTROL_DOWN);
        selectAllItem.setGraphic(createIcon("media/select_all.png"));
        addComponents(selectAllItem);
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
