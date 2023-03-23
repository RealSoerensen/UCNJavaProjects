package dal;

import java.util.ArrayList;
import java.util.List;

import model.ToDoListItem;

public class ToDoListContainer implements ToDoListDataAccessIF{
	private List<ToDoListItem> container;
	private static ToDoListContainer instance;
	
	public ToDoListContainer() {
		container = new ArrayList<>();
		container.add(new ToDoListItem("Hænge tøj op", false));
		container.add(new ToDoListItem("Tage opvasken", true));
		container.add(new ToDoListItem("Gå ud med skraldet", false));
	}
	
	public static ToDoListContainer getInstance() {
		if(instance == null) {
			instance = new ToDoListContainer();
		}
		return instance;
	}

	@Override
	public List<ToDoListItem> getAllItems() {
		return container;
	}
}
