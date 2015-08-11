package tdd.vendingMachine;

/**
 * Created by Martyna on 2015-08-09.
 */
public class Product {

	private Double price;
	private String type;

	public Product(Double price, String type) {
		this.price = price;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
