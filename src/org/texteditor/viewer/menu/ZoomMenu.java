package org.texteditor.viewer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.viewer.CustomViewer;

public class ZoomMenu extends Menu implements CustomViewer {

    public ZoomMenu() {
        super("Zoom");
    }

    @Override
    public void configure() {
        getItems().addAll(
                new MenuItem("25%"),
                new MenuItem("50%"),
                new MenuItem("75%"),
                new MenuItem("100%"),
                new MenuItem("150%"),
                new MenuItem("200%"),
                new MenuItem("400%")
        );
        setId("zoom-menu");
    }
}
