package controller;

import model.SaleOrder;

import java.sql.SQLException;
import java.util.List;

public interface SaleOrderCtrIF {
    boolean create(SaleOrder obj) throws SQLException;

    SaleOrder get(long id) throws SQLException;

    List<SaleOrder> getAll() throws SQLException;

    boolean update(SaleOrder obj) throws SQLException;

    boolean delete(long id) throws SQLException;
}
