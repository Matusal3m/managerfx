package com.manager.managerfx.services;

import com.manager.managerfx.model.entities.Product;
import com.manager.managerfx.model.repositories.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

import static com.manager.managerfx.utils.NumberParser.*;

public class ProductService {
    private final ProductRepository repository = new ProductRepository();

    public ObservableList<Product> getProductsList() {
        List<Product> productList = repository.GetAll();
        return FXCollections.observableArrayList(formatDoubleFromList(productList));
    }

    public void createProduct(CharSequence desc, CharSequence prc) throws Exception {
        String description = desc.toString();
        Double price = parseDecimal(prc);

        if (price == null) {
            throw new Exception("Price could not be handle, probably NULL or NEGATIVE NUMBER");
        };

        Product product = new Product(description, price);

        repository.Save(product);
    }

}

