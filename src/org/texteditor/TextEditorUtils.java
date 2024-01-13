package org.texteditor;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.texteditor.controllers.FileController;
import org.texteditor.controllers.TabController;
import org.texteditor.viewers.pane.AlertPane;

public class TextEditorUtils {

    public static ImageView createIcon(String url) {
        Image openIcon = new Image(String.valueOf(TextEditorApplication.class.getResource(url)));
        ImageView iconView = new ImageView(openIcon);
        iconView.setFitHeight(16.0);
        iconView.setFitWidth(16.0);
        return iconView;
    }

    public static void createAlertPane(TabController tabController) {
        Stage newStage = new Stage();
        AlertPane alertPane = new AlertPane(newStage, new FileController(newStage),
                tabController);
        alertPane.configure();

        Scene scene = new Scene(alertPane, 300, 180);

        newStage.setScene(scene);
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.show();
    }
}
