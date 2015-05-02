package me.dennislysenko.payroll.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import me.dennislysenko.payroll.core.Main;
import me.dennislysenko.payroll.managers.ClientManager;
import me.dennislysenko.payroll.type.Client;
import me.dennislysenko.payroll.type.Data;
import me.dennislysenko.payroll.type.PutAction;

public class DataWriter {

	private static File data = new File(Main.getDataFolder(), "actions.dat");

	public static void addData(Client building, String reference, PutAction action) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(data));
			List<String> lines = new ArrayList<String>();
			String line;
			while ((line = reader.readLine()) != null) {
				if (!line.isEmpty()) {
					lines.add(line);
				}
			}
			reader.close();
			PrintWriter writer = new PrintWriter(data);
			for (String line2 : lines) {
				writer.println(line2);
			}
			writer.println(System.currentTimeMillis() + ":" + building.getId() + ":" + reference + ":" + action.getID());
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void addData(Integer paycheck) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(data));
			List<String> lines = new ArrayList<String>();
			String line;
			while ((line = reader.readLine()) != null) {
				if (!line.isEmpty()) {
					lines.add(line);
				}
			}
			reader.close();
			PrintWriter writer = new PrintWriter(data);
			for (String line2 : lines) {
				writer.println(line2);
			}
			writer.println(System.currentTimeMillis() + ":" + paycheck);
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static List<Data> getData() {
		List<Data> output = new ArrayList<Data>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(data));
			String line;
			while ((line = reader.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] vars = line.split(":");
					if (vars.length == 4) {
						output.add(new Data(new Long(vars[0]), ClientManager.getClient(new Integer(vars[1])), vars[2].replace(";", ":"), PutAction.getAction(new Integer(vars[3]))));
					}
					else {
						output.add(new Data(new Long(vars[0]), new Integer(vars[1])));
					}
				}
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return output;
	}
	
}
