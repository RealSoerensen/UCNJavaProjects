package Exercises.CompositePattern.CompositeInventory;

public class Main {
    public static void main(String[] args) {
        Packages packages = new Packages("Packages");
        Packages painterPackage = new Packages("Painter Package");
        painterPackage.add(new Tool("Paint Brush"));
        painterPackage.add(new Tool("Paint Roller"));
        painterPackage.add(new Tool("Paint Tray"));

        Packages carpenterPackage = new Packages("Carpenter Package");
        carpenterPackage.add(new Tool("Hammer"));
        carpenterPackage.add(new Tool("Saw"));
        carpenterPackage.add(new Tool("Nails"));

        Packages handymanPackage = new Packages("Handyman Package");
        handymanPackage.add(painterPackage);
        handymanPackage.add(carpenterPackage);

        packages.add(painterPackage);
        packages.add(carpenterPackage);
        packages.add(handymanPackage);

        packages.print();
    }
}
