package Exercises.CompositePattern.CompositeLivecode;

import java.util.ArrayList;
import java.util.List;

public class Category extends Material {
	private List<CategoryLine> myList;

	public Category(String name) {
		super(name);
		myList = new ArrayList<CategoryLine>();
	}

	public void addMaterial(Material material, int quantity) {
		CategoryLine cl = new CategoryLine(material, quantity);
		myList.add(cl);
	}

	@Override
	void printInfo() {
		System.out.println(getName());
		for (CategoryLine element : myList) {
			System.out.println(element.getQuantity());
			element.getMaterial().printInfo();
		}
	}

}
