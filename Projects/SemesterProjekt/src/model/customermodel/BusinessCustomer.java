package model.customermodel;

public class BusinessCustomer extends Customer{
    private String cvr;
    private double credit;
    private double discountRate;

    public BusinessCustomer(String name, String address, String phone, String email, int customerId, int pincode, String cvr) {
        super(name, address, phone, email, customerId, pincode);
        this.cvr = cvr;
        credit = 0;
        discountRate = 0;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}
