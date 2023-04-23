package controller;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerCtrIF {
    boolean create(Customer obj) throws SQLException;

    Customer get(long id) throws SQLException;

    List<Customer> getAll() throws SQLException;

    boolean update(Customer obj) throws SQLException;

    boolean delete(long id) throws SQLException;
}
