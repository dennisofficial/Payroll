package me.dennislysenko.payroll.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import me.dennislysenko.payroll.core.ThreadInput;
import me.dennislysenko.payroll.gfx.Table;
import me.dennislysenko.payroll.type.Command;
import me.dennislysenko.payroll.type.PutAction;

public class CmdPut extends Command {

	private String building;
	private String action;
	private String reference;
	private Integer actionId;
	
	public CmdPut() {
		LABEL = "PUT";
		USAGE = "/put";
		DESCRIPTION = "Adds an action into the pay roll.";
	}
	
	@Override
	public void execute(String[] args) {
		ThreadInput.stopThread();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Building: ");
			building = reader.readLine();
			
			while (true) {
				System.out.print("Action: ");
				action = reader.readLine();
				if (action.equalsIgnoreCase(PutAction.EMAIL.getLabel())) {
					System.out.print("Subject: ");
					reference = reader.readLine();
					actionId = PutAction.EMAIL.getID();
					break;
				}
				else if (action.equalsIgnoreCase(PutAction.PAID.getLabel())) {
					System.out.print("Invoice: ");
					reference = reader.readLine();
					actionId = PutAction.PAID.getID();
					break;
				}
				else if (action.equalsIgnoreCase(PutAction.TASK.getLabel())) {
					System.out.print("Task: ");
					reference = reader.readLine();
					actionId = PutAction.TASK.getID();
					break;
				}
				else if (action.equalsIgnoreCase(PutAction.QB.getLabel())) {
					System.out.print("IN/PL: ");
					reference = reader.readLine();
					actionId = PutAction.QB.getID();
					break;
				}
				else {
					System.out.println("Action not found!");
				}
			}
			String[] h = {"Building", "Reference", "Action"};
			Table table = new Table(h);
			table.addData(0, building);
			table.addData(1, reference);
			table.addData(2, PutAction.getAction(actionId).getLabel());
			table.setMarginRight(1);
			table.print();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ThreadInput.startThread();
	}

}
