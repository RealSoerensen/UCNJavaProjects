package tests;

import controller.*;
import dal.DBConnection;
import dal.DatabaseManager;
import model.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSale {
    final DatabaseManager dbManager = new DatabaseManager();
    final OrderLineController orderLineCtr = new OrderLineController(dbManager);
    final SupplierController supplierCtr = new SupplierController(dbManager);
    final ProductController productCtr = new ProductController(dbManager);
    final SaleOrderController saleOrderCtr = new SaleOrderController(dbManager);
    final CustomerController customerCtr = new CustomerController(dbManager);
    static final DBConnection dbConnection;

    static {
        try {
            dbConnection = DBConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    final Timestamp timestamp = new Timestamp(new Date().getTime());

    public TestSale() throws SQLException {
    }

    @BeforeEach
    public void setupEach() throws SQLException {
        Supplier supplier = new Supplier(1, "TestName", "TestAddress", "TestCountry", "TestPhone", "TestEmail");
        supplierCtr.create(supplier);
        Product product = new Product(1, 1, "TestName", "TestBrand", 50, 100, "TestCountry", 4, 5, "TestDesc", "TestCategory");
        productCtr.create(product);
        OrderLine orderLine = new OrderLine(1, 1, 0.50, 1);
        orderLineCtr.create(orderLine);
        Customer customer = new Customer(1, "TestName", "TestAddress", "TestPhone");
        customerCtr.create(customer);
    }

    @Test
    public void testSale() throws SQLException {
        // Arrange
        double expectedTotal = 100;
        double expectedDiscount = 0.500;
        double expectedFinalTotal = addDiscountToTotal(expectedDiscount, expectedTotal);

        // Act
        Product product = productCtr.get(1);
        long productId = product.getProductId();
        Customer customer = customerCtr.get(1);
        long customerId = customer.getCustomerId();
        OrderLine orderLine = orderLineCtr.get(1);
        long orderLineId = orderLine.getOrderLineId();

        SaleOrder saleOrder = new SaleOrder(1, timestamp, "Not delivered", timestamp, timestamp, customerId, orderLineId);
        saleOrderCtr.create(saleOrder);

        double actualTotal = getTotal(productId);
        double actualDiscount = orderLine.getDiscount();
        double actualFinalTotal = addDiscountToTotal(actualDiscount, actualTotal);

        // Assert
        assertEquals(expectedTotal, actualTotal);
        assertEquals(expectedDiscount, actualDiscount);
        assertEquals(expectedFinalTotal, actualFinalTotal);
        printInvoice(saleOrder);
        updateProductStock(productId, orderLine.getQuantity());
    }

    private void updateProductStock(long productId, int quantity) throws SQLException {
        Product product = productCtr.get(productId);
        product.setStock(product.getStock() - quantity);
        if(product.getStock() < product.getMinStock()) {
            product.setStock(product.getMinStock()+20);
        }
        productCtr.update(product);
    }

    public double getTotal(long productId) {
        double total = 0;
        try {
            List<OrderLine> orderLines = orderLineCtr.getAll();
            for (OrderLine orderLine : orderLines) {
                if (orderLine.getProductId() == productId) {
                    total += orderLine.getQuantity() * productCtr.get(orderLine.getProductId()).getSalesPrice();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public double addDiscountToTotal(double discount, double total) {
        if(0 > discount || discount > 1) {
            throw new IllegalArgumentException("Discount must be between 0 and 1");
        }
        return (total - (total * discount));
    }

    private void printInvoice(SaleOrder saleOrder) throws SQLException {
        OrderLine orderLine = orderLineCtr.get(saleOrder.getOrderLineId());
        Product product = productCtr.get(orderLine.getProductId());
        Customer customer = customerCtr.get(saleOrder.getCustomerId());
        Supplier supplier = supplierCtr.get(product.getSupplierId());
        System.out.println("*** Invoice ***");
        System.out.println("Date: " + saleOrder.getDate());
        System.out.println("Customer name: " + customer.getName());
        System.out.println("Customer address: " + customer.getAddress());
        System.out.println("Customer phone: " + customer.getPhoneNo());
        System.out.println("Paymentdate: " + saleOrder.getPaymentDate());
        System.out.println("Deliverydate: " + saleOrder.getDeliveryDate());
        System.out.println("Delivery Status: " + saleOrder.getDeliveryStatus());
        System.out.println("Product name: " + product.getName());
        System.out.println("Product brand: " + product.getBrand());
        System.out.println("Product price: " + product.getSalesPrice());
        System.out.println("Product quantity: " + orderLine.getQuantity());
        System.out.println("Product discount: " + orderLine.getDiscount());
        System.out.println("Product total: " + getTotal(product.getProductId()));
        System.out.println("Supplier name: " + supplier.getName());
        System.out.println("Supplier address: " + supplier.getAddress());
        System.out.println("Supplier country: " + supplier.getPhoneNo());
    }

    @AfterEach
    public void tearDownEach() throws SQLException {
        dbConnection.resetDatabase();
    }

    @AfterAll
    public static void tearDownAll() {
        DBConnection.closeConnection();
    }
}
