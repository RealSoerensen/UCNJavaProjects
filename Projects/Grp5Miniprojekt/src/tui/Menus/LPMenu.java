package tui.Menus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.LPController;
import model.Copy;
import model.LP;
import tui.TextInput;
import tui.TextOptions;

/**
 * Menu for the loaned LPs.
 * 
 * @author Patrick Sørensen
 * @version initial draft version
 */
public class LPMenu {
    // instance variables
    private LPController lpController;
    private TextInput textInput;

    /**
     * Constructor for objects of class LPMenu
     * 
     * @param LPContainer
     */
    public LPMenu() {
        lpController = new LPController();
        textInput = new TextInput();
    }

    public void start() {
        // Clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
        boolean exit = false;
        while (!exit) { // ! means while exit not is true (that is: false)
            int choice = writeMainMenu();
            if (choice == 1) {
                addLP();
            } else if (choice == 2) {
                editLP();
            } else if (choice == 3) {
                removeLP();
            } else if (choice == 4) {
                createCopy();
            } else if (choice == 5) {
                editCopy();
            } else if (choice == 6) {
                removeCopy();
            } else if (choice == 7) {
                showAllLPs();
            } else {
                exit = true;
            } // end else
        } // end while
    }

    /**
     * It writes a menu to the console and returns the user's choice
     * 
     * @return The return value is the number of the option that the user has
     *         chosen.
     */
    private int writeMainMenu() {
        // Write the menu
        TextOptions menu = new TextOptions("\n *** LP Menu ***", "Tilbage");
        menu.addOption("Tilføj LP");
        menu.addOption("Redigere LP");
        menu.addOption("Slet LP");
        menu.addOption("Opret kopi af LP");
        menu.addOption("Redigere kopi af LP");
        menu.addOption("Slet kopi af LP");
        menu.addOption("Vis alle LP'er");
        return menu.prompt();
    }

    /**
     * Adds a new LP to the LPContainer
     */
    private void addLP() {
        System.out.println("\n********** Tilføj LP **********");
        System.out.println("Indtast barcode: ");
        long barcode = textInput.getLongInput();
        textInput.getStringInput();
        System.out.println("Indtast titel: ");
        String title = textInput.getStringInput();
        System.out.println("Indtast kunster: ");
        String artist = textInput.getStringInput();
        System.out.println("Indtast årstal: ");
        String year = textInput.getStringInput();
        // Check if the LP already exists
        LP oldLp = lpController.findLP(barcode);
        if (oldLp != null) {
            System.out.println("LP'en findes allerede");
        } else {
            // Create a new LP
            LP lp = new LP(barcode, title, artist, year);
            // Add the LP to the LPContainer
            lpController.addLP(lp);
            System.out.println("LP'en er tilføjet");
        }
    }

    /**
     * Edits an existing LP in the LPContainer
     */
    private void editLP() {
        System.out.println("\n********** Rediger LP **********");
        System.out.println("Indtast barcode: ");
        long barcode = textInput.getLongInput();
        LP lp = lpController.findLP(barcode);
        if (lp != null) {
            System.out.println("\n");
            System.out.println("Nuværende information på LP'en: ");
            lp.printInfo();
            textInput.getStringInput();
            System.out.println("\nIndtast ny titel: ");
            String title = textInput.getStringInput();
            System.out.println("Indtast ny kunster: ");
            String artist = textInput.getStringInput();
            System.out.println("Indtast nyt årstal: ");
            String year = textInput.getStringInput();
            lp.setTitle(title);
            lp.setArtist(artist);
            lp.setYear(year);
            System.out.println("LP'en er redigeret");
        } else {
            System.out.println("LP'en findes ikke");
        }
    }

    /**
     * Removes an existing LP from the LPContainer
     */
    private void removeLP() {
        System.out.println("\n********** Fjern LP **********");
        System.out.println("Indtast barcode: ");
        long barcode = textInput.getLongInput();
        if (lpController.findLP(barcode) != null) {
            lpController.removeLPByBarCode(barcode);
            System.out.println("LP'en er slettet");
        } else {
            System.out.println("LP'en findes ikke");
        }

    }

    /**
     * Create a new copy of an LP, by asking the user for the barcode of the LP to
     * be copied, and then
     * calling the createCopy method of the LPContainer class.
     */
    private void createCopy() {
        System.out.println("\n********** Opret kopi af LP **********");
        System.out.println("Indtast barcode på LP'en der skal laves en kopi på: ");
        long barcode = textInput.getLongInput();
        LP lp = lpController.findLP(barcode);
        if (lp != null) {
            System.out.println("Indtast serienummer: ");
            int serialNumber = textInput.getIntInput();

            // Clear the input buffer
            textInput.getStringInput();

            Date date = inputPurchaseDate();
            if (date == null)
                return;

            double newPurchasePrice = inputPurchasePrice();
            if (newPurchasePrice == -1)
                return;

            // Check if serial number is already in use
            if (lpController.findLPCopy(lp, serialNumber) == null) {
                Copy copy = new Copy(lp, serialNumber, date, newPurchasePrice);
                lpController.addCopy(copy);
                System.out.println("Kopi tilføjet");
            } else {
                System.out.println("Serienummer allerede i brug");
            }
        } else {
            System.out.println("LP'en findes ikke");
        }
    }

    /**
     * Edit a copy of an LP, by asking the user for the barcode of the LP to be
     * edited, and then
     * calling the editCopy method of the LPContainer class.
     */
    private void editCopy() {
        System.out.println("\n********** Rediger kopi **********");
        System.out.println("Indtast barcode på LP'en der har en kopi der skal redigeres: ");
        long barcode = textInput.getLongInput();
        LP lp = lpController.findLP(barcode);
        if (lp == null) {
            System.out.println("LP'en findes ikke");
            return;
        }
        System.out.println("Indtast serienummer på kopi der skal redigeres: ");
        int serialNumber = textInput.getIntInput();
        Copy copy = lpController.findLPCopy(lp, serialNumber);
        if (copy == null) {
            System.out.println("Kopien findes ikke");
            return;
        }
        System.out.println("Nuværende information på kopien: ");
        copy.printInfo();
        System.out.println("\nIndtast ny serienummer: ");
        int newSerialNumber = textInput.getIntInput();
        // Clear input buffer
        textInput.getStringInput();

        Date date = inputPurchaseDate();
        if (date == null)
            return;

        double newPurchasePrice = inputPurchasePrice();
        if (newPurchasePrice == -1)
            return;

        copy.setSerialNumber(newSerialNumber);
        copy.setPurchaseDate(date);
        copy.setPurchasePrice(newPurchasePrice);
        System.out.println("Kopien er redigeret");
    }

    /**
     * It removes a copy of an LP from the LP container
     */
    private void removeCopy() {
        System.out.println("\n********** Slet kopi **********");
        System.out.println("Indtast barcode på LP'en der har en kopi der skal slettes: ");
        long barcode = textInput.getLongInput();
        LP lp = lpController.findLP(barcode);
        if (lp != null) {
            System.out.println("Indtast serienummer på kopi der skal slettes: ");
            int serialNumber = textInput.getIntInput();
            Copy copy = lpController.findLPCopy(lp, serialNumber);
            if (copy != null) {
                lpController.removeLPCopy(lp, serialNumber);
                System.out.println("Kopien er slettet");
            } else {
                System.out.println("Kopien findes ikke");
            }
        } else {
            System.out.println("LP'en findes ikke");
        }
    }

    /**
     * Shows all LPs in the LPContainer
     */
    private void showAllLPs() {
        System.out.println("\n********** Udskriv alle LP's **********");
        for (LP lp : lpController.getLPs()) {
            System.out.println("\n");
            lp.printInfo();
            if (lpController.getCopies(lp).size() > 0) {
                System.out.println("Kopier: ");
                for (Copy copy : lpController.getCopies(lp)) {
                    copy.printInfo();
                }
            } else {
                System.out.println("Der er ingen kopier af LP'en");
            }
        }
    }

    private double inputPurchasePrice() {
        System.out.println("Indtast ny købspris: ");
        String purchasePrice = "";
        double newPurchasePrice = 0;
        purchasePrice = textInput.getStringInput();
        try {
            newPurchasePrice = Double.parseDouble(purchasePrice);
        } catch (Exception e) {
            System.out.println("Ugyldig pris");
            return -1;
        }
        return newPurchasePrice;
    }

    private Date inputPurchaseDate() {
        System.out.println("Indtast købsdato (DD/MM/YYYY): ");
        String purchaseDate = textInput.getStringInput();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(purchaseDate);
        } catch (ParseException e) {
            System.out.println("Ugyldig dato");
        }
        return date;
    }
}
