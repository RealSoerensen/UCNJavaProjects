package controller;

import model.ordermodel.Order;
import model.ordermodel.OrderContainer;

import java.util.Date;
import java.util.List;

/**
 * The OrderController class is a controller class that handles the order container
 */
public class OrderController {
    OrderContainer orderContainer;

    public OrderController() {
        orderContainer = OrderContainer.getInstance();
    }

    // Adding an order to the order container.
    public void addOrder(Date date) {
        orderContainer.addOrder(date);
    }

    public void addOrder(Order order) {
        orderContainer.addOrder(order);
    }

    // Removing an order from the order container.
    public boolean removeOrder(long orderNo) {
        boolean result = false;
        Order order = getOrder(orderNo);
        if (order != null) {
            result = orderContainer.removeOrder(order);
        }
        return result;
    }

    /**
     * Find an order by its order number.
     *
     * @param orderNo The order number of the order to be found.
     * @return The order object
     */
    public Order getOrder(long orderNo) {
        Order order = null;
        List<Order> orders = orderContainer.getOrders();
        for(int i = 0; i < orders.size() && order == null; i++) {
            if(orders.get(i).getOrderNo() == orderNo) {
                order = orders.get(i);
            }
        }
        return order;
    }

    public List<Order> getOrders() {
        return orderContainer.getOrders();
    }

    public long getOrderSize() {
        return orderContainer.getOrderSize();
    }
}
