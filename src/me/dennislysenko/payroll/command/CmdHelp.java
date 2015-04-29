package me.dennislysenko.payroll.command;

import me.dennislysenko.payroll.engine.CommandManager;
import me.dennislysenko.payroll.type.Command;

public class CmdHelp extends Command {

	public CmdHelp() {
		LABEL = "HELP";
		USAGE = "/help";
		DESCRIPTION = "Shows all the commands.";
	}
	
	@Override
	public void execute() {
		for (Command command : CommandManager.getCommands()) {
			System.out.println(command.getUsage() + " - " + command.getDescription());
		}
	}

}
