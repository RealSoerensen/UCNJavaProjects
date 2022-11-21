package tui.Menus;

import java.util.ArrayList;

import model.Copy;
import model.Friend;
import model.FriendContainer;
import model.LPContainer;
import model.Loan;
import tui.TextInput;
import tui.TextOptions;

/**
 * FriendMenu is a menu for the Friend class.
 * 
 * @author Patrick Sørensen
 * @version 0.1.0 Initial draft version
 */
public class FriendMenu {
    // instance variables
    TextInput textInput;
    FriendContainer friendContainer;
    LPContainer lpContainer;

    /**
     * Constructor for objects of class FriendMenu
     * 
     * @param friendContainer
     * @param lpContainer
     */
    public FriendMenu(FriendContainer friendContainer, LPContainer lpContainer) {
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
        textInput.getStringInput();
        while (running) {
            int choice = writeLoanMenu();
            if (choice == 1) {
                createFriend();
            } else if (choice == 2) {
                editFriends();
            } else if (choice == 3) {
                removeFriends();
            } else if (choice == 4) {
                showAllFriends();
            } else {
                running = false;
            }
        }
    }

    /**
     * It creates a friend
     */
    private void createFriend() {
        System.out.println("\n********** Opret en ven **********");
        // Necessary to clear the buffer
        textInput.getStringInput();
        // Ask for the name of the friend
        System.out.println("Indtast navn: ");
        String name = textInput.getStringInput();
        // Ask for the address of the friend
        System.out.println("Indtast adresse: ");
        String address = textInput.getStringInput();
        // Ask for the postal code of the friend
        System.out.println("Indtast postnummer: ");
        String postalCode = textInput.getStringInput();
        // Ask for the city of the friend
        System.out.println("Indtast by: ");
        String city = textInput.getStringInput();
        // Ask for the phone number of the friend
        System.out.println("Indtast telefonnummer: ");
        String phone = textInput.getStringInput();
        if (friendContainer.findFriend(phone) == null) {
            Friend friend = new Friend(name, address, postalCode, city, phone);
            friendContainer.addFriend(friend);
            // Print a message
            System.out.println("Ven oprettet");
        } else {
            System.out.println("En ven med dette telefonnummer findes allerede");
        }
    }

    /**
     * It asks for a phone number, finds the friend with that phone number, and then
     * asks for new
     * information about the friend
     */
    private void editFriends() {
        System.out.println("\n********** Rediger venner **********");
        // Ask for the phone number of the friend
        System.out.println("Indtast telefonnummer: ");
        // Necessary to clear the buffer
        textInput.getStringInput();
        String phone = textInput.getStringInput();
        // Find the friend
        Friend friend = friendContainer.findFriend(phone);
        // If the friend is found
        if (friend != null) {
            // Ask for the new name of the friend
            System.out.println("Indtast nyt navn: ");
            String newName = textInput.getStringInput();
            // Ask for the new address of the friend
            System.out.println("Indtast ny adresse: ");
            String newAddress = textInput.getStringInput();
            // Ask for the new postal code of the friend
            System.out.println("Indtast nyt postnummer: ");
            String newPostalCode = textInput.getStringInput();
            // Ask for the new city of the friend
            System.out.println("Indtast ny by: ");
            String newCity = textInput.getStringInput();
            // Ask for the new phone number of the friend
            System.out.println("Indtast nyt telefonnummer: ");
            String newPhone = textInput.getStringInput();
            // Edit the friend
            friend.setName(newName);
            friend.setAddress(newAddress);
            friend.setPostalCode(newPostalCode);
            friend.setCity(newCity);
            friend.setPhone(newPhone);
            // Print a message
            System.out.println("Ven redigeret");
        } else {
            // Print a message
            System.out.println("Ven ikke fundet");
        }
    }

    /**
     * It removes a friend from the friendContainer, but before it does that, it
     * removes all loans that the friend has
     */
    public void removeFriends() {
        System.out.println("\n********** Fjern venner **********");
        // Ask for the phone number of the friend
        System.out.println("Indtast telefonnummer: ");
        // Necessary to clear the buffer
        textInput.getStringInput();
        String phone = textInput.getStringInput();
        // Find the friend
        Friend friend = friendContainer.findFriend(phone);
        // If the friend is found
        if (friend != null) {
            ArrayList<Loan> loans = friend.getLoans().getLoanList();
            // Return loans of the friend before deleting the friend
            for (int i = loans.size() - 1; i >= 0; i--) {
                Loan loan = loans.get(i);
                friend.getLoans().removeLoan(loan);
                ArrayList<Copy> copies = lpContainer.getCopies(loan.getCopy().getOriginalLp());
                copies.add(loan.getCopy());
            }
            // Remove the friend
            friendContainer.removeFriend(friend);
            System.out.println("Ven fjernet");
        } else {
            // Print a message
            System.out.println("Ven ikke fundet");
        }
    }

    /**
     * It prints all the friends in the friendContainer
     */
    private void showAllFriends() {
        System.out.println("\n********** Vis alle venner **********");
        // Print all friends
        for (Friend friend : friendContainer.getFriends()) {
            System.out.println("\n");
            System.out.println("Navn: " + friend.getName());
            System.out.println("Adresse: " + friend.getAddress());
            System.out.println("Postnummer: " + friend.getPostalCode());
            System.out.println("By: " + friend.getCity());
            System.out.println("Telefonnummer: " + friend.getPhone());
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
        TextOptions menu = new TextOptions("\n ***** Ven menu *****", "Tilbage");
        menu.addOption("Opret ven");
        menu.addOption("Rediger ven");
        menu.addOption("Slet ven");
        menu.addOption("Se alle venner");
        int choice = menu.prompt();

        return choice;
    }
}
