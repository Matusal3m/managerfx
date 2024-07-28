package com.manager.managerfx.model.repositories;

import com.manager.managerfx.connection.SQLiteConnection;
import com.manager.managerfx.model.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IGenericRepository<Product> {

    private static final Connection connection = SQLiteConnection.connect();

    @Override
    public Product Get(int id) {
        String query = "SELECT * FROM product WHERE id = ?";

        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Product> GetAll() {
        String query = "SELECT * FROM main.product";

        List<Product> productList = new ArrayList<>();

        try {
            assert connection != null;
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getFloat("price")
                );

                productList.add(product);
            }

            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Save(Product product) {
        String query = "INSERT INTO product(id, description, price)" +
                "VALUES(?,?,?)";

        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, product.getId());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Update(Product product) {
        String query = "UPDATE product SET description = ?, price = ?" +
                "WHERE id = ?";

        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, product.getDescription());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void Delete(Product product) {
        String query = "DELETE FROM product WHERE id = ?";

        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, product.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
