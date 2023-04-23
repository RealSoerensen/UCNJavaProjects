package controller;

import dal.DatabaseManager;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController implements ProductCtrIF{
    private DatabaseManager productDBIF;

    public ProductController(DatabaseManager productCtrIF) {
        setProductCtrIF(productCtrIF);
    }

    private void setProductCtrIF(DatabaseManager productCtrIF) {
        this.productDBIF = productCtrIF;
    }

    @Override
    public boolean create(Product obj) throws SQLException {
        return productDBIF.create(obj);
    }

    @Override
    public Product get(long id) throws SQLException {
        return productDBIF.get(Product.class, id);
    }

    @Override
    public List<Product> getAll() throws SQLException {
        return new ArrayList<>(productDBIF.getAll(Product.class));
    }

    @Override
    public boolean update(Product obj) throws SQLException {
        return productDBIF.update(obj);
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return productDBIF.delete(Product.class, id);
    }
}
