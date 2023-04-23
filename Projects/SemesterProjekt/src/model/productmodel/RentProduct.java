package model.productmodel;

public class RentProduct extends Product {
    private int rentPeriod;

    public RentProduct(String name, String description, double price, int quantity, long barcode, String location, int rentPeriod) {
        super(name, description, price, quantity, barcode, location);
        this.rentPeriod = rentPeriod;
    }

    public int getRentPeriod() {
        return rentPeriod;
    }

    public void setRentPeriod(int rentPeriod) {
        this.rentPeriod = rentPeriod;
    }

    @Override
    public String toString() {
        return "Navn: " + name +
                "\nBeskrivelse: " + description +
                "\nPris: " + price +
                "\nAntal: " + quantity +
                "\nStregkode: " + barcode +
                "\nLokation: " + location +
                "\nLejeperiode: " + rentPeriod + "dage";
    }
}
