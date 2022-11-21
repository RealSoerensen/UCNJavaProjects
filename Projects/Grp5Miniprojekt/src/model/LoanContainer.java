package model;

import java.util.ArrayList;

/**
 * LoanContainer class.
 * 
 * @author Patrick Sørensen
 * @version initial draft version
 */
public class LoanContainer {
    // instance variables
    private ArrayList<Loan> loans;
    private static LoanContainer instance = new LoanContainer();

    /**
     * Constructor for objects of class LoanContainer
     */
    public LoanContainer() {
        // initialise instance variables
        loans = new ArrayList<>();
    }

    /**
     * If the instance is null, create a new instance of LoanController and return
     * it. Otherwise, return
     * the existing instance
     * 
     * @return The instance of the LoanController class.
     */
    public static LoanContainer getInstance() {
        if (instance == null) {
            instance = new LoanContainer();
        }
        return instance;
    }

    /**
     * adds a loan to the loan container.
     * 
     * @param loan the loan to be added
     */
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    /**
     * removes a loan from the loan container.
     * 
     * @param loan the loan to be removed
     */
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    /**
     * returns the loan container.
     * 
     * @return the loan container
     */
    public ArrayList<Loan> getLoanList() {
        return loans;
    }

    public Loan getLoanFromList(int serialNumber) {
        Loan loan = null;
        for (int i = 0; i < getLoanList().size(); i++) {
        	Copy copy = loans.get(i).getCopy();
            if (copy.getSerialNumber() == serialNumber) {
                loan = loans.get(i);
            }
        }
        return loan;
    }

    /**
     * get the loan with the given index.
     * 
     * @param index
     * @return
     */
    public Loan getLoan(int index) {
        return loans.get(index);
    }

    /**
     * returns the size of the loan container.
     * 
     * @return the size of the loan container
     */
    public int getNumberOfLoans() {
        return loans.size();
    }

    /**
     * clears the loan container.
     */
    public void clear() {
        loans.clear();
    }
}
