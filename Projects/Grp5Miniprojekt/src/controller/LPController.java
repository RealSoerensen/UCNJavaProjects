package controller;

import model.LPContainer;

/**
 * LPController class used to control the LPs in the LPContainer.
 * 
 * @author Patrick Sørensen
 * @version 0.1.0 Initial draft version
 */
public class LPController {
    // It's a constructor.
    public LPController() {
        LPContainer.getInstance();
    }
}
