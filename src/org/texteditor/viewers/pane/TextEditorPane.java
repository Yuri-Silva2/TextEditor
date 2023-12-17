package org.texteditor.viewers.pane;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.TabController;
import org.texteditor.viewers.menu.FileMenu;

/**
 * The TextEditorPane class represents the main pane for the text editor application,
 * containing a MenuBar and a WriterPane, and is responsible for configuring the layout.
 */
public class TextEditorPane extends BorderPane {

    private final FileController fileController;
    private final TabController tabController;

    public TextEditorPane(TabController tabController, FileController fileController) {
        super();
        this.tabController = tabController;
        this.fileController = fileController;
    }

    /**
     * Configures the layout and components of the TextEditorPane.
     * Sets and ID, creates a MenuBar, FileMenu, and WriterPane, and adds them to the BorderPane.
     */
    public void configure() {
        setId("texteditor-borderpane");

        MenuBar menuBar = createMenuBar();
        FileMenu fileMenu = createFileMenu();

        addComponents(menuBar, fileMenu);

        WriterPane writerPane = createWriterPane();

        setTop(menuBar);
        setCenter(writerPane);
    }

    /**
     * Creates and returns a new MenuBar.
     *
     * @return The created MenuBar
     */
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setId("menu-bar");
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
     * Creates and returns a new WriterPane, configured with default settings.
     *
     * @return The created WriterPane
     */
    private WriterPane createWriterPane() {
        WriterPane writerPane = new WriterPane();
        writerPane.configure();
        return writerPane;
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
