package controllayer;

import databaselayer.DatabaseLayerException;
import databaselayer.IDbPPrice;
import databaselayer.DatabasePPrice;
import modellayer.PPrice;

public class ControlPrice {
	
	private final IDbPPrice dbPrice;
	
	public ControlPrice() {
		this.dbPrice = new DatabasePPrice();
	}
	
	public PPrice getCurrentPrice() {
		return new PPrice();
	}
	
	public PPrice getPriceRemote(int zoneId) {
		// Get price from Parkingsystem DB
		PPrice price;
		try{
			price = dbPrice.getPriceByZoneId(zoneId);
		} catch (DatabaseLayerException e) {
			price = getCurrentPrice();
		}
		return price;
	}

}
