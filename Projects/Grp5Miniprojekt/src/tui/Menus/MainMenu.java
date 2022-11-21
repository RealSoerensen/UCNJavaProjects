package tui.Menus;

import tui.TextOptions;

/**
 * Description of MainMenu goes here.
 * 
 * @author knol, mhi
 * @version 2018.10.19
 * @version 2019.03.29 changed menu to danish and changed method names to
 *          reflect the other example
 */
public class MainMenu {
    // instance variables
    private LoanMenu loanMenu;
    private LPMenu lpMenu;
    private FriendMenu friendMenu;

    /**
     * Constructor for objects of MainMenu
     * 
     * @param friend
     */
    public MainMenu() {
        // initialise instance variables
        loanMenu = new LoanMenu();
        lpMenu = new LPMenu();
        friendMenu = new FriendMenu();
    }

    /**
     * > The function `start()` is called when the program starts. It displays the
     * main menu and calls the
     * appropriate function depending on the user's choice
     */
    public void start() {
        // Clear the screen
        System.out.print("\033[H\033[2J");
        System.out.flush();

        boolean exit = false;
        while (!exit) { // ! means while exit not is true (that is: false)
            int choice = writeMainMenu();
            if (choice == 1) {
                loanMenu.start();
            } else if (choice == 2) {
                lpMenu.start();
            } else if (choice == 3) {
                friendMenu.start();
            } else {
                writeEnd();
                exit = true;
            } // end else
        } // end while
    }

    /**
     * It creates a menu with the given options and returns the user's choice
     * 
     * @return The index of the option chosen by the user.
     */
    private int writeMainMenu() {
        // Write the menu
        TextOptions menu = new TextOptions("\n *** Hovedmenu ***", "Afslut programmet");
        menu.addOption("Lånermenu");
        menu.addOption("LP menu");
        menu.addOption("Vennemenu");
        return menu.prompt();
    }

    /**
     * The function `writeEnd()` writes a goodbye message to the user
     */
    private void writeEnd() {
        System.out.println(" Tak for denne gang ");
    }
}
