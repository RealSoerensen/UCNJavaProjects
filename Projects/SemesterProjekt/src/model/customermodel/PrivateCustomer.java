package model.customermodel;

public class PrivateCustomer extends Customer{
    private String cpr;

    public PrivateCustomer(String name, String address, String phone, String email, int customerId, int pincode, String cpr) {
        super(name, address, phone, email, customerId, pincode);
        this.cpr = cpr;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }
}
