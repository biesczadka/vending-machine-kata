package tdd.vendingMachine;

/**
 * Created by Martyna on 2015-08-09.
 */

public enum Coin {

	FIVE_ZL(5.0),
	TWO_ZL(2.0),
	ONE_ZL(1.0),
	FIFTY_GR(0.5),
	TWENTY_GR(0.2),
	TEN_GR(0.1);

	Double value;

	private Coin(Double value) {
		this.value = value;
	}
}