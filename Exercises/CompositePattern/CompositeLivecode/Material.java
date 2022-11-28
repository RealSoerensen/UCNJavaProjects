package Exercises.CompositePattern.CompositeLivecode;

public abstract class Material {
	private String name;

	public Material(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	abstract void printInfo();
}
