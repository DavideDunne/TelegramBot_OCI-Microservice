package com.springboot.MyTodoList.util;

/**
 * Enum class to store bot labels (Buttons) sent to the user.
 * @author Juarez Barbosa
 */
public enum BotLabels {
	
	SHOW_MAIN_SCREEN("Show Main Screen"), 
	HIDE_MAIN_SCREEN("Hide Main Screen"),
	LIST_ALL_ITEMS("List All Items"), 
	ADD_NEW_ITEM("Add New Item"),
	DONE("DONE"),
	UNDO("UNDO"),
	DELETE("DELETE"),
	MY_TODO_LIST("MY TODO LIST"),
	ALL_TASKS_MANAGER("All tasks Manager"),
	MY_TASKS("MY TASKS"),
	COMPLETED_TASKS("Completed Tasks"),
	DASH("-");

	private String label;

	BotLabels(String enumLabel) {
		this.label = enumLabel;
	}

	public String getLabel() {
		return label;
	}

}
