package org.texteditor.viewer.pane;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import org.texteditor.controller.EventController;
import org.texteditor.viewer.menu.EditMenu;
import org.texteditor.viewer.menu.FileMenu;
import org.texteditor.viewer.menu.ViewMenu;

public class TextEditorPane extends BorderPane {

    private final EventController eventController;

    public TextEditorPane(EventController eventController) {
        super();
        this.eventController = eventController;
    }

    public void configure() {
        setId("texteditor-borderpane");

        MenuBar menuBar = createMenuBar();

        FileMenu fileMenu = createFileMenu();
        EditMenu editMenu = createEditMenu();
        ViewMenu viewMenu = createViewMenu();

        addComponents(menuBar, fileMenu, editMenu, viewMenu);

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
        FileMenu fileMenu = new FileMenu(eventController);
        fileMenu.configure();
        return fileMenu;
    }

    private EditMenu createEditMenu() {
        EditMenu editMenu = new EditMenu();
        editMenu.configure();
        return editMenu;
    }

    private ViewMenu createViewMenu() {
        ViewMenu viewMenu = new ViewMenu(eventController);
        viewMenu.configure();
        return viewMenu;
    }

    private void addComponents(MenuBar menuBar, Menu... menus) {
        menuBar.getMenus().addAll(menus);
    }

    private WriterPane createWriterPane() {
        WriterPane writerPane = new WriterPane();
        writerPane.configure();
        return writerPane;
    }
}
