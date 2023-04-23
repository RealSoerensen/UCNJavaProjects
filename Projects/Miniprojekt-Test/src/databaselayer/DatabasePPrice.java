package databaselayer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.SQLException;

import modellayer.*;

public class DatabasePPrice implements IDbPPrice {
	
	//Hardcoded for now. TODO: Use database
	public PPrice getCurrentPrice() {
		return new PPrice();
	}
	
	public PPrice getPriceByZoneId(int zoneId) throws DatabaseLayerException {
		PPrice foundPrice;
		
		Calendar calendar = Calendar.getInstance();
		Date dateNow = new Date(calendar.getTime().getTime());
		
		Connection con = DBConnection.getInstance().getDBcon();

		String baseSelect = "SELECT top 1 price, pZone_id FROM PPrice ";
		baseSelect += "WHERE pZone_id = " + zoneId + " AND starttime < '" + dateNow + "' ";
		baseSelect += "ORDER BY starttime DESC";

		ResultSet rs;
		int price;
		int pZoneId;
		String pZoneName;
		PZone pZone; 
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			// Todo: Get PPrice object from database
			rs = stmt.executeQuery(baseSelect);
			if (rs.next()) {
				price = rs.getInt("price");
				pZoneId = rs.getInt("pZone_id");
				pZoneName = rs.getString("name");
				pZone = new PZone(pZoneId, pZoneName);
				foundPrice = new PPrice(price, pZone);
			} else {
				foundPrice = null;
			}
			stmt.close();
		} catch (SQLException | NullPointerException  ex) {
			throw new DatabaseLayerException(ex.getMessage());
		} catch (Exception ex) {
			throw new DatabaseLayerException("Data not found! Technical error");
		} finally {
			DBConnection.closeConnection();
		}
				
		return foundPrice;
	}
}
