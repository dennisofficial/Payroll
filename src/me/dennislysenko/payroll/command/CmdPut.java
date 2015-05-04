package me.dennislysenko.payroll.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import me.dennislysenko.payroll.api.DataWriter;
import me.dennislysenko.payroll.api.Table;
import me.dennislysenko.payroll.api.TimeStamp;
import me.dennislysenko.payroll.core.ThreadInput;
import me.dennislysenko.payroll.managers.ClientManager;
import me.dennislysenko.payroll.type.Client;
import me.dennislysenko.payroll.type.Command;
import me.dennislysenko.payroll.type.PutAction;

public class CmdPut extends Command {

	private Client building;
	private String action;
	private String reference;
	private Integer actionId;
	private Integer amount;

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

			while (true) {
				System.out.print("Building: ");
				building = Client.getClient(reader.readLine());
				if (building == null) {
					System.out.println(building + " does not exist! Type: /client");
					break;
				}
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
						reference = reader.readLine().toUpperCase();
						actionId = PutAction.QB.getID();
						break;
					}
					else {
						System.out.println("Action not found!");
					}
				}
				while (true) {
					try {
						System.out.print("Action Rate: ");
						String temp = reader.readLine();
						if (temp.isEmpty()) {
							amount = 3;
						}
						else {
							amount = new Integer(temp);
						}
						break;
					} catch (NumberFormatException ex) {
						System.out.println("Please enter a number!");
					}
				}
				String[] h = {"Date", "Building", "Reference", "Action", "Amount"};
				Table table = new Table(h);
				Calendar cal = Calendar.getInstance();
				table.addData(0, (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH));
				table.addData(1, building.getLabel());
				table.addData(2, reference);
				table.addData(3, PutAction.getAction(actionId).getLabel());
				table.addData(4, amount.toString());
				table.setMarginRight(1);
				table.print();
				DataWriter.addData(building, reference.replace(":", ";"), PutAction.getAction(action), amount);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ThreadInput.startThread();
	}

}
