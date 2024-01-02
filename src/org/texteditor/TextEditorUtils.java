package org.texteditor;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TextEditorUtils {

    public static ImageView createIcon(String url) {
        Image openIcon = new Image(String.valueOf(TextEditorApplication.class.getResource(url)));
        ImageView iconView = new ImageView(openIcon);
        iconView.setFitHeight(16.0);
        iconView.setFitWidth(16.0);
        return iconView;
    }
}
