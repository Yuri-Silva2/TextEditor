package org.texteditor.viewer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.texteditor.controller.EventController;
import org.texteditor.viewer.CustomViewer;

public class FileMenu extends Menu implements CustomViewer {

    private Stage stage;

    public FileMenu() {
        super("Arquivo");
    }

    @Override
    public void configure() {
        EventController hc = new EventController(stage);

        MenuItem newGuide = new MenuItem("Nova guia");
        MenuItem open = new MenuItem("Abrir");
        MenuItem save = new MenuItem("Salvar");
        MenuItem saveAs = new MenuItem("Salvar como");
        MenuItem saveAll = new MenuItem("Salvar tudo");
        MenuItem quit = new MenuItem("Sair");

        hc.setNewGuideEvent(newGuide);
        hc.openEvent(open);
        hc.saveEvent(save);
        hc.saveAsEvent(saveAs);
        hc.saveAllEvent(saveAll);
        hc.setQuitEvent(quit);

        getItems().addAll(
                newGuide,
                open,
                save,
                saveAs,
                saveAll,
                quit
        );

        setId("file-menu");
    }

    public void defineStage(Stage stage) {
        this.stage = stage;
    }
}
