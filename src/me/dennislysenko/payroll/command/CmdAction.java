package me.dennislysenko.payroll.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import me.dennislysenko.payroll.api.Table;
import me.dennislysenko.payroll.core.ThreadInput;
import me.dennislysenko.payroll.type.Client;
import me.dennislysenko.payroll.type.Command;
import me.dennislysenko.payroll.type.Data;
import me.dennislysenko.payroll.type.PutAction;

public class CmdAction extends Command {

	private Client client;
	private String action;
	private String reference;
	private Integer actionId;
	private Integer amount;

	public CmdAction() {
		LABEL = "ACTION";
		USAGE = "/action";
		DESCRIPTION = "Adds an action into the pay roll.";
	}

	@Override
	public void execute(String[] args) {
		if (args.length == 0) {
			help(args);
		}
		else {
			if (args[0].equalsIgnoreCase("help")) {
				help(args);
			}
			else if (args[0].equalsIgnoreCase("add")) {
				add(args);
			}
			else if (args[0].equalsIgnoreCase("list")) {
				list(args);
			}
		}
	}
	
	private void help(String[] args) {
		System.out.println("Action Manager - Help");
		System.out.println("/action help\tShows this screen.");
		System.out.println("/action add\tAdds an action.");
		System.out.println("/action list [days]\tLists actions");
	}
	
	private void add(String[] args) {
		ThreadInput.stopThread();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.print("Client: ");
				client = Client.getClient(reader.readLine());
				if (client == null) {
					System.out.println(client + " does not exist! Type: /client");
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
				String[] h = {"Date", "Client", "Reference", "Action", "Amount"};
				Table t = new Table(h);
				Calendar cal = Calendar.getInstance();
				t.addData(0, (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH));
				t.addData(1, client.getLabel());
				t.addData(2, reference);
				t.addData(3, PutAction.getAction(actionId).getLabel());
				t.addData(4, amount.toString());
				t.setMarginRight(1);
				t.print();
				Data.addData(client, amount, reference.replace(":", ";"), PutAction.getAction(action));
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ThreadInput.startThread();
	}
	
	private void list(String[] args) {
		if (args.length == 1) {
			String[] h = {"Date", "Amount", "Client", "Description", "Action"};
			Table t = new Table(h);
			t.setMarginRight(1);
			for (Data data : Data.getData()) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(data.getTimestamp());
				
				t.addData(0, (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH));
				t.addData(1, "$" + data.getAmount());
				if (data.getAction().equals(PutAction.PAYCHECK)) {
					t.addData(2, "");
				}
				else {
					t.addData(2, data.getClient().getLabel());
				}
				t.addData(3, data.getReference());
				t.addData(4, data.getAction().getLabel());
			}
			t.print();
		}
	}

}
