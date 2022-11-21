package model;

import java.util.Calendar;
import java.util.Date;

/**
 * Loan class.
 * 
 * @author Patrick Sørensen
 * @version initial draft version
 */
public class Loan {
    // instance variables
    private Copy copy;
    private int loanNumber;
    private Date loanDate;
    private Date returnDate;
    private Boolean state;

    /**
     * Constructor for objects of class Loan
     * 
     * @param copy
     * @param loanNumber
     * @param loanDate
     * @param period
     * @param state
     */
    public Loan(Copy copy, int loanNumber, Date loanDate) {
        // initialise instance variables
        this.copy = copy;
        this.loanNumber = loanNumber;
        this.loanDate = loanDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(loanDate);
        cal.add(Calendar.DATE, 7);
        returnDate = cal.getTime();
        this.state = true;
    }

    /**
     * returns the LP loaned.
     * 
     * @return the LP loaned
     */
    public Copy getCopy() {
        return copy;
    }

    /**
     * returns the loan number.
     * 
     * @return the loan number
     */
    public int getLoanNumber() {
        return loanNumber;
    }

    /**
     * returns the loan date.
     * 
     * @return the loan date
     */
    public Date getLoanDate() {
        return loanDate;
    }

    /**
     * returns the loan period.
     * 
     * @return the loan period
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * returns the loan state.
     * 
     * @return the loan state
     */
    public Boolean getState() {
        return state;
    }

    /**
     * sets the loan state.
     * 
     * @param state
     */
    public void setLp(Copy copy) {
        this.copy = copy;
    }

    /**
     * sets the loan number.
     * 
     * @param loanNumber
     */
    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }

    /**
     * sets the loan date.
     * 
     * @param loanDate
     */
    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * sets the loan period.
     * 
     * @param period
     */
    public void setPeriod(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * sets the loan state.
     * 
     * @param state
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * print information about the loan.
     */
    public void printInfo() {
        System.out.println("Lånenummer: " + getLoanNumber());
        System.out.println("Dag lånt: " + getLoanDate());
        System.out.println("Afleverings dag: " + getReturnDate());
        if (getState()) {
            System.out.println("Ikke afleveret");
        } else {
            System.out.println("Afleveret");
        }
    }
}
