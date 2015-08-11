package tdd.vendingMachine;

import java.util.HashMap;

/**
 * Created by TrTech on 2015-08-10.
 */
public class CashBox {

	private int fiveZlCoinsAmount;
	private int twoZlCoinsAmount;
	private int oneZlCoinsAmount;
	private int fifteenGrCoinsAmount;
	private int twentyGrCoinsAmount;
	private int tenGrCoinsAmount;

	public int getFiveZlCoinsAmount() {
		return fiveZlCoinsAmount;
	}

	public void setFiveZlCoinsAmount(int fiveZlCoinsAmount) {
		this.fiveZlCoinsAmount = fiveZlCoinsAmount;
	}

	public int getTenGrCoinsAmount() {
		return tenGrCoinsAmount;
	}

	public void setTenGrCoinsAmount(int tenGrCoinsAmount) {
		this.tenGrCoinsAmount = tenGrCoinsAmount;
	}

	public int getTwentyGrCoinsAmount() {
		return twentyGrCoinsAmount;
	}

	public void setTwentyGrCoinsAmount(int twentyGrCoinsAmount) {
		this.twentyGrCoinsAmount = twentyGrCoinsAmount;
	}

	public int getFifteenGrCoinsAmount() {
		return fifteenGrCoinsAmount;
	}

	public void setFifteenGrCoinsAmount(int fifteenGrCoinsAmount) {
		this.fifteenGrCoinsAmount = fifteenGrCoinsAmount;
	}

	public int getOneZlCoinsAmount() {
		return oneZlCoinsAmount;
	}

	public void setOneZlCoinsAmount(int oneZlCoinsAmount) {
		this.oneZlCoinsAmount = oneZlCoinsAmount;
	}

	public int getTwoZlCoinsAmount() {
		return twoZlCoinsAmount;
	}

	public void setTwoZlCoinsAmount(int twoZlCoinsAmount) {
		this.twoZlCoinsAmount = twoZlCoinsAmount;
	}

	public HashMap<Coin, Integer> calculateChange(HashMap<Coin, Integer> insertedCoins, Double productPrice) {
		//tutaj można zaimplemetować dowolny algorytm obliczania reszty
		return null;
	}
}
