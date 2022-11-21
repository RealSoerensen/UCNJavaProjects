package controller;

import java.util.ArrayList;

import model.Copy;
import model.LP;
import model.LPContainer;

/**
 * LPController class used to control the LPs in the LPContainer.
 * 
 * @author Patrick Sørensen
 * @version 0.1.0 Initial draft version
 */
public class LPController {
    private LPContainer lpContainer;

    /**
     * Constructor for objects of class LPController
     */
    public LPController() {
        lpContainer = LPContainer.getInstance();
    }

    public ArrayList<Copy> getCopies(LP lp) {
        return lpContainer.getCopies(lp);
    }

    public LP findLPByTitle(String title) {
        return lpContainer.findLPByTitle(title);
    }

    public void addLP(LP lp) {
        lpContainer.addLP(lp);
    }

    public LP findLP(long barcode) {
        return lpContainer.findLP(barcode);
    }

    public void removeLPByBarCode(long barcode) {
        lpContainer.removeLPByBarCode(barcode);
    }

    public Copy findLPCopy(LP lp, int serialNumber) {
        return lpContainer.findLPCopy(lp, serialNumber);
    }

    public void addCopy(Copy copy) {
        lpContainer.addCopy(copy);
    }

    public void removeLPCopy(LP lp, int serialNumber) {
        lpContainer.removeLPCopy(lp, serialNumber);
    }

    public ArrayList<LP> getLPs() {
        return lpContainer.getLPs();
    }
}
