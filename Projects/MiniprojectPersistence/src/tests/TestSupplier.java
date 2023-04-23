package tests;

import controller.SupplierController;
import dal.DBConnection;
import dal.DatabaseManager;
import model.Supplier;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestSupplier {
    private static SupplierController supplierController;
    static DBConnection dbConnection;
    static Connection con;

    @BeforeAll
    static void setUp() throws SQLException {
        dbConnection = DBConnection.getInstance();
        con = DBConnection.getConnection();
        supplierController = new SupplierController(new DatabaseManager());
    }

    @Test
    void testCreate() {
        // Arrange
        Supplier supplier = new Supplier(1, "Test", "Test", "Test", "Test", "Test");
        Supplier supplier2 = new Supplier(2, "Test", "Test", "Test", "Test", "Test");

        // Act
        boolean result = supplierController.create(supplier);
        boolean result2 = supplierController.create(supplier2);

        // Assert
        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    void testGet() throws SQLException {
        // Arrange
        Supplier supplier = new Supplier(1, "Test", "Test", "Test", "Test", "Test");
        Supplier supplier2 = new Supplier(2, "Test", "Test", "Test", "Test", "Test");
        supplierController.create(supplier);
        supplierController.create(supplier2);
        int id = 2;

        // Act
        Supplier supplier3 = supplierController.get(id);

        // Assert
        assertEquals(id, supplier3.getSupplierId());
    }

    @Test
    void testUpdate() {
        // Arrange
        Supplier supplier = new Supplier(1, "Test", "Test", "Test", "Test", "Test");
        supplierController.create(supplier);

        // Act
        supplier.setName("Test2");
        boolean result = supplierController.update(supplier);

        // Assert
        assertTrue(result);
    }

    @Test
    void testDelete() {
        // Arrange
        Supplier supplier = new Supplier(1, "Test", "Test", "Test", "Test", "Test");
        supplierController.create(supplier);
        int id = 1;

        // Act
        boolean result = supplierController.delete(id);

        // Assert
        assertTrue(result);
    }

    @AfterEach
    void tearDown() throws SQLException {
        dbConnection.resetDatabase();
    }
}
