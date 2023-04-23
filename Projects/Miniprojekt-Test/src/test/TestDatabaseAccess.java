package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

import java.time.LocalDate;

//import controllayer.ControlPayStation;
//import controllayer.Currency;
//import controllayer.IPayStation;
//import controllayer.IReceipt;
//import controllayer.IllegalCoinException;

import databaselayer.*;
import modellayer.*;
import controllayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDatabaseAccess {
	private static PBuy tempPBuy;
	private DBConnection con;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		con = DBConnection.getInstance();
	}

	@Test
	public void wasConnected() {
		assertNotNull(con, "Connected - connection cannot be null");
		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue(wasNullified, "Disconnected - instance set to null");
		
		con = DBConnection.getInstance();
		boolean connectionIsOpen = DBConnection.getOpenStatus();
		assertTrue(connectionIsOpen);	
	}
	
	
	@Test
	public void wasInsertedBuy() {
		// Arrange
		LocalDate timeNow = java.time.LocalDate.now();
		double payedCentAmount = 100;
		
		tempPBuy = new PBuy();
		
		PPayStation pStat = new PPayStation(1, "P-423E");
		pStat.setAmount(payedCentAmount);
		tempPBuy.setAssociatedPaystation(pStat);
		tempPBuy.setBuyTime(timeNow);
		
		DatabasePBuy dbPbuy = new DatabasePBuy();
		
		// Act
		int key = 0;
		try {
			key = dbPbuy.insertParkingBuy(tempPBuy);
		} catch (DatabaseLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// Assert
		assertTrue(key > 0);
		
	}	
	
	
	@Test
	public void wasRetrievedPriceDatabaselayer() {
		// Arrange
		DatabasePPrice dbPrice = new DatabasePPrice();

		// Act
		//Nothing to do
		
		// Assert
		assertNotNull(dbPrice);
		
	}
	
	
	@Test
	public void wasRetrievedPriceControllayer() {
		// Arrange
		ControlPrice controlPrice = new ControlPrice();
		
		// Act
		// Nothing to do
		
		// Assert
		assertNotNull(controlPrice);
	}
	
	/** Fixture for pay station testing. */
	@AfterEach
	public void cleanUp() {
		DBConnection.closeConnection();
	}	
	
	@AfterAll
	public static void cleanUpWhenFinish() {
		// Arrange
		DatabasePBuy dbPbuy = new DatabasePBuy();
		int numDeleted = 0;
		
		// Act
		try {
			numDeleted = dbPbuy.deleteParkingBuy(tempPBuy);
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		} finally {
			DBConnection.closeConnection();
		}
	
		// Assert
		assertTrue(numDeleted > 0);
	}
}
