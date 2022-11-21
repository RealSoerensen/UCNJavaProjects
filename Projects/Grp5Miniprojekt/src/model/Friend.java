package model;

/**
 * Friend class.
 * 
 * @author Patrick Sørensen
 * @version initial draft version
 */
public class Friend {
    // instance variables
    private String name;
    private String address;
    private String postalCode;
    private String city;
    private String phone;
    private LoanContainer loanContainer;

    /**
     * Constructor for objects of class Friend
     * 
     * @param name
     * @param address
     * @param postalCode
     * @param city
     * @param phone
     */
    public Friend(String name, String address, String postalCode, String city,
            String phone) {
        // initialise instance variables
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.phone = phone;
        loanContainer = new LoanContainer();
    }

    /**
     * returns the name of the Friend.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * returns the address of the Friend.
     * 
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * return an array of the LPs loaned by the Friend.
     * 
     * @return the LPs loaned
     */
    public LoanContainer getLoans() {
        return loanContainer;
    }

    /**
     * returns the postal code of the Friend.
     * 
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * returns the city of the Friend.
     * 
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * returns the phone number of the Friend.
     * 
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the name of the Friend
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the address of the Friend
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the postal code of the Friend
     * 
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Sets the city of the Friend
     * 
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the phone number of the Friend
     * 
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Print the Friend information.
     */
    public void printInfo() {
        System.out.println("Navn: " + getName());
        System.out.println("Adresse: " + getAddress());
        System.out.println("Postnummer: " + getPostalCode());
        System.out.println("By: " + getCity());
        System.out.println("Tlf. nummer: " + getPhone());
    }
}
