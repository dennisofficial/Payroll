package me.dennislysenko.payroll.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
			else {
				System.out.println("Unknown Sub Command: /action help");
			}
		}
	}
	
	private void help(String[] args) {
		System.out.println("Action Manager - Help");
		System.out.println("/action help\tShows this screen.");
		System.out.println("/action add\tAdds an action.");
		System.out.println("/action list [filter]\tLists actions");
	}
	
	private void add(String[] args) {
		ThreadInput.stopThread();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				System.out.print("Client: ");
				String input = reader.readLine();
				client = Client.getClient(input);
				if (client == null) {
					System.out.println(input + " does not exist! Type: /client");
					break;
				}
				while (true) {
					System.out.print("Action: ");
					action = reader.readLine();
					if (action.equalsIgnoreCase(PutAction.EMAIL.LABEL)) {
						System.out.print("Subject: ");
						reference = reader.readLine();
						actionId = PutAction.EMAIL.ID;
						break;
					}
					else if (action.equalsIgnoreCase(PutAction.PAID.LABEL)) {
						System.out.print("Invoice: ");
						reference = reader.readLine().toUpperCase();
						actionId = PutAction.PAID.ID;
						break;
					}
					else if (action.equalsIgnoreCase(PutAction.TASK.LABEL)) {
						System.out.print("Task: ");
						reference = reader.readLine();
						actionId = PutAction.TASK.ID;
						break;
					}
					else if (action.equalsIgnoreCase(PutAction.QB.LABEL)) {
						System.out.print("IN/PL: ");
						reference = reader.readLine().toUpperCase();
						actionId = PutAction.QB.ID;
						break;
					}
					else if (action.equalsIgnoreCase(PutAction.TAXES.LABEL)) {
						reference = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
						actionId = PutAction.QB.ID;
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
				t.addData(3, PutAction.getAction(actionId).LABEL);
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
		List<Data> datas = new ArrayList<Data>();
		if (args.length == 1) {
			datas = Data.getData();
		}
		else {
			// Filters:
			// -d:days
			// -c:client
			// -a:action
			
			HashMap<String, String> filters = new HashMap<String, String>();
			
			// Register Filters
			for (int i = 1; i < args.length; i++) {
				String filter = args[i];
				if (args[i].contains(":")) {
					filters.put(filter.split(":")[0], filter.split(":")[1]);
				}
				else {
					System.out.println("Syntax Error!");
					return;
				}
			}

			// Stops repeatable bug
			Boolean d = true;
			Boolean c = true;
			Boolean a = true;
			
			for (Data data : Data.getData()) {
				Boolean add = true;
				if (filters.containsKey("-d")) {
					if (d) {
						try {
							Integer value = new Integer(filters.get("-d"));
							Calendar cal = Calendar.getInstance();
							cal.setTimeInMillis(System.currentTimeMillis() - ((86400L * 1000) * value));
							if (data.getTimestamp() < cal.getTimeInMillis()) {
								add = false;
							}
						} catch (NumberFormatException ex) {
							System.out.println("Please enter a number for \"-d\" filter.");
							d = false;
						}
					}
				}
				if (filters.containsKey("-c")) {
					if (!data.getAction().equals(PutAction.PAYCHECK)) {
						Client client = Client.getClient(filters.get("-c"));
						if (client != null && c) {
							if (!data.getClient().equals(client)) {
								add = false;
							}
						}
						else {
							System.out.println("Client does not exist in filter.");
							c = false;
						}
					}
					else {
						add = false;
					}
				}
				if (filters.containsKey("-a")) {
					PutAction action = PutAction.getAction(filters.get("-a"));
					if (action != null && a) {
						if (!data.getAction().equals(action)) {
							add = false;
						}
					}
					else {
						System.out.println("Action does not exist in filter.");
						a = false;
					}
				}
				if (add) {
					datas.add(data);
				}
			}
		}
		String[] h = {"Date", "Amount", "Client", "Description", "Action"};
		Table t = new Table(h);
		t.setMarginRight(1);
		for (Data data : datas) {
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
			t.addData(4, data.getAction().LABEL);
		}
		t.print();
	}

}
