package model;

import java.util.ArrayList;

/**
 * Container for Friends.
 * 
 * @author Patrick Sørensen
 * @version initial draft version
 */
public class FriendContainer {
    // instance variables
    private ArrayList<Friend> friends;
    private static FriendContainer instance = new FriendContainer();

    /**
     * Constructor for objects of class FriendContainer
     */
    public FriendContainer() {
        // initialise instance variables
        friends = new ArrayList<>();
    }

    /**
     * If the instance is null, create a new instance of FriendController and return
     * it. Otherwise, return
     * the existing instance
     * 
     * @return The instance of the FriendController class.
     */
    public static FriendContainer getInstance() {
        if (instance == null) {
            instance = new FriendContainer();
        }
        return instance;
    }

    /**
     * Create a new friend and add it to the container.
     * 
     * @param name
     * @param address
     * @param postalCode
     * @param city
     * @param phone
     */
    public void createFriend(String name, String address, String postalCode, String city,
            String phone) {
        Friend friend = new Friend(name, address, postalCode, city,
                phone);
        addFriend(friend);
    }

    /**
     * Adds a friend to the container.
     * 
     * @param friend the friend to be added
     */
    public void addFriend(Friend friend) {
        friends.add(friend);
    }

    /**
     * Removes a friend from the container.
     * 
     * @param index of the friend to get.
     * @return the friend at the given index.
     */
    public Friend getFriend(int index) {
        return friends.get(index);
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
        for (int i = 0; i < friends.size() && !found; i++) {
            Friend currentfriend = friends.get(i);
            if (currentfriend.getPhone().equals(phoneNum)) {
                found = true;
                neededfriend = currentfriend;
            }
        }
        return neededfriend;
    }

    /**
     * Removes a friend from the container.
     * 
     * @param index the index of the friend to be removed
     */
    public void removeFriend(Friend friend) {
        friends.remove(friend);
    }

    /**
     * This function returns an ArrayList of Friend objects
     * 
     * @return The ArrayList of friends.
     */
    public ArrayList<Friend> getFriends() {
        return friends;
    }
}
