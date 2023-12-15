package org.texteditor.viewer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class ViewMenu extends Menu {

    public ViewMenu() {
        super("Visualizar");
    }

    public void configure() {
        setId("view-menu");

        ZoomMenu zoomMenu = createZoomMenu();

        getItems().addAll(
                zoomMenu,
                new MenuItem("Margens")
        );
    }

    private ZoomMenu createZoomMenu() {
        ZoomMenu zoomMenu = new ZoomMenu();
        zoomMenu.configure();
        return zoomMenu;
    }
}
