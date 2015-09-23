package me.dennislysenko.payroll.command;

import me.dennislysenko.payroll.type.Command;

public class CmdClear extends Command {

	public CmdClear() {
		LABEL = "CLEAR";
		USAGE = "/clear";
		DESCRIPTION = "Clears Buffer Screen.";
	}
	
	@Override
	public void execute(String[] args) {
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
	}

}
