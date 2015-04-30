package me.dennislysenko.payroll.command;

import me.dennislysenko.payroll.type.Command;

public class CmdPaycheck extends Command {

	public CmdPaycheck() {
		LABEL = "PAYCHECK";
		USAGE = "/paycheck <amount>";
		DESCRIPTION = "Adds a paycheck action.";
	}
	
	@Override
	public void execute(String[] args) {
	}

}
