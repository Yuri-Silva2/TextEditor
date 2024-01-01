package org.texteditor.viewers.pane;

import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.TabController;
import org.texteditor.viewers.menu.FileMenu;

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

        setPrefHeight(48.0);

        MenuBar textMenuBar = createMenuBar("text-menu-bar");
        textMenuBar.setLayoutY(0.0);

        MenuBar iconMenuBar = createMenuBar("icon-menu-bar");
        iconMenuBar.setLayoutY(25.0);

        FileMenu fileMenu = createFileMenu();

        addComponents(textMenuBar, fileMenu);
        addComponents(iconMenuBar, fileMenu);

        getChildren().addAll(textMenuBar, iconMenuBar);
    }

    /**
     * Creates and returns a new MenuBar.
     *
     * @return The created MenuBar
     */
    private MenuBar createMenuBar(String id) {
        MenuBar menuBar = new MenuBar();
        menuBar.setPrefHeight(20.0);
        menuBar.setId(id);
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
    private void createIcons() {

    }

    /**
     * Adds the specified Menu components to the provided MenuBar.
     *
     * @param menuBar   The MenuBar to which the menus will be added
     * @param menus     The Menu components to be added to the MenuBar.
     */
    private void addComponents(MenuBar menuBar, Menu... menus) {
        menuBar.getMenus().addAll(menus);
    }

}
