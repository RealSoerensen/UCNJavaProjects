package tests;

import controller.CustomerController;
import controller.EmployeeController;
import controller.OrderController;
import controller.ProductController;
import model.employeemodel.Employee;
import model.ordermodel.Order;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UnitTests {
    CustomerController customerController = new CustomerController();
    EmployeeController employeeController = new EmployeeController();
    OrderController orderController = new OrderController();
    ProductController productController = new ProductController();

    @Test
    public void testCustomer() {
        customerController.addPrivateCustomer("Victor", "Test", "123123", "test", 2, "test");
        assertEquals(1, customerController.getAllCustomers().size());
        customerController.addBusinessCustomer("Max", "Test1", "123132", "test1", 2, "test1");
        assertEquals(2, customerController.getAllCustomers().size());
        customerController.removeCustomer("123123");
        assertEquals(1, customerController.getAllCustomers().size());
    }

    @Test
    public void testEmployee() {
        employeeController.addEmployee("Victor", "Test", "123123", 2);
        assertEquals(1, employeeController.getEmployees().size());
        Employee employee = employeeController.getEmployee("Test");
        assertEquals("Victor", employee.getName());
        employeeController.updateName("Test", "Max");
        assertEquals("Max", employeeController.getEmployee("Test").getName());
        employeeController.removeEmployee("Test");
        assertEquals(0, employeeController.getEmployees().size());
    }

    @Test
    public void testOrder() {
        orderController.addOrder(new Date());
        assertEquals(1, orderController.getOrderSize());
        orderController.addOrder(new Date());
        orderController.addOrder(new Date());
        List<Order> orders = orderController.getOrders();
        Order order = orders.get(0);
        orderController.removeOrder(order.getOrderNo());
        assertEquals(2, orderController.getOrderSize());

    }

    @Test
    public void testProduct() {
        productController.addSaleProduct("Victor", "Test", 2, 2, 21231, "test");
        assertEquals(1, productController.getAllProducts().size());
        productController.addRentProduct("Victor", "Test", 2, 2, 2123, "test", 2);
        assertEquals(2, productController.getAllProducts().size());
        productController.removeProduct(2123);
        assertEquals(1, productController.getAllProducts().size());
    }

    @Test
    public void testLoginMenu(){
        employeeController.addEmployee("Victor", "Test", "123123", 2);
        employeeController.removeEmployee("Test");
        employeeController.addEmployee("Max", "Test1", "123132", 2);
        assertEquals(1, employeeController.login("Test1", "123132"));
        employeeController.removeEmployee("Test");
        assertEquals(0, employeeController.login("Test", "123123"));
    }
}
