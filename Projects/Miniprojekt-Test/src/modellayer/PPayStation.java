package modellayer;

import controllayer.ControlPrice;
import controllayer.IllegalCoinException;
import databaselayer.DatabaseLayerException;
import utility.Validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Inspired by the book: Flexible, Reliable Software
 * Henrik B�rbak Christensen: Flexible, Reliable Software. Taylor and Francis
 * Group, LLC 2010
 */

public class PPayStation {

	// PayStation ident
	private int id;
	// PayStaion model
	private String payStationModel;
	// Amount inserted in pay station
	private double amount = 0;
	private final ControlPrice controlPrice;

	public PPayStation(int id, String payStationModel) {
		setId(id);
		setPayStationModel(payStationModel);
		this.controlPrice = new ControlPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPayStationModel() {
		return payStationModel;
	}

	public void setPayStationModel(String payStationModel) {
		this.payStationModel = payStationModel;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void addAmount(Coin coin) {

		Currency.ValidCurrency currency = coin.getCurrency();

		double valueInCent;

		if (currency == Currency.ValidCurrency.DKK) {
			try {
				validateCoin(coin);
			} catch (IllegalCoinException e) {
				System.out.println(e.getMessage());
			}
			PPrice nowPrice = controlPrice.getPriceRemote(getId());

			valueInCent = getDkkCoinValueInCent(coin, nowPrice);
		} else {
			valueInCent = getEuroCoinValueInCent(coin);
		}

		this.amount += valueInCent;
	}

	public int getTimeBoughtInMinutes() {
		PPrice aPrice = controlPrice.getCurrentPrice();
		int timeBoughtInMinutes;

		double timeBoughtInSeconds = this.amount * aPrice.getParkingPrice();
		timeBoughtInMinutes = (int) ((timeBoughtInSeconds + 59) / 60);

		return timeBoughtInMinutes;
	}

	public void validateCoin(Coin coin) throws IllegalCoinException {
		Validation.validateCoin(coin);
	}

	public double getEuroCoinValueInCent(Coin coin) {
		double valueInCent;
		double coinValue = coin.getAmount();

		if (coin.getCoinType() == Currency.ValidCoinType.INTEGER) {
			valueInCent = coinValue * 100;
		} else {
			valueInCent = coinValue;
		}

		return valueInCent;
	}

	public double getDkkCoinValueInCent(Coin coin, PPrice price) {
		double valueInCent;
		Currency.ValidCoinType coinType = coin.getCoinType();
		double coinValue = coin.getAmount();

		if (coinType == Currency.ValidCoinType.INTEGER) {
			valueInCent = (coinValue * 100) / price.getExchangeEuroDkk();
		} else {
			valueInCent = coinValue / price.getExchangeEuroDkk();
		}

		return valueInCent;
	}

	public ControlPrice getPrice() {
		return controlPrice;
	}

	public List<Coin> getValidCoins(List<Coin> coins) {
		List<Coin> validCoins = new ArrayList<>(List.of());

		for (Coin c : coins) {
			try {
				validateCoin(c);
				validCoins.add(c);
			} catch (IllegalCoinException e) {
				System.out.println(e.getMessage());
			}
		}

		return validCoins;
	}
}
