package controller;

import model.FriendContainer;

/**
 * FriendController class used to control the Friends in the FriendContainer.
 * 
 * @author Patrick Sørensen
 * @version 0.1.0 Initial draft version
 */
public class FriendController {
    // Singleton instance
    public static FriendController instance = new FriendController();

    // Calling the constructor of the superclass.
    public FriendController() {
        FriendContainer.getInstance();
    }
}
