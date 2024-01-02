package org.texteditor.viewers.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.texteditor.TextEditorUtils;

public class FormatMenu extends Menu implements CustomMenu {

    public FormatMenu() {
        super("Formatar");
    }

    @Override
    public void configure() {
        setId("format-menu");

        MenuItem codificationANSIItem = createNewItem("Codificação em ANSI    ", this::onCodificationANSIEvent);
        codificationANSIItem.setGraphic(TextEditorUtils.createIcon("media/type.png"));

        MenuItem codificationUTF8Item = createNewItem("Codificação em UTF-8    ", this::onCodificationUTF8Event);
        codificationUTF8Item.setGraphic(TextEditorUtils.createIcon("media/type.png"));

        MenuItem convertANSIItem = createNewItem("Converter para ANSI    ", this::onConvertANSIEvent);
        convertANSIItem.setGraphic(TextEditorUtils.createIcon("media/type.png"));

        MenuItem convertUTF8Item = createNewItem("Converter para UTF-8    ", this::onConvertUTF8Event);
        convertUTF8Item.setGraphic(TextEditorUtils.createIcon("media/type.png"));

        addComponents(codificationANSIItem, codificationUTF8Item,
                convertANSIItem, convertUTF8Item);
    }

    private void onCodificationANSIEvent() {

    }

    private void onCodificationUTF8Event() {

    }

    private void onConvertANSIEvent() {

    }

    private void onConvertUTF8Event() {

    }

    /**
     * Creates a MenuItem for creating a new item.
     *
     * @return MenuItem for creating a new item
     */
    private MenuItem createNewItem(String content, Runnable eventHandler) {
        MenuItem newItem = new MenuItem(content);
        newItem.setId("new-item");
        newItem.setOnAction(actionEvent -> eventHandler.run());
        return newItem;
    }

    /**
     * Adds an array of MenuItems to the menu.
     *
     * @param menuItems MenuItems to be added to the menu
     */
    private void addComponents(MenuItem... menuItems) {
        getItems().addAll(menuItems);
    }
}
