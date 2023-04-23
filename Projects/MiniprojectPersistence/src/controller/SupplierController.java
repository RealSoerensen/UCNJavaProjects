package controller;

import dal.DatabaseManager;
import model.Supplier;

import java.sql.SQLException;

public class SupplierController implements SupplierCtrIF{
    private final DatabaseManager supplierDBIF;

    public SupplierController(DatabaseManager supplierDBIF) {
        this.supplierDBIF = supplierDBIF;
    }

    @Override
    public boolean create(Supplier supplier) {
        boolean result = false;
        try {
            result = supplierDBIF.create(supplier);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Supplier get(long id) throws SQLException {
        return supplierDBIF.get(Supplier.class, id);
    }

    @Override
    public boolean update(Supplier supplier) {
        boolean result = false;
        try {
            result = supplierDBIF.update(supplier);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        try {
            result = supplierDBIF.delete(Supplier.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
