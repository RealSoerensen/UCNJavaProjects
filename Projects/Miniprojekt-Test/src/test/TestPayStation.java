package test;

import controllayer.ControlPayStation;
import controllayer.ControlReceipt;
import controllayer.IllegalCoinException;
import databaselayer.DatabaseLayerException;
import modellayer.Currency;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPayStation {
    private static ControlPayStation ps;
    @BeforeEach
    public void setUp() {
        ps = new ControlPayStation();
    }

    @Test
    public void testBuy() throws IllegalCoinException, DatabaseLayerException {
        // Arrange
        ps.addPayment(2, Currency.ValidCurrency.EURO, Currency.ValidCoinType.INTEGER);
        int expected = ps.readDisplay();
        // Act
        ControlReceipt receipt = ps.buy();

        // Assert
        assertEquals(expected, receipt.getParkingTicketValue());
    }

    @AfterAll
    public static void tearDown() {
        ps = new ControlPayStation();
        ps.setReady();
    }
}
