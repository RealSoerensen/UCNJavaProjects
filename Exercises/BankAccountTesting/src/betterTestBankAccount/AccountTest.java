package betterTestBankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
	private Account a;
	private Account a16000;

	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("setUp()");
		a = new Account(1,3000d);
		a16000 = new Account(2,16000d);
	}

	@AfterEach
	public void tearDown() throws Exception {
		System.out.println("tearDown()");
	}

	@Test
	public void testRedraw() {
		System.out.println("testRedraw()");
		boolean result = a.withdraw(3000);
		assertTrue(result);
		double expBal = 0d;
		assertEquals(expBal, a.getBalance(), 0d);
	}

}
