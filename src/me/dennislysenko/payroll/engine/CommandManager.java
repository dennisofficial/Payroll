package me.dennislysenko.payroll.engine;

import java.util.ArrayList;
import java.util.List;

import me.dennislysenko.payroll.command.CmdHelp;
import me.dennislysenko.payroll.type.Command;

public class CommandManager {

	private static List<Command> commands = new ArrayList<Command>();
	
	public static void registerCommands() {
		commands.add(new CmdHelp());
		commands.add(new CmdPut());
	}
	
	public static List<Command> getCommands() {
		return commands;
	}
	
	public static void execute(String input) {
		String label = input.split(" ")[0];
		Boolean found = false;
		for (Command command : commands) {
			if (command.getLabel().equalsIgnoreCase(label)) {
				command.execute();
				found = true;
			}
		}
		if (!found) {
			System.out.println("Command not found. Type: /help");
		}
	}
	
}
