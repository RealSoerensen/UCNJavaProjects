package controller;

import model.productmodel.Product;
import model.productmodel.ProductContainer;
import model.productmodel.RentProduct;

import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private final ProductContainer productContainer;

    public ProductController() {
        productContainer = ProductContainer.getInstance();
    }

    // It adds a sale product to the product container.
    public void addSaleProduct(String name, String description, double price, int stock, long barcode, String location) {
        if(checkIfUniqueProduct(barcode)) {
            productContainer.addSaleProduct(name, description, price, stock, barcode, location);
        }
    }

    private boolean checkIfUniqueProduct(long barcode) {
        boolean unique = true;
        List<Product> products = productContainer.getAllProducts();
        int i = 0;
        while (i < products.size() && unique) {
            if (products.get(i).getBarcode() == barcode) {
                unique = false;
            }
            i++;
        }
        return unique;
    }

    /**
     * It adds a rent product to the product container.
     *
     * @param name        The name of the product
     * @param description A description of the product.
     * @param price       The price of the product
     * @param stock       The amount of the product in stock
     * @param barcode     The barcode of the product.
     * @param location    The location of the product in the store.
     * @param rentPeriod  The amount of days the product can be rented for.
     */
    public void addRentProduct(String name, String description, double price, int stock, long barcode, String location, int rentPeriod) {
        if (checkIfUniqueProduct(barcode)) {
            productContainer.addRentProduct(name, description, price, stock, barcode, location, rentPeriod);
        }
    }


    /**
     * Remove a product from the container.
     *
     * @param barcode The barcode of the product to be removed.
     */
    public void removeProduct(long barcode) {
        productContainer.removeProduct(barcode);
    }

    /**
     * Find a product by barcode.
     *
     * @param barcode The barcode of the product to be found.
     * @return The product with the barcode that was passed in.
     */
    public Product getProduct(long barcode) {
        return productContainer.getProduct(barcode);
    }

    public List<Product> getAllProducts() {
        return productContainer.getAllProducts();
    }

    /**
     * If the product exists, return its string representation.
     *
     * @param barcode The barcode of the product to print.
     * @return A string representation of the product.
     */
    public String printProduct(long barcode) {
        String result;
        Product product = getProduct(barcode);
        if(product instanceof RentProduct rentProduct) {
            result = rentProduct.toString();
        } else {
            result = product.toString();
        }
        return result;
    }

    public List<String> printAllProducts() {
        List<String> productStrings = new ArrayList<>();
        for (Product product : productContainer.getAllProducts()) {
            if(product instanceof RentProduct rentProduct) {
                productStrings.add(rentProduct.toString());
            } else {
                productStrings.add(product.toString());
            }
        }
        return productStrings;
    }

    public boolean updateName(long barcode, String name) {
        boolean result = false;
        Product product = getProduct(barcode);
        if (product != null) {
            product.setName(name);
            result = true;
        }
        return result;
    }

    public boolean updatePrice(long barcode, double price) {
        boolean result = false;
        Product product = getProduct(barcode);
        if (product != null) {
            product.setPrice(price);
            result = true;
        }
        return result;
    }

    public boolean updateQuantity(long barcode, int amount) {
        boolean result = false;
        Product product = getProduct(barcode);
        if (product != null) {
            product.setQuantity(amount);
            result = true;
        }
        return result;
    }

    /**
     * If the product exists, update its description.
     *
     * @param barcode The barcode of the product to update.
     * @param description The new description of the product.
     * @return A boolean value.
     */
    public boolean updateDescription(long barcode, String description) {
        boolean result = false;
        Product product = getProduct(barcode);
        if (product != null) {
            product.setDescription(description);
            result = true;
        }
        return result;
    }

    public boolean updateRentPeriod(long barcode, int rentPeriod) {
        boolean result = false;
        Product product = getProduct(barcode);
        if (product instanceof RentProduct rentProduct) {
            rentProduct.setRentPeriod(rentPeriod);
            result = true;
        }
        return result;
    }
}
