package Exercises.CompositePattern.CompositeInventory;

import java.util.ArrayList;
import java.util.List;

public class Packages implements Component {
    private String name;
    private List<Component> components;

    public Packages(String name) {
        super();
        this.name = name;
        this.components = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void add(Component component) {
        components.add(component);
    }

    public void remove(Component component) {
        components.remove(component);
    }

    public void print() {
        System.out.println(getName());
        for (Component component : components) {
            component.print();
        }
    }
}
