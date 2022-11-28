package Exercises.CompositePattern.CompositeLivecode;

public class CategoryLine {
	private int quantity;
	private Material material;

	public CategoryLine(Material material, int quantity) {
		this.material = material;
		this.quantity = quantity;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
