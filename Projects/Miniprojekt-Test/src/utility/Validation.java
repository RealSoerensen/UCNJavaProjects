package utility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import controllayer.IllegalCoinException;
import modellayer.Coin;
import modellayer.Currency;

public class Validation {
	private static final Set<Integer> VALID_DKKCOIN_VALUES = new HashSet<>(Arrays.asList(1, 2, 5, 10, 20));
	private static final Set<Integer> VALID_DKKORECOIN_VALUES = new HashSet<>(List.of(50));
	private static final Set<Integer> VALID_EURCOIN_VALUES = new HashSet<>(Arrays.asList(1, 2));
	private static final Set<Integer> VALID_CENTCOIN_VALUES = new HashSet<>(Arrays.asList(1, 2, 5, 10, 20, 50));
	
	public static void validateCoin(Coin coin) throws IllegalCoinException {
	    int coinValue = coin.getAmount();
	    Currency.ValidCurrency currency = coin.getCurrency();
	    boolean isValidCoin = switch (currency) {
			case EURO -> validateEuro(coin);
			case DKK -> validateDkk(coin);
			default -> throw new IllegalCoinException("Invalid coin: " + coinValue);
		};

		if (!isValidCoin) {
	        String currencyName = currency.name().toLowerCase();
	        throw new IllegalCoinException("Invalid " + currencyName + " coin: " + coinValue);
	    }
	}
	
	private static boolean validateEuro(Coin coin) {
	    Currency.ValidCoinType coinType = coin.getCoinType();
	    int amount = coin.getAmount();

		return switch (coinType) {
			case FRACTION -> testCentCoin(amount);
			case INTEGER -> testEuroCoin(amount);
		};
	}
	
	private static boolean validateDkk(Coin coin) {
	    Currency.ValidCoinType coinType = coin.getCoinType();
	    int amount = coin.getAmount();

		return switch (coinType) {
			case FRACTION -> testOreCoin(amount);
			case INTEGER -> testDkkCoin(amount);
		};
	}
	
	private static boolean testCoinValue(int coinValue, Set<Integer> validValues) {
	    return validValues.contains(coinValue);
	}

	private static boolean testCentCoin(int coinValue) {
	    return testCoinValue(coinValue, VALID_CENTCOIN_VALUES);
	}

	private static boolean testEuroCoin(int coinValue) {
	    return testCoinValue(coinValue, VALID_EURCOIN_VALUES);
	}

	private static boolean testOreCoin(int coinValue) {
	    return testCoinValue(coinValue, VALID_DKKORECOIN_VALUES);
	}

	private static boolean testDkkCoin(int coinValue) {
	    return testCoinValue(coinValue, VALID_DKKCOIN_VALUES);
	}
}
