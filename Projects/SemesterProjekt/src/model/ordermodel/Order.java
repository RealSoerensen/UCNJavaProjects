package model.ordermodel;

import model.customermodel.Customer;
import model.customermodel.CustomerContainer;
import model.productmodel.Product;
import model.productmodel.ProductContainer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private long orderNo;
    private int discount;
    private Date date;
    private final List<Product> products;
    private Customer customer;
    private final ProductContainer productContainer;
    private final CustomerContainer customerContainer;

    public Order(long orderNo, Date date) {
        this.date = date;
        this.orderNo = orderNo;
        products = new ArrayList<>();
        productContainer = ProductContainer.getInstance();
        customerContainer = CustomerContainer.getInstance();
        customer = null;
    }
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean addProduct(long barcode, int quantity) {
        boolean productAdded = false;
        int i = 0;
        Product product = productContainer.getProduct(barcode);
        while(i < quantity) {
            if(productContainer.getProduct(barcode) != null) {
                productAdded = products.add(product);
            }
            i++;
        }
        return productAdded;
    }

    public boolean addCustomer(String phone) {
        boolean customerAdded = false;
        customer = customerContainer.getCustomer(phone);
        if(customer != null) {
            customerAdded = true;
        }
        return customerAdded;
    }

    public Customer getCustomer() {
        return customer;
    }
}
