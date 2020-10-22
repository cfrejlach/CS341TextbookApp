package chris;

import java.io.Serializable;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 1234567L;
	
	private int sku;
	private int quantity;
	private double price;
	private String title;
	
	public Book(String title, int sku, double price, int quantity) {
		this.title = title;
		this.sku = sku;
		this.price = price;
		this.quantity = quantity;
	}

	public int getSku() {
		return sku;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}
	
	public String toString() {
		return " Title: " + this.title + " | SKU: " + this.sku +  " | Price: $" + this.price + " | Quantity: " + this.quantity
				+ " \n";
	}
	
}