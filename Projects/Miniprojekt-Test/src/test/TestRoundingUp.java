package test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import databaselayer.DatabaseLayerException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import controllayer.*;
import modellayer.Currency;
import org.junit.jupiter.api.Test;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestRoundingUp {

	static ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	//Verify that 24 seconds are rounded up to a minute.
	@Test
	public void shouldRoundUpToAMinute() throws IllegalCoinException, DatabaseLayerException {
		//Arrange
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		//Act
		ps.addPayment(1, coinCurrency, coinType);
		
		//Assert
		assertEquals(1, ps.getPPayStation().getTimeBoughtInMinutes());
	}
	
	//Verify that 160 seconds are rounded up to 3 minutes.
	@Test
	public void oneOreShouldRoundUpToThreeMinutes() throws IllegalCoinException, DatabaseLayerException {
		//Arrange
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		//Act
		ps.addPayment(50, coinCurrency, coinType);
		
		//Assert
		assertEquals(3, ps.getPPayStation().getTimeBoughtInMinutes());
	}
	
	@AfterEach
	public void cleanUp() {
		ps.setReady();
	}
}
