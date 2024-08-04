package com.manager.managerfx.controllers;

import com.manager.managerfx.model.entities.Product;
import com.manager.managerfx.services.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class ProductsController implements Initializable {

    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<Product, String> descColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TextField descField;
    /* TODO: Handle paste method in priceField */
    @FXML
    private TextField priceField;

    private final ProductService service = new ProductService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.setItems(service.getProductsList());
    }

    @FXML
    private void handleProductRegister() throws Exception {
        service.createProduct(
                descField.getCharacters(),
                priceField.getCharacters()
        );

        refreshTable();
        cleanTextFields(List.of(descField, priceField));
    }

    @FXML
    private void handlePriceField() {
        UnaryOperator<TextFormatter.Change> filter = change -> {

            if (change.isAdded()) {
                if (change.getControlText().contains(".")) {
                    if (change.getText().matches("[^0-9]")) {
                        change.setText("");
                        System.out.println(change.getText());
                    }
                } else if (change.getText().matches("[^0-9.]")) {
                    change.setText("");
                    System.out.println(change.getText());
                }
            }

            return change;
        };
        priceField.setTextFormatter(new TextFormatter<>(filter));
    }

    private void refreshTable() {
        table.setItems(service.getProductsList());
    }

    private void cleanTextFields(List<TextField> fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

}