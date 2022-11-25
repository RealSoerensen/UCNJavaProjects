package Exercises.CompositePattern.SimpleComposite;

public class Main {
    public static void main(String[] args) {
        Composite composite = new Composite();

        Leaf leaf1 = new Leaf("Leaf 1");
        Leaf leaf2 = new Leaf("Leaf 2");
        Leaf leaf3 = new Leaf("Leaf 3");
        composite.add(leaf1);
        composite.add(leaf2);
        composite.add(leaf3);

        Composite composite2 = new Composite();
        Leaf leaf4 = new Leaf("Leaf 4");
        composite2.add(leaf4);

        composite.add(composite2);
        composite.operation();

    }
}
