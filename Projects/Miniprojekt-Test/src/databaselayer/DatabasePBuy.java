package databaselayer;

import java.sql.*;
import java.lang.NullPointerException;

import modellayer.PBuy;
import modellayer.PPayStation;

public class DatabasePBuy implements IDbPBuy {

	// example using embedded SQL
	public int insertParkingBuy(PBuy parkingBuy) throws DatabaseLayerException {
		int insertedKey = 1;
		
		java.sql.Date sqldate = java.sql.Date.valueOf(parkingBuy.getBuyTime());
		PPayStation payStation = parkingBuy.getAssociatedPaystation();
		
		int parkingDuration = payStation.getTimeBoughtInMinutes();
		double payedCentAmount = payStation.getAmount();

		Connection con = DBConnection.getInstance().getDBcon();

		String baseInsert = "INSERT INTO PBuy (buyTime, duration, payedAmount, pPaystation_id) VALUES ";
		baseInsert += "(" + sqldate + ", " + parkingDuration + ", " + payedCentAmount + ", " + payStation.getId() + ")";

		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			stmt.executeUpdate(baseInsert, Statement.RETURN_GENERATED_KEYS);
	
			ResultSet rs = stmt.getGeneratedKeys();
		    if (rs.next()) {
		    	insertedKey = rs.getInt(1);
		    }
		    stmt.close();
		} catch (SQLException | NullPointerException  ex) {
			throw new DatabaseLayerException(ex.getMessage());
		} catch (Exception ex) {
			throw new DatabaseLayerException("Data not found! Technical error");
		} finally {
			DBConnection.closeConnection();
		}
		
		return insertedKey;
	}

	// example using prepared stmt
	public int deleteParkingBuy(PBuy parkingBuy) throws DatabaseLayerException {
		int numRowsDeleted;
		Connection con;

		String baseDelete = "DELETE FROM PBuy";

		try {
			con = DBConnection.getInstance().getDBcon();
			PreparedStatement pstmt = con.prepareStatement(baseDelete);
			numRowsDeleted = pstmt.executeUpdate();
		} catch (SQLException ex) {
			throw new DatabaseLayerException("Error deleting data");
		} catch (NullPointerException ex) {
			throw new DatabaseLayerException("Null pointer exception - possibly Connection object");
		} catch (Exception ex) {
			throw new DatabaseLayerException("Data not deleted! Technical error");
		} finally {
			DBConnection.closeConnection();
		}
		
		return numRowsDeleted;
	}

}
