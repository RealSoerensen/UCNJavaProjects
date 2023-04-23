package model.productmodel;

public abstract class Product {
    protected String name;
    protected String description;
    protected double price;
    protected int quantity;
    protected long barcode;
    protected String location;

    public Product(String name, String description, double price, int quantity, long barcode, String location) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.barcode = barcode;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getBarcode() {
        return barcode;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return "Navn: " + name + "\nBeskrivelse: " + description + "\nPris: " + price + "\nAntal: " + quantity + "\nStregkode: " + barcode + "\nLokation: " + location;
    }
}
