package model;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Container for LPs.
 * 
 * @author Patrick Sørensen
 * @version initial draft version
 */
public class LPContainer {
    // instance variables
    private HashMap<LP, ArrayList<Copy>> lps;
    private static LPContainer instance = new LPContainer();

    /**
     * Constructor for objects of class LPContainer
     */
    private LPContainer() {
        // initialise instance variables
        lps = new HashMap<>();
    }

    /**
     * If the instance is null, create a new instance of LPController and return it.
     * Otherwise, return the existing instance
     * 
     * @return The instance of the LPController class.
     */
    public static LPContainer getInstance() {
        if (instance == null) {
            instance = new LPContainer();
        }
        return instance;
    }

    /**
     * Get the LPs in the container.
     * 
     * @return the LPs
     */
    public ArrayList<LP> getLPs() {
        ArrayList<LP> result = new ArrayList<>();
        for (LP lp : lps.keySet()) {
            result.add(lp);
        }
        return result;
    }

    /**
     * Add an LP to the container.
     * 
     * @param lp the LP to be added
     */
    public void addLP(LP lp) {
        lps.put(lp, new ArrayList<Copy>());
    }

    /**
     * Get the number of LPs in the container.
     * 
     * @return the number of LPs
     */
    public int getNumberOfLPs() {
        return lps.size();
    }

    /**
     * Find an LP by title.
     * 
     * @param barCode the barcode of the LP to be found
     * @return the LP with the given barcode, or null if no such LP
     */
    public LP findLP(long barcode) {
        LP neededLP = null;
        for (LP lp : lps.keySet()) {
            if (lp.getBarcode() == barcode) {
                neededLP = lp;
            }
        }
        return neededLP;
    }

    /**
     * Find LP by titel.
     * 
     * @param title the title of the LP to be found
     */
    public LP findLPByTitle(String title) {
        LP neededLP = null;
        for (LP lp : lps.keySet()) {
            if (lp.getTitle().equals(title)) {
                neededLP = lp;
            }
        }
        return neededLP;
    }

    /**
     * remove an LP from the container by index.
     * 
     * @param i the index of the LP to be removed
     */
    public void removeLP(LP lp) {
        lps.remove(lp);
    }

    /**
     * Given an LP, return the copies of that LP.
     * 
     * @param lp The LP to get the copies of.
     * @return The copies of the LP.
     */
    public ArrayList<Copy> getCopies(LP lp) {
        return lps.get(lp);
    }

    /**
     * remove an LP from the container by barcode.
     * 
     * @param barCode the LP to be removed
     */
    public void removeLPByBarCode(long barCode) {
        LP lp = findLP(barCode);
        if (lp != null) {
            lps.remove(lp);
        }
    }

    /**
     * "Find the copy of the LP with the given serial number."
     * 
     * The function is a bit more complicated than that, but that's the gist of it
     * 
     * @param lp           LP object
     * @param serialNumber the serial number of the copy
     * @return A copy of the LP with the serial number that was passed in.
     */
    public Copy findLPCopy(LP lp, long serialNumber) {
        Copy neededCopy = null;
        ArrayList<Copy> copies = lps.get(lp);
        for (Copy copy : copies) {
            if (copy.getSerialNumber() == serialNumber) {
                neededCopy = copy;
            }
        }
        return neededCopy;
    }

    /**
     * Add a copy to an LP.
     * 
     * @param lp   The LP object that the copy is being added to.
     * @param copy the copy to be added
     */
    public void addCopy(Copy copy) {
        lps.get(copy.getOriginalLp()).add(copy);
    }

    /**
     * If the LP exists, and the serial number is not already in use, create a new
     * copy of the LP
     * 
     * @param barcode       long
     * @param serialNumber  int
     * @param purchaseDate  java.util.Date
     * @param purchasePrice double
     */
    public void createCopy(long barcode, int serialNumber, Date purchaseDate, double purchasePrice) {
        LP lp = findLP(barcode);
        if (lp != null) {
            // Check if serial number used by another copy in same list
            if (findLPCopy(lp, serialNumber) == null) {
                Copy copy = new Copy(lp, serialNumber, purchaseDate, purchasePrice);
                addCopy(copy);
            }
        }
    }

    /**
     * Get the copy with the given serial number from the library.
     * 
     * @param serialNumber the serial number of the copy to be returned
     * @return A copy of the LP with the given serial number.
     */
    public Copy getCopy(int serialNumber) {
        Copy copy = null;
        for (LP lp : lps.keySet()) {
            for (Copy c : lps.get(lp)) {
                if (c.getSerialNumber() == serialNumber) {
                    copy = c;
                }
            }
        }
        return copy;
    }

    /**
     * Removes a copy of an LP from the library
     * 
     * @param lp           LP object
     * @param serialNumber the serial number of the copy
     */
    public void removeLPCopy(LP lp, int serialNumber) {
        Copy copy = findLPCopy(lp, serialNumber);
        if (copy != null) {
            lps.get(lp).remove(copy);
        }
    }
}
