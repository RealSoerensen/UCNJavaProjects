package model.customermodel;

public abstract class Customer {
    private String name;
    private String address;
    private String phone;
    private String email;
    private int customerId;
    private int pincode;

    public Customer(String name, String address, String phone, String email, int customerId, int pincode) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.customerId = customerId;
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString(){
        return "Navn: " + name +
                "\nAdresse: " + address +
                "\nTlf. nr: " + phone +
                "\nEmail: " + email +
                "\nKunde ID: " + customerId +
                "\nPincode: " + pincode;
    }
}
