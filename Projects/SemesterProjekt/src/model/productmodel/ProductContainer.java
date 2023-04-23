package model.productmodel;

import java.util.ArrayList;
import java.util.List;

public class ProductContainer {
    private static ProductContainer instance;
    private final List<Product> products;

    private ProductContainer() {
        products = new ArrayList<>();
    }

    public static ProductContainer getInstance() {
        if (instance == null) {
            instance = new ProductContainer();
        }
        return instance;
    }

    public boolean addSaleProduct(String name, String description, double price, int quantity, long barcode, String location) {
        boolean result = false;
        if (checkIfUniqueProduct(barcode)) {
            Product product = new SaleProduct(name, description, price, quantity, barcode, location);
            result = products.add(product);
        }
        return result;
    }

    public boolean addRentProduct(String name, String description, double price, int quantity, long barcode, String location, int rentPeriod) {
        boolean result = false;
        if (checkIfUniqueProduct(barcode)) {
            Product product = new RentProduct(name, description, price, quantity, barcode, location, rentPeriod);
            result = products.add(product);
        }
        return result;
    }

    public boolean removeProduct(long barcode) {
        boolean found = false;
        int i = 0;
        while(i < products.size() && !found) {
            if(products.get(i).barcode == barcode) {
                found = true;
                products.remove(i);
            }
            i++;
        }
        return found;
    }

    public Product getProduct(long barcode) {
        boolean found = false;
        int i = 0;
        Product product = null;
        while(i < products.size() && !found) {
            if(products.get(i).barcode == barcode) {
                found = true;
                product = products.get(i);
            }
            i++;
        }
        return product;
    }

    private boolean checkIfUniqueProduct(long barcode) {
        boolean unique = true;
        int i = 0;
        while(i < products.size() && unique) {
            Product product = products.get(i);
            if (product.getBarcode() == barcode) {
                unique = false;
            }
            i++;
        }
        return unique;
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void addProduct(String name, String description, double price, int stock, long barcode, String location) {
        Product product = new SaleProduct(name, description, price, stock, barcode, location);
        products.add(product);
    }
}
