package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import databaselayer.DatabaseLayerException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import controllayer.*;
import modellayer.Coin;
import modellayer.Currency;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestLegalCoin {

	static ControlPayStation ps;

	/** Fixture for pay station testing. */
	@BeforeEach
	public void setUp() {
		ps = new ControlPayStation();
	}

	// Verify that all Legal DKK Fraction amounts are accepted.
	@Test
	public void shouldAcceptLegalDkkFractionAmount() throws IllegalCoinException, DatabaseLayerException {
		// Arrange
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		Coin coin = new Coin(50, coinCurrency, coinType);
		double coinValue = ps.getPPayStation().getDkkCoinValueInCent(coin, ps.getControlPrice().getCurrentPrice());

		// Act
		ps.addPayment(50, coinCurrency, coinType);

		// Assert
		assertEquals(coinValue, ps.getPPayStation().getAmount());
	}

	// Verify that all Legal DKK Integer amounts are accepted.
	@Test
	public void shouldAcceptLegalDkkIntegerAmount() {
		// Arrange
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;

		List<Coin> coins = Arrays.asList(new Coin(1, coinCurrency, coinType),
				new Coin(1, coinCurrency, coinType),
				new Coin(5, coinCurrency, coinType));

		double totalCoinValue = getTotalAmount(coins);

		// Act
		addToPaystation(coins, coinCurrency, coinType);

		// Assert
		assertEquals(totalCoinValue, ps.getPPayStation().getAmount(), 0.001);
	}

	// Verify that all Legal Euro Fraction amounts are accepted.
	@Test
	public void shouldAcceptLegalEuroFractionAmount() {
		// Arrange
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		List<Coin> coins = Arrays.asList(new Coin(1, coinCurrency, coinType),
				new Coin(2, coinCurrency, coinType),
				new Coin(5, coinCurrency, coinType),
				new Coin(10, coinCurrency, coinType),
				new Coin(20, coinCurrency, coinType),
				new Coin(50, coinCurrency, coinType));
		// Act
		double totalCoinValue = getTotalAmount(coins);

		addToPaystation(coins, coinCurrency, coinType);

		// Assert
		assertEquals(totalCoinValue, ps.getPPayStation().getAmount(), 0.001);
	}

	// Verify that all Legal Euro Integer amounts are accepted.
	@Test
	public void shouldAcceptLegalEuroIntegerAmount() {
		// Arrange
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		List<Coin> coins = List.of(
				new Coin(1, coinCurrency, coinType),
				new Coin(2, coinCurrency, coinType));

		double totalCoinValue = getTotalAmount(coins);

		// Act
		addToPaystation(coins, coinCurrency, coinType);

		// Assert
		assertEquals(totalCoinValue, ps.getPPayStation().getAmount(), 0.001);
	}

	// Verify multiple coins of different types are accepted.
	// Throw IllegalCoinException if coin is not euro or dkk.
	@Test
	public void shouldAcceptMultipleCoinsOfDifferentTypes() {
		// Arrange
		Currency.ValidCurrency eurCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCurrency dkkCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCurrency nokCurrency = Currency.ValidCurrency.NOK;
		Currency.ValidCoinType fractionType = Currency.ValidCoinType.FRACTION;
		Currency.ValidCoinType integerType = Currency.ValidCoinType.INTEGER;

		List<Coin> coins = List.of(
				new Coin(2, eurCurrency, integerType),
				new Coin(50, eurCurrency, fractionType),
				new Coin(20, dkkCurrency, integerType),
				new Coin(5, dkkCurrency, integerType),
				new Coin(5, nokCurrency, integerType)
		);

		// Act
		List<Coin> validCoins = ps.getPPayStation().getValidCoins(coins);
		double totalCoinValue = getTotalAmount(validCoins);

		validCoins.forEach(coin -> {
			try {
				ps.addPayment(coin.getAmount(), coin.getCurrency(), coin.getCoinType());
			} catch (IllegalCoinException | DatabaseLayerException e) {
				throw new RuntimeException(e);
			}
		});

		// Assert
		assertEquals(totalCoinValue, ps.getPPayStation().getAmount(), 0.001);
	}

	private double getTotalAmount(List<Coin> coins) {
		return coins.stream()
				.mapToDouble(coin -> {
					if (coin.getCurrency() == Currency.ValidCurrency.DKK) {
						return ps.getPPayStation().getDkkCoinValueInCent(coin, ps.getControlPrice().getCurrentPrice());
					} else if (coin.getCurrency() == Currency.ValidCurrency.EURO) {
						return ps.getPPayStation().getEuroCoinValueInCent(coin);
					} else {
						throw new RuntimeException("Invalid currency");
					}
				})
				.sum();
	}

	private void addToPaystation(List<Coin> coins, Currency.ValidCurrency coinCurrency, Currency.ValidCoinType coinType){
		coins.forEach(c -> {
			try {
				ps.addPayment(c.getAmount(), coinCurrency, coinType);
			} catch (IllegalCoinException | DatabaseLayerException e) {
				throw new RuntimeException(e);
			}
		});
	}

	@AfterAll
	public static void cleanUp() {
		ps.setReady();
	}
}
