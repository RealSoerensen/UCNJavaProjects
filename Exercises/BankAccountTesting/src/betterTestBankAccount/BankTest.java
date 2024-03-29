package betterTestBankAccount;

import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
	Bank bank;

	@BeforeEach
	public void setUp() throws Exception {
		bank = new Bank();
		bank.addAccount(new Account(1,100));
		bank.addAccount(new Account(2,80));
		bank.addAccount(new Account(3,25));
		bank.addAccount(new Account(4,50));
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testTransfer() {
		Account a = bank.findById(1); 
		Account b = bank.findById(2); 
		double balanceA = a.getBalance(); 
		double balanceB = b.getBalance(); 
		bank.transfer(50, a, b);  // call withdraw method from Account a and deposit from Account b
		assertEquals(balanceA-50, a.getBalance(),0); 
		assertEquals(balanceB+50, b.getBalance(),0); 

	}
	
	@Test
	public void testTransferFail() {
		Account c = bank.findById(3);
		Account d = bank.findById(4); 
		boolean res = bank.transfer(50, c, d);  // call withdraw method from Account a and deposit from Account b
		double balanceC = c.getBalance(); 
		double balanceD = d.getBalance(); 
		assertFalse(res);
		assertEquals(balanceC, c.getBalance(),0); 
		assertEquals(balanceD, d.getBalance(),0); 
	}

}
