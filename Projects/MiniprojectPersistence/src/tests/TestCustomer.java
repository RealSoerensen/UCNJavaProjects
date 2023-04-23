package tests;

import controller.CustomerController;
import dal.DBConnection;
import dal.DatabaseManager;
import model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomer {
    private static CustomerController customerController;
    static DBConnection dbConnection;
    static Connection con;

    @BeforeAll
    static void setUp() throws SQLException {
        dbConnection = DBConnection.getInstance();
        con = DBConnection.getConnection();
        customerController = new CustomerController(new DatabaseManager());
    }

    @Test
    public void testCreate() throws SQLException {
        // Arrange
        Customer customer1 = new Customer(1, "Test", "Test", "Test");
        Customer customer2 = new Customer(2, "Test", "Test", "Test" );

        // Act
        boolean result = customerController.create(customer1);
        boolean result2 = customerController.create(customer2);

        // Assert
        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    public void testGet() throws SQLException {
        // Arrange
        Customer customer1 = new Customer(1, "Test", "Test", "Test");
        Customer customer2 = new Customer(2, "Test", "Test", "Test" );
        customerController.create(customer1);
        customerController.create(customer2);
        int id = 2;

        // Act
        Customer customer3 = customerController.get(id);

        // Assert
        assertEquals(id, customer3.getCustomerId());
    }

    @Test
    public void testUpdate() throws SQLException {
        // Arrange
        Customer customer1 = new Customer(1, "Test", "Test", "Test");
        Customer customer2 = new Customer(2, "Test", "Test", "Test" );
        customerController.create(customer1);
        customerController.create(customer2);
        int id = 2;
        Customer customer3 = customerController.get(id);
        customer3.setName("Test2");

        // Act
        boolean result = customerController.update(customer3);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testDelete() throws SQLException {
        // Arrange
        Customer customer1 = new Customer(1, "Test", "Test", "Test");
        Customer customer2 = new Customer(2, "Test", "Test", "Test" );
        customerController.create(customer1);
        customerController.create(customer2);
        int id = 2;

        // Act
        boolean result = customerController.delete(id);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testGetAll() throws SQLException {
        // Arrange
        Customer customer1 = new Customer(1, "Test", "Test", "Test");
        Customer customer2 = new Customer(2, "Test", "Test", "Test" );
        customerController.create(customer1);
        customerController.create(customer2);

        // Act
        int result = customerController.getAll().size();

        // Assert
        assertEquals(2, result);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        dbConnection.resetDatabase();
    }

}
