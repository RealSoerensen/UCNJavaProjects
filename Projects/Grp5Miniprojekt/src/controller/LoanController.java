package controller;

import model.LoanContainer;

/**
 * LoanController class used to control the Loans in the LoanContainer.
 * 
 * @author Patrick Sørensen
 * @version initial draft version
 */
public class LoanController {
    /**
     * Constructor for objects of class LoanContainer
     */
    public LoanController() {
        LoanContainer.getInstance();
    }
}
