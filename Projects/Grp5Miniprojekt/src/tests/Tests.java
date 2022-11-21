package tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import controller.FriendController;
import controller.LPController;
import controller.LoanController;
import tui.Menus.FriendMenu;
import model.Loan;
import model.LP;
import model.Copy;
import model.Friend;

import java.util.Date;

public class Tests {
    FriendController friendController = new FriendController();
    LPController lpController = new LPController();
    FriendMenu friendMenu = new FriendMenu();

    @Test
    void testReturnLoanFromRemovedFriend() {
        friendController.createFriend("Mogens", "Hovedgaden 1", "1234", "Byen", "12345678");

        // Create a new LP
        LP lp = new LP(0, "Sang 1", "Hans", "1990");
        lpController.addLP(lp);
        // Create copy of lp
        Copy copy = new Copy(lp, 123123, new Date(), 39.99);
        lpController.addCopy(copy);
        // Get friend
        Friend friend = friendController.findFriend("12345678");
        // get loancontainer from friend
        LoanController loanController = friend.getLoans();
        // Create new loan and add to loancontainer
        Loan newLoan = new Loan(copy, 0, new Date());
        loanController.addLoan(newLoan);

        friendMenu.removeFriends();
        Assert.assertEquals(0, loanController.getNumberOfLoans());
    }
}
