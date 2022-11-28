package Exercises.CompositePattern.CompositeLivecode;

public class Resources extends Material {
	private double price;

	public Resources(String name, double price) {
		super(name);
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double newPrice) {
		price = newPrice;
	}

	void printInfo() {
		System.out.println("Name: " + getName());
		System.out.println("Price: " + getPrice());

	}

}
