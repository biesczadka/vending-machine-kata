package tdd.vendingMachine;

import exceptions.NotEnoughSpaceException;
import exceptions.WrongShelfNumberException;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

	private HashMap<Integer, Shelf> shelves;
	private CashBox cashBox;
	private Display display;
	private Integer chosenShelf;
	private HashMap<Coin, Integer> insertedCoins;
	private FeederBox feederBox;

	public VendingMachine(int numberOfShelves, int shelfCapacity, CashBox cashBox, Display display, FeederBox feederBox) {

		this.shelves = new HashMap<Integer, Shelf>();
		insertedCoins = getEmptyCoinMap();
		this.display = display;
		this.cashBox = cashBox;
		chosenShelf = null;
		this.feederBox = feederBox;

		for (int shelfNumber = 1; shelfNumber <= numberOfShelves; shelfNumber++) {
			Shelf shelf = new Shelf(shelfNumber, shelfCapacity);
			shelves.put(shelfNumber, shelf);
		}
	}

	public void addProduct(Product product, Integer amount, Integer shelfNumber) throws WrongShelfNumberException, NotEnoughSpaceException {

		if (shelfNumber > shelves.size()) throw new WrongShelfNumberException();
		Shelf shelf = shelves.get(shelfNumber);
		if (shelf.getCapacity() < amount) throw new NotEnoughSpaceException();
		shelf.setProduct(product);
		shelf.setProductAmount(amount);

	}

	public void chooseShelf(Integer shelfNumber) {
		if (shelfNumber > shelves.size()) {
			display.showMessage(Display.WRONG_SHELF);
			return;
		}
		if (shelves.get(shelfNumber).getProductAmount() <= 0) {
			display.showMessage(Display.EMPTY_SHELF);
			return;
		}
		chosenShelf = shelfNumber;
	}

	public void insertCoin(Coin coin) {
		Integer amount = insertedCoins.get(coin);
		amount++;
		insertedCoins.put(coin, amount);
		if (chosenShelf != null) {
			if (sumCoinsInMap(insertedCoins) >= shelves.get(chosenShelf).getProduct().getPrice()) {
				HashMap<Coin, Integer> change = cashBox.calculateChange(insertedCoins, shelves.get(chosenShelf).getProduct().getPrice());

				if (change != null) {
					shelves.get(chosenShelf).decreaseProductAmount();
					giveProductAndChange(change, chosenShelf);
				} else {
					display.showMessage(Display.CHANGE_IMPOSSIBLE);
					giveBackMoney(insertedCoins);
				}
				chosenShelf = null;

			}
		} else {
			giveBackMoney(insertedCoins);
			display.showMessage(Display.NO_CHOSEN_SHELF);
		}
	}

	public void cancel() {
		giveBackMoney(insertedCoins);
		chosenShelf = null;
	}


	private void giveProductAndChange(HashMap<Coin, Integer> coinsAmount, Integer shelfNumber) {
		feederBox.giveProductAndChange(coinsAmount, shelfNumber);

	}

	private void giveBackMoney(HashMap<Coin, Integer> coinsAmount) {
		insertedCoins = getEmptyCoinMap();
		feederBox.giveBackMoney(coinsAmount);
	}

	private Double sumCoinsInMap(HashMap<Coin, Integer> coinMap) {
		Double sum = 0.0;
		for (Map.Entry<Coin, Integer> coinIntegerEntry : coinMap.entrySet()) {
			sum += (coinIntegerEntry.getKey().value * coinIntegerEntry.getValue());
		}
		return sum;
	}

	private HashMap<Coin, Integer> getEmptyCoinMap() {

		HashMap<Coin, Integer> coinsMap = new HashMap<Coin, Integer>();
		coinsMap.put(Coin.FIVE_ZL, 0);
		coinsMap.put(Coin.TWO_ZL, 0);
		coinsMap.put(Coin.ONE_ZL, 0);
		coinsMap.put(Coin.FIFTY_GR, 0);
		coinsMap.put(Coin.TWENTY_GR, 0);
		coinsMap.put(Coin.TEN_GR, 0);
		return coinsMap;

	}


	public String getDisplayMessage() {
		return display.getCurrentMessage();
	}
}
