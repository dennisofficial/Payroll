package me.dennislysenko.payroll.type;

import java.util.ArrayList;
import java.util.List;

import me.dennislysenko.payroll.command.CmdAction;
import me.dennislysenko.payroll.command.CmdClear;
import me.dennislysenko.payroll.command.CmdClient;
import me.dennislysenko.payroll.command.CmdHelp;
import me.dennislysenko.payroll.command.CmdPaycheck;
import me.dennislysenko.payroll.command.CmdStop;

public abstract class Command {

	private static List<Command> commands = new ArrayList<Command>();
	
	public String LABEL;
	public String USAGE;
	public String DESCRIPTION;
	
	public abstract void execute(String[] args);

	public String getLabel() {
		return LABEL;
	}
	
	public String getUsage() {
		return USAGE;
	}
	
	public String getDescription() {
		return DESCRIPTION;
	}
	
	public static void registerCommands() {
		commands.add(new CmdHelp());
		commands.add(new CmdAction());
		commands.add(new CmdPaycheck());
		commands.add(new CmdClient());
		commands.add(new CmdStop());
		commands.add(new CmdClear());
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
			System.out.println("Unknown Command: /help");
		}
	}
	
}
