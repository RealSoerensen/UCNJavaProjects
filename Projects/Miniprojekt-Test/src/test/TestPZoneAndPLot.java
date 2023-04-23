package test;

import modellayer.PLot;
import modellayer.PPayStation;
import modellayer.PZone;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPZoneAndPLot {
    private static PZone pz;
    
    @BeforeEach
    public void setUp() {
        pz = new PZone();
    }

	@Test
	public void shouldAddThreePLots() {
		// Arrange
		PLot pLot1 = new PLot();
		PLot pLot2 = new PLot();
		PLot pLot3 = new PLot();

		List<PLot> expectedLots = Arrays.asList(pLot1, pLot2, pLot3);

		// Act
		expectedLots.forEach(pz::addParkingLot);

		// Assert
		List<PLot> actualLots = pz.getParkingLots();
		assertEquals(expectedLots.size(), actualLots.size());
		assertTrue(actualLots.containsAll(expectedLots));
	}

	@Test
	public void shouldRemoveTwoPLot() {
		// Arrange
		List<PLot> pLots = Arrays.asList(new PLot(), new PLot(), new PLot());
		for(PLot pLot : pLots) {
			pz.addParkingLot(pLot);
		}

		// Act
		for(int i = 0; i < 2; i++) {
			pz.removeParkingLot(pz.getParkingLots().get(0));
		}

		// Assert
		assertEquals(1, pz.getParkingLots().size());
	}

	@Test
	public void shouldAddPayStationToParkingLot() {
		// Arrange
		PLot parkingLot = new PLot();
		PPayStation payStation = new PPayStation(1, "Model 1");

		// Act
		parkingLot.addPaystation(payStation);

		// Assert
		assertEquals(1, parkingLot.getPaystations().size());
	}

	@Test
	public void shouldRemovePayStationFromParkingLot() {
		// Arrange
		PLot parkingLot = new PLot();
		PPayStation payStation = new PPayStation(1, "Model 1");
		parkingLot.addPaystation(payStation);

		// Act
		parkingLot.removePaystation(payStation);

		// Assert
		assertEquals(0, parkingLot.getPaystations().size());
	}

	@Test
	public void shouldRetrieveCorrectInformationFromParkingLot() {
		// Arrange
		int id = 69;
		String name = "Test Parking Lot";
		int zipCode = 9000;
		PLot parkingLot = new PLot(id, name, zipCode);
		pz.addParkingLot(parkingLot);

		// Act
		PLot retrievedParkingLot = pz.getParkingLots().get(0);

		// Assert
		assertEquals(id, retrievedParkingLot.getPLotId());
		assertEquals(name, retrievedParkingLot.getName());
		assertEquals(zipCode, retrievedParkingLot.getZipCode());
	}
    
    @Test
    public void shouldGetCorrectInformationFromPPayStation() {
        // Arrange
    	int paystationId = 42;
    	String modelName = "Model 1";
    	
    	PLot pLot1 = new PLot();
    	pz.addParkingLot(pLot1);
    	
    	PPayStation paystation = new PPayStation(paystationId, modelName);
    	pz.getParkingLots().get(0).addPaystation(paystation);
    	
        // Act
    	PPayStation retrievedPaystation = pz.getParkingLots().get(0).getPaystations().get(0);
		
        // Assert
    	assertEquals(paystationId, retrievedPaystation.getId());
    	assertEquals(modelName, retrievedPaystation.getPayStationModel());
    }

    @AfterAll
    public static void tearDown() {
    	pz = new PZone();
    }
}
