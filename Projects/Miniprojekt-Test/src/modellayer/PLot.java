package modellayer;

import java.util.ArrayList;
import java.util.List;

//New class added
public class PLot {
	
	// Id of actual parkinglot
	private int id;
	private String name;
	private int zipCode;
	private List<PPayStation> paystations;

	// Hard coded value
	public PLot() {
		this.id = 1;
		this.name = "Parking Lot A";
		this.zipCode = 9000;
		paystations = new ArrayList<>();
	}
	
	// Dynamically set parkinglot
	public PLot(int id, String name, int zipCode) {
		this.id = id;
		this.name = name;
		this.zipCode = zipCode;
		paystations = new ArrayList<>();
	}
	
	public int getPLotId() {
		return id;
	}

	public void setPLotId(int pLotId) {
		this.id = pLotId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public List<PPayStation> getPaystations() {
		return paystations;
	}

	public void addPaystation(PPayStation paystation) {
		if(!paystations.contains(paystation)) {
			paystations.add(paystation);
		}
	}
	
	public void removePaystation(PPayStation paystation) {
		paystations.remove(paystation);
	}
}
