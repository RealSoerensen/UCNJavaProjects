package dal;

import java.util.List;

import model.ToDoListItem;

public interface ToDoListDataAccessIF {
	public List<ToDoListItem> getAllItems();
}
