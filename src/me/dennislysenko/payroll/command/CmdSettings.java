package me.dennislysenko.payroll.command;

import me.dennislysenko.payroll.type.Command;

public class CmdSettings extends Command {

	public CmdSettings() {
		LABEL = "SETTINGS";
		USAGE = "/settings";
		DESCRIPTION = "Show's settings, and allows for editing.";
	}
	
	@Override
	public void execute(String[] args) {
		
	}

}
