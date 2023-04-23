package tests;

import controller.ProductController;
import controller.SupplierController;
import dal.DBConnection;
import dal.DatabaseManager;
import model.Product;
import model.Supplier;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestProducts {
    static final ProductController productController;

    static {
        try {
            productController = new ProductController(new DatabaseManager());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static final SupplierController supplierController;
    static {
        try {
            supplierController = new SupplierController(new DatabaseManager());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static final DBConnection dbConnection;

    static {
        try {
            dbConnection = DBConnection.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static Connection con = DBConnection.getConnection();

    @BeforeEach
    public void setupEach() {
        Supplier supplier = new Supplier(1, "TestName", "TestAddress", "TestCountry", "TestPhone", "TestEmail");
        supplierController.create(supplier);
    }

    @Test
    public void testCreate() throws SQLException {
        // Arrange
        Product product = new Product(1, 1, "TestName", "TestBrand", 50, 100, "TestCountry", 4, 5, "TestDesc", "TestCategory");

        // Act
        boolean result = productController.create(product);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testProductGet() throws SQLException {
        // Arrange
        Product product = new Product(1, 1, "TestName", "TestBrand", 50, 100, "TestCountry", 4, 5, "TestDesc", "TestCategory");
        productController.create(product);

        // Act
        product = productController.get(1);

        // Assert
        assertEquals(1, product.getProductId());
    }

    @Test
    public void testGetAll() throws SQLException {
        // Arrange
        Product product = new Product(1, 1, "TestName", "TestBrand", 50, 100, "TestCountry", 4, 5, "TestDesc", "TestCategory");

        // Act
        productController.create(product);
        List<Product> products = productController.getAll();

        // Assert
        assertEquals(1, products.size());
    }

    @Test
    public void testProductContainerUpdate() throws SQLException {
        // Arrange
        Product product = new Product(1, 1, "TestName", "TestBrand", 50, 100, "TestCountry", 4, 5, "TestDesc", "TestCategory");

        // Act
        productController.create(product);
        product.setSalesPrice(200);
        productController.update(product);

        // Assert
        Product product2 = productController.get(1);
        assertEquals(200, product2.getSalesPrice());

    }

    @Test
    public void testProductContainerDelete() throws SQLException {
        // Arrange
        Product product = new Product(1, 1, "TestName", "TestBrand", 50, 100, "TestCountry", 4, 5, "TestDesc", "TestCategory");

        // Act
        productController.create(product);
        productController.delete(1);

        // Assert
        Product product2 = productController.get(1);
        assertNull(product2);
    }

    @Test
    public void testProductContainerGetAll() throws SQLException {
        // Arrange
        Product product = new Product(1, 1, "TestName", "TestBrand", 50, 100, "TestCountry", 4, 5, "TestDesc", "TestCategory");
        Product product2 = new Product(2, 1, "TestName", "TestBrand", 50, 100, "TestCountry", 4, 5, "TestDesc", "TestCategory");

        // Act
        productController.create(product);
        productController.create(product2);
        List<Product> products = productController.getAll();

        // Assert
        assertEquals(2, products.size());
    }

    @AfterEach
    public void tearDown() throws SQLException {
        dbConnection.resetDatabase();
    }
}
