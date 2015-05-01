package me.dennislysenko.payroll.managers;

import java.util.ArrayList;
import java.util.List;

import me.dennislysenko.payroll.command.CmdHelp;
import me.dennislysenko.payroll.command.CmdPaycheck;
import me.dennislysenko.payroll.command.CmdPut;
import me.dennislysenko.payroll.type.Command;

public class CommandManager {

	private static List<Command> commands = new ArrayList<Command>();
	
	public static void registerCommands() {
		commands.add(new CmdHelp());
		commands.add(new CmdPut());
		commands.add(new CmdPaycheck());
	}
	
	public static List<Command> getCommands() {
		return commands;
	}
	
	public static void execute(String input) {
		String label = input.split(" ")[0];
		String[] args;
		if (input.trim().split(" ").length == 1) {
			args = new String[0];
		}
		else {
			args = input.replaceFirst(label, "").trim().split(" ");
		}
		Boolean found = false;
		for (Command command : commands) {
			if (command.getLabel().equalsIgnoreCase(label)) {
				command.execute(args);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Command not found. Type: /help");
		}
	}
	
}
