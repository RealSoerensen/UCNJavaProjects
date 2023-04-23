package controller;

import dal.DatabaseManager;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerCtrIF {
    private DatabaseManager data;

    public CustomerController(DatabaseManager data) {
        setCustomerCtrIF(data);
    }

    private void setCustomerCtrIF(DatabaseManager dbManager) {
        this.data = dbManager;
    }

    @Override
    public boolean create(Customer obj) throws SQLException {
        return data.create(obj);
    }

    @Override
    public Customer get(long id) throws SQLException {
        return data.get(Customer.class, id);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return new ArrayList<>(data.getAll(Customer.class));
    }

    @Override
    public boolean update(Customer obj) throws SQLException {
        return data.update(obj);
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return data.delete(Customer.class, id);
    }
}
