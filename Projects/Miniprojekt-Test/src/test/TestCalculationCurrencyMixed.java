package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import databaselayer.DatabaseLayerException;
import org.junit.jupiter.api.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyMixed {

	static ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeAll
	public static void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Entering 1 cent and 50 �re should make the display report 4 minutes parking time.
	 */
	@Test
	public void shouldDisplay4MinFor1CentAnd50Ore() throws IllegalCoinException, DatabaseLayerException {
		// Arrange
		int expectedParkingTime = 4;
		Currency.ValidCurrency eurCoinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		Currency.ValidCurrency dkkCoinCurrency = Currency.ValidCurrency.DKK;
		// Act
		ps.addPayment(50, dkkCoinCurrency, coinType);
		ps.addPayment(1, eurCoinCurrency, coinType);
		
		// Assert
		assertEquals(expectedParkingTime, ps.readDisplay(), "Should display 4 min for 50 øre and 1 cent");	
	}

	
	/** Fixture for pay station testing. */
	@AfterAll
	public static void cleanUp() {
		ps.setReady();
	}
	
}
