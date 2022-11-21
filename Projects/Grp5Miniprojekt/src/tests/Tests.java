package tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tui.Menus.FriendMenu;
import model.FriendContainer;
import model.LPContainer;
import model.Loan;
import model.LoanContainer;
import model.LP;
import model.Copy;
import model.Friend;

import java.util.Date;

public class Tests {
	FriendContainer friendContainer = new FriendContainer();
	LPContainer lpContainer = new LPContainer();
	FriendMenu friendMenu = new FriendMenu(friendContainer, lpContainer);
	
	@Test
	void testReturnLoanFromRemovedFriend() {
        friendContainer.createFriend("Mogens", "Hovedgaden 1", "1234", "Byen", "12345678");

        // Create a new LP
        LP lp = new LP(0, "Sang 1", "Hans", "1990");
        lpContainer.addLP(lp);
        // Create copy of lp
        Copy copy = new Copy(lp, 123123, new Date(), 39.99);
        lpContainer.addCopy(copy);
        //Get friend
        Friend friend = friendContainer.getFriend(0);
        // get loancontainer from friend
        LoanContainer loanContainer = friend.getLoans();
        // Create new loan and add to loancontainer
        Loan newLoan = new Loan(copy, 0, new Date());
        loanContainer.addLoan(newLoan);
        
		friendMenu.removeFriends();
		Assert.assertEquals(0, loanContainer.getNumberOfLoans());
	}
}
