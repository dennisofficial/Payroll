package me.dennislysenko.payroll.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.dennislysenko.payroll.core.Main;
import me.dennislysenko.payroll.type.Client;

public class ClientManager {

	private static List<Client> clients = new ArrayList<Client>();

	public static void loadClients() {
		try {
			File file = new File(Main.getDataFolder(), "clients.dat");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] vars = line.split("|");
					
					Integer id = new Integer(vars[0]);
					String label = vars[1];
					String[] alias = vars[2].replace("[", "").replace("]", "").split(",");
					
					clients.add(new Client(id, label, alias));
				}
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void addClient(String label, String[] alias) {
		
	}

	public static void checkFor(String client) {

	}

	public static Client getClient(String client) {
		return null;
	}

}
