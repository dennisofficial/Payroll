package me.dennislysenko.payroll.command;

import me.dennislysenko.payroll.core.Main;
import me.dennislysenko.payroll.type.Command;

public class CmdStop extends Command {

	public CmdStop() {
		LABEL = "STOP";
		USAGE = "/stop";
		DESCRIPTION = "Stops the program.";
	}
	
	@Override
	public void execute(String[] args) {
		System.out.println(Main.TITLE + " v" + Main.VERSION + " - Shutting Down!");
		System.exit(0);
	}

}
