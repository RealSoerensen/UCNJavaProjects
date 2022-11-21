import model.LP;

import java.util.Date;

import model.Copy;
import model.FriendContainer;
import model.LPContainer;
import tui.Menus.MainMenu;

/**
 * The main class of the program.
 * 
 * @author Patrick Sørensen
 * @version 0.1.0 Initial draft version
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        FriendContainer friendContainer = new FriendContainer();
        LPContainer lpContainer = new LPContainer();

        MainMenu mainMenu = new MainMenu(friendContainer, lpContainer);
        mainMenu.start();
    }
}
