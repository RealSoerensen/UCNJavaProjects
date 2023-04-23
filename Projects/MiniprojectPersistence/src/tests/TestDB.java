package tests;

import org.junit.jupiter.api.Test;
import dal.DBConnection;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestDB {
    DBConnection dbConnection;

    public TestDB() throws SQLException {
        dbConnection = DBConnection.getInstance();
    }

    @Test
    public void shouldStartConnectionToDB() throws SQLException {
    	//Arrange
    	
    	//Act
        DBConnection.getInstance(); //Creates connection upon instance creation.
        
        //Assert
        assertTrue(dbConnection.getOpenStatus());
    }
    
    @Test
    public void shouldCloseConnectionToDB() {
    	//Arrange
    	
    	//Act
    	DBConnection.closeConnection();
    	
    	//Assert
        assertFalse(dbConnection.getOpenStatus());
    }
}
