package Exercises.CompositePattern.CompositeInventory;

public class Tool implements Component {
    private String toolName;

    public Tool(String toolName) {
        super();
        this.toolName = toolName;
    }

    public String getToolName() {
        return toolName;
    }

    public void print() {
        System.out.println(getToolName());
    }
}