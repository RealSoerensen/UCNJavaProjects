package test;

import controllayer.ControlPayStation;
import controllayer.IllegalCoinException;
import databaselayer.DatabaseLayerException;
import modellayer.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestReset {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that the pay station is cleared and display shows 0 after a buy scenario
	 */
	@Test
	public void shouldClearAfterBuy() throws Exception {
		// Arrange
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;

		// Act
		ps.addPayment(2, coinCurrency, coinType);
		ps.buy();
		
		// Assert
		assertEquals(0, ps.readDisplay());
	}

	/**
	 * Verify that cancel() clears the pay station
	 */
	@Test
	public void shouldClearAfterCancel() throws IllegalCoinException, DatabaseLayerException {
		// Arrange
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		Currency.ValidCurrency coinCurrency =  Currency.ValidCurrency.DKK;
		
		// Act
		ps.addPayment(5, coinCurrency, coinType);
		ps.cancel();
		
		// Assert
		assertEquals(0, ps.readDisplay());
	}
}
