package me.dennislysenko.payroll.command;

import me.dennislysenko.payroll.type.Command;
import me.dennislysenko.payroll.type.Data;
import me.dennislysenko.payroll.type.PutAction;

public class CmdPaycheck extends Command {

	public CmdPaycheck() {
		LABEL = "PAYCHECK";
		USAGE = "/paycheck [amount]";
		DESCRIPTION = "Adds a paycheck action.";
	}
	
	@Override
	public void execute(String[] args) {
		Integer paid;
		if (args.length == 0 ) {
			Integer paycheck = 0;
			for (Data data : Data.getData()) {
				if (data.getAction().equals(PutAction.PAYCHECK)) {
					paycheck -= data.getAmount();
				}
				else {
					paycheck += data.getAmount();
				}
			}
			System.out.println("Paycheck: $" + paycheck);
		}
		else if (args.length == 1) {
			try {
				paid = new Integer(args[0]);
				Data.addData(paid);
				System.out.println("Recorded $" + paid + " to database!");
			} catch (NumberFormatException ex) {
				System.out.println("Please enter a number only!");
			}
		}
		else {
			System.out.println("Usage: " + USAGE);
		}
	}

}
