package me.dennislysenko.payroll.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import me.dennislysenko.payroll.core.ThreadInput;
import me.dennislysenko.payroll.type.Command;
import me.dennislysenko.payroll.type.PutAction;

public class CmdPut extends Command {

	private String building;
	private String action;
	private String reference;
	
	public CmdPut() {
		LABEL = "PUT";
		USAGE = "/put";
		DESCRIPTION = "Adds a task into the pay roll.";
	}
	
	@Override
	public void execute() {
		ThreadInput.stopThread();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Building: ");
			building = reader.readLine();
			System.out.print("Action: ");
			action = reader.readLine();
			if (action.equalsIgnoreCase(PutAction.EMAIL.getLabel())) {
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
