package org.texteditor.viewers.tab.locate;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * The LocateTabBuild class provides static methods for creating various JavaFX nodes commonly used in locate tabs,
 * such as AnchorPane, TextField, Text, CheckBox, and Button.
 */
public class LocateTabBuild {

    /**
     * Creates an AnchorPane with the specified child nodes.
     *
     * @param nodes The child nodes to add to the AnchorPane.
     * @return The AnchorPane with the specified child nodes.
     */
    protected static AnchorPane createAnchorPane(Node... nodes) {
        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(nodes);
        return pane;
    }

    /**
     * Creates a TextField with default width, height, and position.
     *
     * @param yCord The y-coordinate of the TextField's position.
     * @return The TextField with default properties.
     */
    protected static TextField createTextField(double yCord) {
        TextField textField = new TextField();
        textField.setPrefWidth(224.0);
        textField.setPrefHeight(25.0);
        textField.setLayoutX(117.0);
        textField.setLayoutY(yCord);
        return textField;
    }

    /**
     * Creates a Text node with the specified content and position.
     *
     * @param arg The text content of the Text node.
     * @param xCord   The x-coordinate of the Text node's position.
     * @param yCord   The y-coordinate of the Text node's position.
     * @return The Text node with the specified content and position.
     */
    protected static Text createText(String arg, double xCord, double yCord) {
        Text text = new Text(arg);
        text.setLayoutX(xCord);
        text.setLayoutY(yCord);
        return text;
    }

    /**
     * Creates a CheckBox with the specified label and position.
     *
     * @param name  The label text of the CheckBox.
     * @param yCord The y-coordinate of the CheckBox's position.
     * @return The CheckBox with the specified label and position.
     */
    protected static CheckBox createCheckBox(String name, double yCord) {
        CheckBox checkBox = new CheckBox(name);
        checkBox.setPrefWidth(204.0);
        checkBox.setPrefHeight(17.0);
        checkBox.setLayoutX(27.0);
        checkBox.setLayoutY(yCord);
        return checkBox;
    }

    /**
     * Creates a Button with the specified label and position.
     *
     * @param name  The label text of the Button.
     * @param yCord The y-coordinate of the Button's position.
     * @return The Button with the specified label and position.
     */
    protected static Button createButton(String name, double yCord) {
        Button button = new Button(name);
        button.setPrefWidth(145.0);
        button.setPrefHeight(25.0);
        button.setLayoutX(367.0);
        button.setLayoutY(yCord);
        return button;
    }
}
