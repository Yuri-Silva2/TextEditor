package org.texteditor.viewer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.texteditor.controller.EventController;

public class ZoomMenu extends Menu {

    private final EventController eventController;

    public ZoomMenu(EventController eventController) {
        super("Zoom");
        this.eventController = eventController;
    }

    public void configure() {
        setId("zoom-menu");

        MenuItem menuItem25 = createMenuItem25();
        MenuItem menuItem50 = createMenuItem50();
        MenuItem menuItem75 = createMenuItem75();
        MenuItem menuItem100 = createMenuItem100();
        MenuItem menuItem150 = createMenuItem150();
        MenuItem menuItem200 = createMenuItem200();

        addComponents(menuItem25, menuItem50,
                menuItem75, menuItem100,
                menuItem150, menuItem200);
    }

    private MenuItem createMenuItem25() {
        return new MenuItem("25%");
    }

    private MenuItem createMenuItem50() {
        return new MenuItem("50%");
    }

    private MenuItem createMenuItem75() {
        return new MenuItem("75%");
    }

    private MenuItem createMenuItem100() {
        return new MenuItem("100%");
    }

    private MenuItem createMenuItem150() {
        return new MenuItem("150%");
    }

    private MenuItem createMenuItem200() {
        return new MenuItem("200%");
    }

    private void addComponents(MenuItem... menuItems) {
        getItems().addAll(menuItems);
    }
}
