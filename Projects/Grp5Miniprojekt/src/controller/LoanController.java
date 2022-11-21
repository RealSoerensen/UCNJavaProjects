package controller;

import java.util.ArrayList;

import model.Loan;
import model.LoanContainer;

/**
 * LoanController class used to control the Loans in the LoanContainer.
 * 
 * @author Patrick Sørensen
 * @version initial draft version
 */
public class LoanController {
    private LoanContainer loanContainer;

    /**
     * Constructor for objects of class LoanContainer
     */
    public LoanController() {
        loanContainer = LoanContainer.getInstance();
    }

    public ArrayList<Loan> getLoanList() {
        return loanContainer.getLoanList();
    }

    public void removeLoan(Loan loan) {
        loanContainer.removeLoan(loan);
    }

    public void addLoan(Loan loan) {
        loanContainer.addLoan(loan);
    }

    public Loan getLoanFromList(int serialNumber) {
        return loanContainer.getLoanFromList(serialNumber);
    }

    public int getNumberOfLoans() {
        return loanContainer.getNumberOfLoans();
    }
}
