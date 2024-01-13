package org.texteditor.viewers.pane;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.TabController;
import org.texteditor.viewers.menu.*;

public class UtilitiesPane extends AnchorPane {

    private final FileController fileController;
    private final TabController tabController;

    public UtilitiesPane(TabController tabController, FileController fileController) {
        super();
        this.tabController = tabController;
        this.fileController = fileController;
    }

    /**
     * Configures the layout and components of the UtilitiesPane.
     * Sets and ID, creates a two MenuBar's, FileMenu, IconMenu, and adds them to the AnchorPane.
     */
    public void configure() {
        setId("icon-pane");
        setStyle("-fx-background-color: transparent;");

        setPrefHeight(48.0);

        MenuBar textMenuBar = createMenuBar("text-menu-bar");
        textMenuBar.setLayoutY(0.0);

        MenuBar iconMenuBar = createMenuBar("icon-menu-bar");
        iconMenuBar.setLayoutY(25.0);

        Menu[] menus = {createFileMenu(), createEditMenu(),
                createLocateMenu(), createViewMenu(),
                createFormatMenu(), createConfigMenu(),
                createHelpMenu()};

        addComponents(textMenuBar, menus);
        //addComponents(iconMenuBar, fileMenu);

        getChildren().addAll(textMenuBar, iconMenuBar);
    }

    /**
     * Creates and returns a new MenuBar.
     *
     * @return The created MenuBar
     */
    private MenuBar createMenuBar(String id) {
        MenuBar menuBar = new MenuBar();
        menuBar.setId(id);
        menuBar.setPrefHeight(16.0);
        return menuBar;
    }

    /**
     * Creates and returns a new FileMenu, configured with the provided controllers.
     *
     * @return The created FileMenu
     */
    private FileMenu createFileMenu() {
        FileMenu fileMenu = new FileMenu(tabController, fileController);
        fileMenu.configure();
        return fileMenu;
    }

    /**
     * Creates and returns a new EditMenu, configured with the provided controllers.
     *
     * @return The created EditMenu
     */
    private EditMenu createEditMenu() {
        EditMenu editMenu = new EditMenu(tabController);
        editMenu.configure();
        return editMenu;
    }

    /**
     * Creates and returns a new LocateMenu, configured with the provided controllers.
     *
     * @return The created LocateMenu
     */
    private LocateMenu createLocateMenu() {
        LocateMenu locateMenu = new LocateMenu();
        locateMenu.configure();
        return locateMenu;
    }

    /**
     * Creates and returns a new ViewMenu, configured with the provided controllers.
     *
     * @return The created ViewMenu
     */
    private ViewMenu createViewMenu() {
        ViewMenu viewMenu = new ViewMenu();
        viewMenu.configure();
        return viewMenu;
    }

    /**
     * Creates and returns a new FormatMenu, configured with the provided controllers.
     *
     * @return The created FormatMenu
     */
    private FormatMenu createFormatMenu() {
        FormatMenu formatMenu = new FormatMenu();
        formatMenu.configure();
        return formatMenu;
    }

    /**
     * Creates and returns a new ConfigMenu, configured with the provided controllers.
     *
     * @return The created ConfigMenu
     */
    private ConfigMenu createConfigMenu() {
        ConfigMenu configMenu = new ConfigMenu();
        configMenu.configure();
        return configMenu;
    }

    /**
     * Creates and returns a new HelpMenu, configured with the provided controllers.
     *
     * @return The created HelpMenu
     */
    private HelpMenu createHelpMenu() {
        HelpMenu helpMenu = new HelpMenu();
        helpMenu.configure();
        return helpMenu;
    }

    private void createIcons() {

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

}
