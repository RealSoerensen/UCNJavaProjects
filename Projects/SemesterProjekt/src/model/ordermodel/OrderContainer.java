package model.ordermodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OrderContainer {
    private final List<Order> orderContainer;
    private static OrderContainer instance;

    private OrderContainer() {
        orderContainer = new ArrayList<>();
    }

    /**
     * If the instance is null, create a new instance of the class and return it. Otherwise, return the existing instance
     *
     * @return The instance of the OrderContainer class.
     */
    public static OrderContainer getInstance() {
        if (instance == null) {
            instance = new OrderContainer();
        }
        return instance;
    }

    /**
     * This function adds an order to the order container.
     *
     * @param date The date of the order
     */
    public void addOrder(Date date) {
        Random random = new Random();
        long orderNo = random.nextInt(1000000);
        for(int i = 0; i < orderContainer.size(); i++) {
            if(orderContainer.get(i).getOrderNo() == orderNo) {
                orderNo = random.nextInt(1000000);
                i = 0;
            }
        }
        orderContainer.add(new Order(orderNo, date));
    }

    public void addOrder(Order order) {
        orderContainer.add(order);
    }

    /**
     * If the orderContainer contains the order, remove it and return true, otherwise return false.
     *
     * @param order The order to be removed.
     * @return A boolean value.
     */
    public boolean removeOrder(Order order) {
        return orderContainer.remove(order);
    }

    public List<Order> getOrders() {
        return orderContainer;
    }

    public long getOrderSize() {
        return orderContainer.size();
    }
}
