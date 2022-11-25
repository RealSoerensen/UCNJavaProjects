package Exercises.CompositePattern.SimpleComposite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private List<Component> list;

    public Composite() {
        super();
        this.list = new ArrayList<>();
    }

    public void operation() {
        for (Component leaf : list) {
            leaf.operation();
        }
    }

    public void add(Component leaf) {
        list.add(leaf);
    }

    public void remove(Component leaf) {
        list.remove(leaf);
    }

    public Component getChild(int i) {
        return list.get(i);
    }
}
