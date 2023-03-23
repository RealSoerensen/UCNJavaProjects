
import java.util.List;

import controller.ToDoListController;
import dal.ToDoListContainer;
import model.ToDoListItem;

public class Main {

	public static void main(String[] args) {
		
		ToDoListController todoController = new ToDoListController(ToDoListContainer.getInstance());
		List<ToDoListItem> items = todoController.getUnfinishedItems();	
		for(ToDoListItem item : items) {
			System.out.println(item);
		}
	}

}
