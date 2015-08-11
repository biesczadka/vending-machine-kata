package tdd.vendingMachine;


import exceptions.NotEnoughSpaceException;
import exceptions.WrongShelfNumberException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;

public class VendingMachineTest {

	VendingMachine vendingMachine;
	CashBox cashBox;
	Display display;
	FeederBox feederBox;

	@Before
	public void create() {
		cashBox = Mockito.mock(CashBox.class);
		feederBox = Mockito.mock(FeederBox.class);
		display = new Display();
		vendingMachine = new VendingMachine(3, 5, cashBox, display, feederBox);
	}

	@Test
	public void chooseShelfTest() {
		vendingMachine.chooseShelf(20);
		Assert.assertEquals(vendingMachine.getDisplayMessage(), Display.WRONG_SHELF);
		vendingMachine.chooseShelf(1);
		Assert.assertEquals(vendingMachine.getDisplayMessage(), Display.EMPTY_SHELF);
	}

	@Test(expected = NotEnoughSpaceException.class)
	public void addProductNotEnoughSpaceTest() throws WrongShelfNumberException, NotEnoughSpaceException {
		Product product = new Product(5.0, "Paluszki słone");
		vendingMachine.addProduct(product, 40, 3);
	}

	@Test(expected = WrongShelfNumberException.class)
	public void addProductWrongShelfNumberTest() throws WrongShelfNumberException, NotEnoughSpaceException {
		Product product = new Product(5.0, "Paluszki słone");
		vendingMachine.addProduct(product, 2, 31);
	}

	@Test
	public void changeImpossibleTest() throws WrongShelfNumberException, NotEnoughSpaceException {
		Mockito.when(cashBox.calculateChange(Mockito.any(HashMap.class), Mockito.any(Double.class))).thenReturn(null);
		Product product = new Product(4.0, "Paluszki słone");
		vendingMachine.addProduct(product, 3, 1);
		vendingMachine.chooseShelf(1);
		vendingMachine.insertCoin(Coin.FIVE_ZL);
		Assert.assertEquals(vendingMachine.getDisplayMessage(), Display.CHANGE_IMPOSSIBLE);
		Mockito.verify(cashBox, Mockito.times(1)).calculateChange(Mockito.any(HashMap.class), Mockito.any(Double.class));
		Mockito.verify(feederBox, Mockito.times(1)).giveBackMoney(Mockito.any(HashMap.class));
		vendingMachine.insertCoin(Coin.FIVE_ZL);
		Assert.assertEquals(vendingMachine.getDisplayMessage(), Display.NO_CHOSEN_SHELF);
	}

	@Test
	public void buyProductTest() throws WrongShelfNumberException, NotEnoughSpaceException {
		Mockito.when(cashBox.calculateChange(Mockito.any(HashMap.class), Mockito.any(Double.class))).thenReturn(new HashMap());
		Product product = new Product(4.0, "Paluszki słone");
		vendingMachine.addProduct(product, 1, 1);
		vendingMachine.chooseShelf(1);
		vendingMachine.insertCoin(Coin.TWO_ZL);
		Mockito.verify(cashBox, Mockito.times(0)).calculateChange(Mockito.any(HashMap.class), Mockito.any(Double.class));
		vendingMachine.insertCoin(Coin.TWO_ZL);
		Mockito.verify(feederBox, Mockito.times(1)).giveProductAndChange(Mockito.any(HashMap.class), Mockito.any(Integer.class));
		Mockito.verify(cashBox, Mockito.times(1)).calculateChange(Mockito.any(HashMap.class), Mockito.any(Double.class));
	}

	@Test
	public void cancelTest() throws WrongShelfNumberException, NotEnoughSpaceException {
		Mockito.when(cashBox.calculateChange(Mockito.any(HashMap.class), Mockito.any(Double.class))).thenReturn(new HashMap());
		Product product = new Product(4.0, "Paluszki słone");
		vendingMachine.addProduct(product, 1, 1);
		vendingMachine.chooseShelf(1);
		vendingMachine.insertCoin(Coin.TWO_ZL);
		vendingMachine.cancel();
		Mockito.verify(feederBox, Mockito.times(1)).giveBackMoney(Mockito.any(HashMap.class));
	}

}
