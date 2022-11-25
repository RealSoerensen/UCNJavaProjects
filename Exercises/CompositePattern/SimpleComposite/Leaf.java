package Exercises.CompositePattern.SimpleComposite;

public class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void operation() {
        System.out.println(getName());
    }
}
