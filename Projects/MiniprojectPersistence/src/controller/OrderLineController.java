package controller;

import dal.DatabaseManager;
import model.OrderLine;

import java.sql.SQLException;
import java.util.List;

public class OrderLineController implements OrderLineCtrIF{
    final DatabaseManager orderLineDBIF;

    public OrderLineController(DatabaseManager orderLineDBIF) {
        this.orderLineDBIF = orderLineDBIF;
    }

    @Override
    public boolean create(OrderLine orderLine) throws SQLException {
        return orderLineDBIF.create(orderLine);
    }

    @Override
    public OrderLine get(long id) throws SQLException {
        return orderLineDBIF.get(OrderLine.class, id);
    }

    @Override
    public List<OrderLine> getAll() throws SQLException {
        return orderLineDBIF.getAll(OrderLine.class);
    }

    @Override
    public boolean update(OrderLine orderLine) throws SQLException {
        return orderLineDBIF.update(orderLine);
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return orderLineDBIF.delete(OrderLine.class, id);
    }
}
