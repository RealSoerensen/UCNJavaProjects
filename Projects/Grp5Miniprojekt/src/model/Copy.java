package model;

import java.util.Date;

/**
 * Copy
 */
public class Copy {
    // instance variables
    private LP originalLp;
    private int serialNumber;
    private Date purchaseDate;
    private double purchasePrice;

    /**
     * Constructor for objects of class Copy
     *
     * @param serialNumber
     * @param purchaseDate
     * @param purchasePrice
     */
    public Copy(LP originalLp, int serialNumber, Date purchaseDate, double purchasePrice) {
        // initialise instance variables
        this.originalLp = originalLp;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
    }

    /**
     * returns the original LP.
     *
     * @return the original LP
     */
    public LP getOriginalLp() {
        return originalLp;
    }

    /**
     * Returns the serial number of the copy.
     *
     * @return the serial number
     */
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * Returns the purchase date of the copy.
     *
     * @return the purchase date
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Returns the purchase price of the copy.
     *
     * @return the purchase price
     */
    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Set the original LP.
     * 
     * @param originalLp
     */
    public void setOriginalLp(LP originalLp) {
        this.originalLp = originalLp;
    }

    /**
     * Sets the serial number of the copy.
     * 
     * @param serialNumber
     */
    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Sets the purchase date of the copy.
     * 
     * @param purchaseDate
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * Sets the purchase price of the copy.
     * 
     * @param purchasePrice
     */
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void printInfo() {
        System.out.println("Serienummer: " + getSerialNumber());
        System.out.println("Købsdato: " + getPurchaseDate());
        System.out.println("Købspris: " + getPurchasePrice());
    }
}