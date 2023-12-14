package org.texteditor.viewer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.controller.EventController;

public class ViewMenu extends Menu {

    private final EventController eventController;

    public ViewMenu(EventController eventController) {
        super("Visualizar");
        this.eventController = eventController;
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
        ZoomMenu zoomMenu = new ZoomMenu(eventController);
        zoomMenu.configure();
        return zoomMenu;
    }
}
