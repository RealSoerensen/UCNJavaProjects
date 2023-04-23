package test;


import controllayer.*;
import modellayer.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestIllegalCoin {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that illegal coins are rejected.
	 */
	
	// Norwegian coin
	@Test
	public void shouldRejectIllegalCurrencyNokCoin() {
		//Arrange
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.NOK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		//Assert
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(50, coinCurrency, coinType));
	}
	
	// unknown Euro coin value
	@Test
	public void shouldRejectIllegalEuroCoin() {
		// Arrange
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		//Assert
		assertThrows(IllegalCoinException.class, () -> ps.addPayment(50, coinCurrency, coinType));
	}
}
