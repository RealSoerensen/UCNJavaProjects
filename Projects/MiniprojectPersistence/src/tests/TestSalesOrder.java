package tests;

import controller.*;
import dal.DBConnection;
import dal.DatabaseManager;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TestSalesOrder {
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

    public TestSalesOrder() throws SQLException {
    }

    @BeforeEach
    public void setupEach() throws SQLException {
        dbConnection.resetDatabase();
        Supplier supplier = new Supplier(1, "TestName", "TestAddress", "TestCountry", "TestPhone", "TestEmail");
        supplierCtr.create(supplier);
        Product product = new Product(1, 1, "TestName", "TestBrand", 50, 100, "TestCountry", 4, 5, "TestDesc", "TestCategory");
        productCtr.create(product);
        OrderLine orderLine = new OrderLine(1, 1, 1, 1);
        orderLineCtr.create(orderLine);
        Customer customer = new Customer(1, "TestName", "TestAddress", "TestPhone");
        customerCtr.create(customer);

    }

    @Test
    public void testCreate() throws SQLException {
        // Arrange
        SaleOrder saleOrder = new SaleOrder(1, timestamp, "test", timestamp, timestamp, 1, 1);


        // Act
        boolean result = saleOrderCtr.create(saleOrder);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testGet() throws SQLException {
        // Arrange
        SaleOrder saleOrder = new SaleOrder(1, timestamp, "test", timestamp, timestamp, 1, 1);
        saleOrderCtr.create(saleOrder);

        int id = 1;

        // Act
        SaleOrder result = saleOrderCtr.get(id);

        // Assert
        assertEquals(id, result.getSaleOrderId());
    }

    @Test
    public void testGetAll() throws SQLException {
        // Arrange
        SaleOrder saleOrder = new SaleOrder(1, timestamp, "test", timestamp, timestamp, 1, 1);
        saleOrderCtr.create(saleOrder);

        // Act
        int result = saleOrderCtr.getAll().size();

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void testUpdate() throws SQLException {
        // Arrange
        SaleOrder saleOrder = new SaleOrder(1, timestamp, "test", timestamp, timestamp, 1, 1);
        saleOrderCtr.create(saleOrder);
        SaleOrder saleOrder2 = new SaleOrder(1, timestamp, "test2", timestamp, timestamp, 1, 1);

        // Act
        boolean result = saleOrderCtr.update(saleOrder2);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testDelete() throws SQLException {
        // Arrange
        SaleOrder saleOrder = new SaleOrder(1, timestamp, "test", timestamp, timestamp, 1, 1);
        saleOrderCtr.create(saleOrder);

        // Act
        boolean result = saleOrderCtr.delete(1);

        // Assert
        assertTrue(result);
    }

    @AfterEach
    public void tearDownEach() throws SQLException {
        dbConnection.resetDatabase();
    }
}
