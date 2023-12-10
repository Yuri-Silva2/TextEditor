package org.texteditor.viewer.pane;

import javafx.scene.control.TabPane;
import org.texteditor.viewer.CustomViewer;

public class WriterPane extends TabPane implements CustomViewer {

    public WriterPane() {
        super();
    }

    @Override
    public void configure() {
        setId("writer-tabpane");
    }
}
