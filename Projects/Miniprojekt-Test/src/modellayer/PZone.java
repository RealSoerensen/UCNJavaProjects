package modellayer;

import java.util.ArrayList;
import java.util.List;

public class PZone {
	
	// Id of actual parkingzone
	private int id;
	private String name;
	private List<PLot> parkingLots; //New List
	private PPrice price; //New variable

	// Hard coded value
	public PZone() {
		this.id = 2;
		this.name = "Zone B";
		parkingLots = new ArrayList<>(); //New Code
	}
	
	// Dynamically set parkingzone
	public PZone(int pZoneIdent, String pZoneName) {
		this.id = pZoneIdent;
		this.name = pZoneName;
		parkingLots = new ArrayList<>(); //New Code
		price = new PPrice(); //New Code
	}	
	
	public int getpZoneId() {
		return id;
	}

	public void setpZoneId(int pZoneId) {
		this.id = pZoneId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	//New Method
	public List<PLot> getParkingLots() {
		return parkingLots;
	}

	//New Method
	public void addParkingLot(PLot parkingLot) {
		if(!parkingLots.contains(parkingLot)) {
			parkingLots.add(parkingLot);
		}
	}
	
	//New Method
	public void removeParkingLot(PLot parkingLot) {
		parkingLots.remove(parkingLot);
	}
}
