package controller;

import model.OrderLine;

import java.sql.SQLException;
import java.util.List;

public interface OrderLineCtrIF {
    boolean create(OrderLine orderLine) throws SQLException;
    OrderLine get(long id) throws SQLException;
    List<OrderLine> getAll() throws SQLException;
    boolean update(OrderLine orderLine) throws SQLException;
    boolean delete(long id) throws SQLException;
}
