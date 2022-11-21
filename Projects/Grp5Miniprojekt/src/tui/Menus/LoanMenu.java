package tui.Menus;

import java.util.ArrayList;
import java.util.Date;

import model.Copy;
import model.Friend;
import model.FriendContainer;
import model.LP;
import model.LPContainer;
import model.Loan;
import tui.TextInput;
import tui.TextOptions;

/**
 * Write a description of class LoanMenu here.
 *
 * @author Mogens Holm Iversen
 * @version 0.1.0 Initial draft version
 */
public class LoanMenu {
    // instance variables
    private FriendContainer friendContainer;
    private LPContainer lpContainer;
    private TextInput textInput;

    /**
     * Constructor for objects of class LoanMenu
     * 
     * @param loanContainer
     */
    public LoanMenu(FriendContainer friendContainer, LPContainer lpContainer) {
        // initialise instance variables
        this.friendContainer = friendContainer;
        this.lpContainer = lpContainer;
        textInput = new TextInput();
    }

    /**
     * It prints a menu, asks the user for a choice, and then calls the appropriate
     * function
     */
    public void start() {
        // Clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
        boolean running = true;
        while (running) {
            int choice = writeLoanMenu();
            if (choice == 1) {
                createLoan();
            } else if (choice == 2) {
                returnLoan();
            } else if (choice == 3) {
                showAllLoans();
            } else {
                writeEnd();
                running = false;
            }
        }
    }

    /**
     * It creates a loan
     */
    private void createLoan() {
        System.out.println("\n********** Opret lån **********");
        System.out.println("Indtast titel på LP'en");
        // Necessary to clear the buffer
        textInput.getStringInput();
        String title = textInput.getStringInput();
        LP lp = lpContainer.findLPByTitle(title);
        ArrayList<Copy> copies = lpContainer.getCopies(lp);
        if (copies != null) {
            System.out.println("Indtast telefonnummer på låner");
            String phone = textInput.getStringInput();
            Friend friend = friendContainer.findFriend(phone);

            if (friend != null) {
                Copy copy = copies.get(0);
                Loan loan = new Loan(copy, friend.getLoans().getLoanList().size(), new Date());
                friend.getLoans().addLoan(loan);
                copies.remove(copy);
                System.out.println("Lånet er oprettet. Lånet skal afleveres igen den " + loan.getReturnDate());
            } else {
                System.out.println("Der findes ingen ven med dette tlf. nummer " + phone);
            }
        } else {
            System.out.println("Der er ingen eksemplarer af LP'en til rådighed");
        }
    }

    /**
     * The function asks the user for a name and a phone number, and then finds the
     * friend with that name
     * and phone number. If the friend is found, the function asks for the title of
     * the LP, and then finds
     * the loan with that title. If the loan is found, the loan is set to returned
     */
    private void returnLoan() {
        System.out.println("\n********** Returner lån **********");
        System.out.println("Indtast telefonnummer på låner");
        String phone = textInput.getStringInput();
        Friend friend = friendContainer.findFriend(phone);
        if (friend != null) {
            System.out.println("Indtast serienummer på LP'en");
            int serialNumber = textInput.getIntInput();
            // Check if serial
            Loan loan = friend.getLoans().getLoanFromList(serialNumber);
            if (loan != null) {
                loan.setState(false);
                friend.getLoans().getLoanList().remove(loan);
                ArrayList<Copy> copies = lpContainer.getCopies(loan.getCopy().getOriginalLp());
                copies.add(loan.getCopy());
                System.out.println("LP'en er nu returneret");
            } else {
                System.out.println("Der findes ingen lån med serienummer " + serialNumber);
            }
        } else {
            System.out.println("Der findes ingen ven med dette navn og telefonnummer");
        }
    }

    /**
     * It prints out all the loans in the system
     */
    private void showAllLoans() {
        System.out.println("\n********** Udskriv alle lån **********");
        for (Friend friend : friendContainer.getFriends()) {
            if (friend.getLoans().getLoanList().size() != 0) {
                System.out.println("\n");
                friend.printInfo();
                System.out.println("Lånte LP'er:");
                for (Loan loan : friend.getLoans().getLoanList()) {
                    System.out.println("Lån serienummer: " + loan.getCopy().getSerialNumber());
                    System.out.println("Titel: " + loan.getCopy().getOriginalLp().getTitle() + " - "
                            + loan.getCopy().getOriginalLp().getArtist());
                    System.out.println("Udlånt den: " + loan.getLoanDate());
                    System.out.println("Afleveres den: " + loan.getReturnDate());
                }
            }
        }
    }

    /**
     * It creates a menu with the options "Back", "Create loan", "Return loan" and
     * "See all loans". It then
     * prompts the user to choose one of the options and returns the choice
     * 
     * @return The choice of the user.
     */
    private int writeLoanMenu() {
        // creates a keyboard object to read input
        TextOptions menu = new TextOptions("\n ***** Låner Menu *****", "Tilbage");
        menu.addOption("Opret lån");
        menu.addOption("Retur af lån");
        menu.addOption("Se alle lån");
        int choice = menu.prompt();

        return choice;
    }

    /**
     * The function `writeEnd()` writes a goodbye message to the user
     */
    private void writeEnd() {
        System.out.println(" Tak for denne gang ");
    }
}
