package controller;

import model.Supplier;

import java.sql.SQLException;

public interface SupplierCtrIF {
    boolean create(Supplier supplier);
    Supplier get(long id) throws SQLException;
    boolean update(Supplier supplier);
    boolean delete(long id);
}
