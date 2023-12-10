package org.texteditor.viewer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.viewer.CustomViewer;

public class ViewMenu extends Menu implements CustomViewer {

    public ViewMenu() {
        super("Visualizar");
    }

    @Override
    public void configure() {
        ZoomMenu zoomMenu = new ZoomMenu();
        zoomMenu.configure();

        getItems().addAll(
                zoomMenu,
                new MenuItem("Margens")
        );
        setId("view-menu");
    }
}
