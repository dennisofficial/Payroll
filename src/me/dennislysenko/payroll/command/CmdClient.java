package me.dennislysenko.payroll.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.dennislysenko.payroll.api.Table;
import me.dennislysenko.payroll.core.ThreadInput;
import me.dennislysenko.payroll.type.Client;
import me.dennislysenko.payroll.type.Command;

public class CmdClient extends Command {

	public CmdClient() {
		LABEL = "CLIENT";
		USAGE = "/client";
		DESCRIPTION = "Client Editor.";
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
		}
	}

	private void help(String[] args) {
		System.out.println("Client Manager - Help");
		System.out.println("/client help\tShows this screen.");
		System.out.println("/client add\tAdds a new client.");
	}

	private void add(String[] args) {
		ThreadInput.stopThread();
		while (true) {
			try {
				Integer id = Client.getRowNum();
				String building;
				List<String> alias = new ArrayList<String>();

				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

				System.out.print("Enter Client Name: ");
				building = reader.readLine();
				
				if (Client.exists(building)) {
					Client client = Client.getClient(building);
					System.out.println("This Client \"" + client.getLabel() + "\" already exists!");
					break;
				}

				System.out.println("Enter alias keywords: Type \"exit\" to stop!");
				while (true) {
					String input = reader.readLine();
					if (input.equalsIgnoreCase("exit")) {
						break;
					}
					alias.add(input.toUpperCase());
				}
				
				String[] h = {"ID", "Label", "Alias"};
				Table t = new Table(h);
				t.setMarginRight(1);
				t.addData(0, id.toString());
				t.addData(1, building);
				t.addData(2, alias.toString());
				t.print();
				Client.addClient(id, building, alias.toArray(new String[alias.size()]));
				break;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		ThreadInput.startThread();
	}
	
}
