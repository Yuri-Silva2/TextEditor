package org.texteditor.viewer.menu;

import javafx.scene.control.Menu;
import org.texteditor.viewer.CustomViewer;

public class EditMenu extends Menu implements CustomViewer {

    public EditMenu() {
        super("Editar");
    }

    @Override
    public void configure() {
        getItems().addAll(
                new javafx.scene.control.MenuItem("Recortar"),
                new javafx.scene.control.MenuItem("Copiar"),
                new javafx.scene.control.MenuItem("Colar"),
                new javafx.scene.control.MenuItem("Duplicar"),
                new javafx.scene.control.MenuItem("Deletar")
        );
        setId("edit-menu");
    }
}
