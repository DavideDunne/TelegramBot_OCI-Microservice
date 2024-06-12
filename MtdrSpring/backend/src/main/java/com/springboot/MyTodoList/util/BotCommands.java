package com.springboot.MyTodoList.util;

/**
 * Enum class to store bot commands sent by the user.
 * @author Juarez Barbosa
 */
public enum BotCommands {

	START_COMMAND("/start"), 
	HIDE_COMMAND("/hide"), 
	TODO_LIST("/todolist"),
	MY_TASKS("/mytasks"),
	ADD_ITEM("/additem");

	private String command;

	BotCommands(String enumCommand) {
		this.command = enumCommand;
	}

	public String getCommand() {
		return command;
	}
}
