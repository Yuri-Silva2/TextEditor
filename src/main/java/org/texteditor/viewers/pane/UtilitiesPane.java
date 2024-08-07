package org.texteditor.viewers.pane;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.texteditor.controllers.EventController;
import org.texteditor.viewers.menu.*;

import static org.texteditor.Main.createIcon;

/**
 * Represents the UtilitiesPane in a text editor application, providing quick access to various actions.
 * Extends JavaFX AnchorPane and includes methods for configuring layout and components.
 */
public class UtilitiesPane extends AnchorPane {

    private final EventController eventController;

    /**
     * Constructor for the UtilitiesPane class.
     *
     * @param eventController The EventController responsible for handling utility-related events.
     */
    public UtilitiesPane(EventController eventController) {
        super();
        this.eventController = eventController;
    }

    /**
     * Configures the layout and components of the UtilitiesPane.
     * Sets and ID, creates a two MenuBar's, FileMenu, IconMenu, and adds them to the AnchorPane.
     */
    public void configure() {
        setStyle("-fx-background-color: transparent;");

        setPrefHeight(48.0);

        MenuBar textMenuBar = createMenuBar();
        ToolBar iconMenuBar = createToolBar();

        addComponents(textMenuBar, getMenus());
        addComponents(iconMenuBar, getButtons());

        getChildren().addAll(textMenuBar, iconMenuBar);
    }

    /**
     * Gets an array of Menu components for the UtilityPane.
     *
     * @return An array of Menu components.
     */
    private Menu[] getMenus() {
        return new Menu[]{createFileMenu(), createEditMenu(),
                createFindMenu(), createViewMenu(),
                createHelpMenu()};
    }

    /**
     * Gets an array of Button components for the UtilityPane.
     *
     * @return An array of Button components.
     */
    private Button[] getButtons() {
        return new Button[]{createButton(createIcon("add.png"), "Nova guia", eventController::onNewTabEvent),
                createButton(createIcon("open.png"), "Abrir...", eventController::onOpenEvent),
                createButton(createIcon("save.png"), "Salvar", eventController::onSaveEvent),
                createButton(createIcon("save_as.png"), "Salvar como", eventController::onSaveAsEvent),
                createButton(createIcon("save.png"), "Salvar tudo", eventController::onSaveAllEvent),
                createButton(createIcon("cut.png"), "Recortar", eventController::onCutEvent),
                createButton(createIcon("copy.png"), "Copiar", eventController::onCopyEvent),
                createButton(createIcon("paste.png"), "Colar", eventController::onPasteEvent),
                createButton(createIcon("undo.png"), "Desfazer", eventController::onUndoEvent),
                createButton(createIcon("remake.png"), "Refazer", eventController::onRemakeEvent),
                createButton(createIcon("find.png"), "Localizar...", eventController::onFindEvent),
                createButton(createIcon("find_replace.png"), "Substituir...", eventController::onFindAndReplaceEvent),
                createButton(createIcon("zoom_in.png"), "Ampliar", eventController::onEnlargeEvent),
                createButton(createIcon("zoom_out.png"), "Reduzir", eventController::onReduceEvent)};
    }

    /**
     * Creates and returns a new Button with the specified ImageView, tooltip, and event handler.
     *
     * @param view         The ImageView for the Button.
     * @param toolTip      The tooltip for the Button.
     * @param eventHandler The event handler for the Button.
     * @return The created Button.
     */
    private Button createButton(ImageView view, String toolTip, Runnable eventHandler) {
        Button button = new Button("", view);
        button.setStyle("-fx-background-color: transparent;");
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Tooltip buttonToolTip = new Tooltip(toolTip);
        buttonToolTip.setStyle("-fx-background-color: white;");
        button.setTooltip(buttonToolTip);
        button.setOnAction(event -> eventHandler.run());
        return button;
    }

    /**
     * Creates and returns a new MenuBar.
     *
     * @return The created MenuBar
     */
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setId("text-menu-bar");
        menuBar.setPrefHeight(16.0);
        menuBar.setLayoutY(0.0);
        return menuBar;
    }

    /**
     * Creates and returns a new ToolBar.
     *
     * @return The created ToolBar
     */
    private ToolBar createToolBar() {
        ToolBar toolBar = new ToolBar();
        toolBar.setId("icon-tool-bar");
        toolBar.setPrefHeight(16.0);
        toolBar.setLayoutY(25.0);
        return toolBar;
    }

    /**
     * Creates and returns a new FileMenu, configured with the provided controllers.
     *
     * @return The created FileMenu
     */
    private FileMenu createFileMenu() {
        FileMenu fileMenu = new FileMenu(eventController);
        fileMenu.configure();
        return fileMenu;
    }

    /**
     * Creates and returns a new EditMenu, configured with the provided controllers.
     *
     * @return The created EditMenu
     */
    private EditMenu createEditMenu() {
        EditMenu editMenu = new EditMenu(eventController);
        editMenu.configure();
        return editMenu;
    }

    /**
     * Creates and returns a new FindMenu, configured with the provided controllers.
     *
     * @return The created FindMenu
     */
    private FindMenu createFindMenu() {
        FindMenu findMenu = new FindMenu(eventController);
        findMenu.configure();
        return findMenu;
    }

    /**
     * Creates and returns a new ViewMenu, configured with the provided controllers.
     *
     * @return The created ViewMenu
     */
    private ViewMenu createViewMenu() {
        ViewMenu viewMenu = new ViewMenu(eventController);
        viewMenu.configure();
        return viewMenu;
    }

    /**
     * Creates and returns a new HelpMenu, configured with the provided controllers.
     *
     * @return The created HelpMenu
     */
    private HelpMenu createHelpMenu() {
        HelpMenu helpMenu = new HelpMenu(eventController);
        helpMenu.configure();
        return helpMenu;
    }

    /**
     * Adds the specified Menu components to the provided MenuBar.
     *
     * @param menuBar The MenuBar to which the menus will be added
     * @param menus   The Menu components to be added to the MenuBar.
     */
    private void addComponents(MenuBar menuBar, Menu... menus) {
        menuBar.getMenus().addAll(menus);
    }

    /**
     * Adds the specified Button components to the provided ToolBar.
     *
     * @param toolBar The ToolBar to which the buttons will be added.
     * @param buttons The Button components to be added to the ToolBar.
     */
    private void addComponents(ToolBar toolBar, Button... buttons) {
        toolBar.getItems().addAll(buttons);
    }
}
