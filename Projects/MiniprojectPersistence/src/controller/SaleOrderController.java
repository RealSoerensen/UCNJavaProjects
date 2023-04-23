package controller;

import dal.DatabaseManager;
import model.SaleOrder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleOrderController implements SaleOrderCtrIF {
    private DatabaseManager orderDBIF;

    public SaleOrderController(DatabaseManager orderDBIF) {
        setOrderCtrIF(orderDBIF);
    }

    private void setOrderCtrIF(DatabaseManager orderDBIF) {
        this.orderDBIF = orderDBIF;
    }

    @Override
    public boolean create(SaleOrder obj) throws SQLException {
        return orderDBIF.create(obj);
    }

    @Override
    public SaleOrder get(long id) throws SQLException {
        return orderDBIF.get(SaleOrder.class, id);
    }

    @Override
    public List<SaleOrder> getAll() throws SQLException {
        return new ArrayList<>(orderDBIF.getAll(SaleOrder.class));
    }

    @Override
    public boolean update(SaleOrder obj) throws SQLException {
        return orderDBIF.update(obj);
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return orderDBIF.delete(SaleOrder.class, id);
    }
}
