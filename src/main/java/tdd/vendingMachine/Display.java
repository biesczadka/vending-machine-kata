package tdd.vendingMachine;

/**
 * Created by Martyna on 2015-08-09.
 */
public class Display {

	public static final String WRONG_SHELF = "Błędny numer półki";
	public static final String EMPTY_SHELF = "Półka jest pusta";
	public static final String CHANGE_IMPOSSIBLE = "Brak możliwości wydania reszty";
	public static final String NO_CHOSEN_SHELF = "Nie wybrano półki";

	private String currentMessage = "";

	public void showMessage(String message) {
		currentMessage = message;
	}

	public void clearMessage(String message) {
		currentMessage = "";
	}

	public String getCurrentMessage() {
		return currentMessage;
	}
}
