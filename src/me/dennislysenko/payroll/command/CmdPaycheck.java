package me.dennislysenko.payroll.command;

import me.dennislysenko.payroll.api.DataWriter;
import me.dennislysenko.payroll.type.Command;

public class CmdPaycheck extends Command {

	public CmdPaycheck() {
		LABEL = "PAYCHECK";
		USAGE = "/paycheck <amount>";
		DESCRIPTION = "Adds a paycheck action.";
	}
	
	@Override
	public void execute(String[] args) {
		Integer paid;
		if (args.length != 1) {
			System.out.println("Usage: " + USAGE);
		}
		else {
			try {
				paid = new Integer(args[0]);
				DataWriter.addData(paid);
				System.out.println("Recorded $" + paid + " to database!");
			} catch (NumberFormatException ex) {
				System.out.println("Please enter a number only!");
			}
		}
	}

}
