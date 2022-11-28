package Exercises.CompositePattern.CompositeLivecode;

public class TryMe {

	public static void main(String[] args) {
		Category paintPackage = new Category("Malingpakke");
		Category builderPackage = new Category("Bygningpakke");

		paintPackage.addMaterial(new Resources("Malerpensel", 2), 2);
		builderPackage.addMaterial(new Resources("Hammer", 3), 1);

		paintPackage.printInfo();
		builderPackage.printInfo();

	}

}
