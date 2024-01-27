package org.texteditor;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class IndexColumnDemo extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Table height example");

        TableView<String> table = new TableView<>(FXCollections.observableArrayList(
                "Stacey", "Kristy", "Mary Anne", "Claudia"
        ));

        TableColumn<String, Integer> indexColumn = new TableColumn<>();
        indexColumn.setCellFactory(col -> {
            TableCell<String, Integer> indexCell = new TableCell<>();
            ReadOnlyObjectProperty<TableRow<String>> rowProperty = indexCell.tableRowProperty();
            ObjectBinding<String> rowBinding = Bindings.createObjectBinding(() -> {
                TableRow<String> row = rowProperty.get();
                if (row != null) {
                    int rowIndex = row.getIndex();
                    if (rowIndex < row.getTableView().getItems().size()) {
                        return Integer.toString(rowIndex);
                    }
                }
                return null;
            }, rowProperty);
            indexCell.textProperty().bind(rowBinding);
            return indexCell;
        });

        TableColumn<String, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue()));

        ObservableList<TableColumn<String, ?>> columns = table.getColumns();
        columns.add(indexColumn);
        columns.add(nameColumn);

        Group root = new Group();
        root.getChildren().add(table);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        stage.sizeToScene();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
