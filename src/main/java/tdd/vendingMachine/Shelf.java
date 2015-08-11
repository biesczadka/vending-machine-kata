package tdd.vendingMachine;

/**
 * Created by Martyna on 2015-08-09.
 */
public class Shelf {

	private Integer number;
	private Integer capacity;
	private Integer productAmount;
	private Product product;

	public Shelf(Integer number, Integer capacity) {
		this.number = number;
		this.capacity = capacity;
		productAmount = 0;
		product = null;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getProductAmount() {
		return productAmount;
	}

	public boolean setProductAmount(Integer productAmount) {
		if (productAmount <= capacity) {
			this.productAmount = productAmount;
			return true;
		} else {
			return false;
		}
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean increaseProductAmount() {
		if (productAmount + 1 <= capacity) {
			productAmount++;
			return true;
		} else {
			return false;
		}
	}

	public boolean decreaseProductAmount() {
		if (productAmount - 1 >= 0) {
			productAmount--;
			return true;
		} else {
			return false;
		}
	}
}
