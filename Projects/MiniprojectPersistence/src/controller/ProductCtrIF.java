package controller;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductCtrIF {
    boolean create(Product obj) throws SQLException;

    Product get(long id) throws SQLException;

    List<Product> getAll() throws SQLException;

    boolean update(Product obj) throws SQLException;

    boolean delete(long id) throws SQLException;
}
