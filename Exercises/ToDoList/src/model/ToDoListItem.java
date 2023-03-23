package model;

public class ToDoListItem {
	private String name;
	private boolean done;
	
	public ToDoListItem(String name, boolean done) {
		this.name = name;
		this.done = done;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	
	@Override
	public String toString() {
		return ("Name: " + getName() + "\nDone: " + isDone());
	}
}
