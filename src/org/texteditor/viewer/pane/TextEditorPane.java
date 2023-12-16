package org.texteditor.viewer.pane;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import org.texteditor.controller.FileController;
import org.texteditor.controller.TabController;
import org.texteditor.viewer.menu.FileMenu;

public class TextEditorPane extends BorderPane {

    private final FileController fileController;
    private final TabController tabController;

    public TextEditorPane(TabController tabController, FileController fileController) {
        super();
        this.tabController = tabController;
        this.fileController = fileController;
    }

    public void configure() {
        setId("texteditor-borderpane");

        MenuBar menuBar = createMenuBar();

        FileMenu fileMenu = createFileMenu();

        addComponents(menuBar, fileMenu);

        WriterPane writerPane = createWriterPane();

        setTop(menuBar);
        setCenter(writerPane);
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setId("menu-bar");
        return menuBar;
    }

    private FileMenu createFileMenu() {
        FileMenu fileMenu = new FileMenu(tabController, fileController);
        fileMenu.configure();
        return fileMenu;
    }

    private WriterPane createWriterPane() {
        WriterPane writerPane = new WriterPane();
        writerPane.configure();
        return writerPane;
    }

    private void addComponents(MenuBar menuBar, Menu... menus) {
        menuBar.getMenus().addAll(menus);
    }
}
