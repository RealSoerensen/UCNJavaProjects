package controller;

import java.util.ArrayList;

import model.Friend;
import model.FriendContainer;

/**
 * FriendController class used to control the Friends in the FriendContainer.
 * 
 * @author Patrick Sørensen
 * @version 0.1.0 Initial draft version
 */
public class FriendController {
    private FriendContainer friendContainer;

    // Calling the constructor of the superclass.
    public FriendController() {
        friendContainer = FriendContainer.getInstance();
    }

    /**
     * Get friend by name and phone.
     * 
     * @param name
     * @return Friend
     */
    public Friend findFriend(String phoneNum) {
        boolean found = false;
        Friend neededfriend = null;
        ArrayList<Friend> friends = friendContainer.getFriends();
        for (int i = 0; i < friends.size() && !found; i++) {
            Friend currentfriend = friends.get(i);
            if (currentfriend.getPhone().equals(phoneNum)) {
                found = true;
                neededfriend = currentfriend;
            }
        }
        return neededfriend;
    }

    public void addFriend(Friend friend) {
        friendContainer.addFriend(friend);
    }

    public void removeFriend(Friend friend) {
        friendContainer.removeFriend(friend);
    }

    public ArrayList<Friend> getFriends() {
        return friendContainer.getFriends();
    }

    public void createFriend(String string, String string2, String string3, String string4, String string5) {
        friendContainer.createFriend(string, string2, string3, string4, string5);
    }
}
