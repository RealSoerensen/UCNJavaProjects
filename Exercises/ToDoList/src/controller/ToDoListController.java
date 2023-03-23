package controller;

import java.util.ArrayList;
import java.util.List;

import dal.ToDoListDataAccessIF;
import model.ToDoListItem;

public class ToDoListController{
	
	private ToDoListDataAccessIF dataAccess;
	
	public ToDoListController(ToDoListDataAccessIF dataAccess) {
		setDataAccess(dataAccess);
	}

	public List<ToDoListItem> getUnfinishedItems(){
		List<ToDoListItem> unfinishedItems = new ArrayList<>();
		for(ToDoListItem item : dataAccess.getAllItems()) {
			if(!item.isDone()) {
				unfinishedItems.add(item);
			}
		}
		return unfinishedItems;
		
	}
	
	public void setDataAccess(ToDoListDataAccessIF dataAccess) {
		this.dataAccess = dataAccess;
	}
	
	public ToDoListDataAccessIF getDataAccess() {
		return dataAccess;
	}

}
