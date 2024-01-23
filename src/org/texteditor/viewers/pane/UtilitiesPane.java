package org.texteditor.viewers.pane;

import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.texteditor.controllers.EventController;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.TabController;
import org.texteditor.controllers.TextFileController;
import org.texteditor.viewers.menu.*;

import static org.texteditor.TextEditorApplication.createIcon;

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
        setId("icon-pane");
        setStyle("-fx-background-color: transparent;");

        setPrefHeight(48.0);

        MenuBar textMenuBar = createMenuBar();
        ToolBar iconMenuBar = createToolBar();
        iconMenuBar.setStyle("-fx-background-color: transparent;");

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
                createLocateMenu(), createViewMenu(),
                createFormatMenu(), createConfigMenu(),
                createHelpMenu()};
    }

    /**
     * Gets an array of Button components for the UtilityPane.
     *
     * @return An array of Button components.
     */
    private Button[] getButtons() {
        return new Button[]{createButton(createIcon("media/add.png"), "Nova guia", eventController::onNewTabEvent),
                createButton(createIcon("media/open.png"), "Abrir...", eventController::onOpenEvent),
                createButton(createIcon("media/save.png"), "Salvar", eventController::onSaveEvent),
                createButton(createIcon("media/save_as.png"), "Salvar como", eventController::onSaveAsEvent),
                createButton(createIcon("media/save.png"), "Salvar tudo", eventController::onSaveAllEvent),
                createButton(createIcon("media/cut.png"), "Recortar", eventController::onCutEvent),
                createButton(createIcon("media/copy.png"), "Copiar", eventController::onCopyEvent),
                createButton(createIcon("media/paste.png"), "Colar", eventController::onPasteEvent),
                createButton(createIcon("media/undo.png"), "Desfazer", eventController::onUndoEvent),
                createButton(createIcon("media/remake.png"), "Refazer", eventController::onRemakeEvent),
                createButton(createIcon("media/find.png"), "Localizar...", eventController::onLocateEvent),
                createButton(createIcon("media/find_replace.png"), "Substituir...", eventController::onFindAndReplaceEvent),
                createButton(createIcon("media/zoom_in.png"), "Ampliar", eventController::onEnlargeEvent),
                createButton(createIcon("media/zoom_out.png"), "Reduzir", eventController::onReduceEvent),
                createButton(createIcon("media/segment.png"), "Quebrar linhas automaticamente", eventController::onLineStyleEvent)};
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
     * Creates and returns a new LocateMenu, configured with the provided controllers.
     *
     * @return The created LocateMenu
     */
    private LocateMenu createLocateMenu() {
        LocateMenu locateMenu = new LocateMenu(eventController);
        locateMenu.configure();
        return locateMenu;
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
     * Creates and returns a new FormatMenu, configured with the provided controllers.
     *
     * @return The created FormatMenu
     */
    private FormatMenu createFormatMenu() {
        FormatMenu formatMenu = new FormatMenu(eventController);
        formatMenu.configure();
        return formatMenu;
    }

    /**
     * Creates and returns a new ConfigMenu, configured with the provided controllers.
     *
     * @return The created ConfigMenu
     */
    private ConfigMenu createConfigMenu() {
        ConfigMenu configMenu = new ConfigMenu(eventController);
        configMenu.configure();
        return configMenu;
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
