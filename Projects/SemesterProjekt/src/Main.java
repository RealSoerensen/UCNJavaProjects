import controller.CustomerController;
import controller.EmployeeController;
import controller.OrderController;
import controller.ProductController;
import model.ordermodel.Order;
import gui.menus.LoginMenu;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();
        EmployeeController employeeController = new EmployeeController();
        OrderController orderController = new OrderController();
        ProductController productController = new ProductController();

        employeeController.addEmployee("Mogens", "Admin", "Admin", 3);
        employeeController.addEmployee("Gianna", "Manager", "Manager", 2);
        employeeController.addEmployee("Jakob", "Employee", "Employee", 1);

        customerController.addPrivateCustomer("Mogens", "Mogensens vej", "12345678", "12345678@gmail.com", 4532, "130399-5959");
        customerController.addPrivateCustomer("Gianna", "Gianninis vej", "234523423", "87654321@gmail.com", 3451, "121299-6000");
        customerController.addPrivateCustomer("Jakob", "Jakobsens vej", "234241235", "123456789@gmail.com", 6753, "111199-3435");

        customerController.addBusinessCustomer("Mogens Aps", "Mogensvej 1", "59324252", "mogensaps@gmail.com", 3478, "12345678");
        customerController.addBusinessCustomer("Gianna Aps", "Giannavej 1", "123123123", "giannaaps@gmail.com", 9875, "87654321");
        customerController.addBusinessCustomer("Jakob Aps", "Jakobsvej 1", "321321231", "jakobaps@gmail.com", 1245, "123456789");

        productController.addSaleProduct("Hammer", "Its a hammer", 10, 10, 1, "In your mom");
        productController.addSaleProduct("Screwdriver", "Its a screwdriver", 10, 10, 2, "In your dad");
        productController.addSaleProduct("Nail", "Its a nail", 10, 10, 3, "In your sister");
        productController.addSaleProduct("Saw", "Its a saw", 10, 10, 4, "In your brother");

        productController.addRentProduct("Hammer", "Its a hammer", 10, 10, 5, "In your mom", 6);
        productController.addRentProduct("Screwdriver", "Its a screwdriver", 10, 10, 6, "In your dad", 3);
        productController.addRentProduct("Nail", "Its a nail", 10, 10, 7, "In your sister", 2);
        productController.addRentProduct("Saw", "Its a saw", 10, 10, 8, "In your brother", 1);

        orderController.addOrder(new Date());
        List<Order> orders = orderController.getOrders();
        Order order = orderController.getOrder(orders.get(0).getOrderNo());
        order.addProduct(1, 5);
        order.addProduct(2, 5);
        order.addCustomer("12345678");
        order.setDiscount(0);

        orderController.addOrder(new Date());
        orders = orderController.getOrders();
        Order order2 = orderController.getOrder(orders.get(1).getOrderNo());
        order2.addProduct(3, 5);
        order2.addProduct(4, 5);
        order2.addCustomer("234523423");
        order2.setDiscount(10);

        orderController.addOrder(new Date());
        orders = orderController.getOrders();
        Order order3 = orderController.getOrder(orders.get(2).getOrderNo());
        order3.addProduct(5, 5);
        order3.addProduct(6, 5);
        order3.addCustomer("321321231");
        order3.setDiscount(15);

        LoginMenu loginMenu = new LoginMenu();
        loginMenu.run();
    }
}
