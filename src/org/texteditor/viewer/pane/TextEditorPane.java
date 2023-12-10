package org.texteditor.viewer.pane;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.texteditor.viewer.CustomViewer;
import org.texteditor.viewer.menu.EditMenu;
import org.texteditor.viewer.menu.FileMenu;
import org.texteditor.viewer.menu.ViewMenu;

public class TextEditorPane extends BorderPane implements CustomViewer {

    private Stage stage;

    public TextEditorPane() {
        super();
    }

    @Override
    public void configure() {
        MenuBar menuBar = new MenuBar();
        menuBar.setId("menu-bar");

        FileMenu fileMenu = new FileMenu();
        fileMenu.defineStage(this.stage);
        fileMenu.configure();
        EditMenu editMenu = new EditMenu();
        editMenu.configure();
        ViewMenu viewMenu = new ViewMenu();
        viewMenu.configure();

        menuBar.getMenus().addAll(
                fileMenu,
                editMenu,
                viewMenu
        );

        WriterPane writerPane = new WriterPane();
        writerPane.configure();

        setTop(menuBar);
        setCenter(writerPane);
    }

    public void defineStage(Stage stage) {
        this.stage = stage;
    }
}
