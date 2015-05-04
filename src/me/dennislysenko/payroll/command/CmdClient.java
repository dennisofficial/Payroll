package me.dennislysenko.payroll.command;

import me.dennislysenko.payroll.type.Command;

public class CmdClient extends Command {

	public CmdClient() {
		LABEL = "CLIENT";
		USAGE = "/client";
		DESCRIPTION = "Client Editor.";
	}
	
	@Override
	public void execute(String[] args) {
	}

}
