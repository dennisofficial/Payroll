package me.dennislysenko.payroll.type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import me.dennislysenko.payroll.core.Main;

public class Data {

	Long TIMESTAMP;
	Client CLIENT;
	String REFERENCE;
	PutAction ACTION;
	Integer AMOUNT;

	private static File data = new File(Main.getDataFolder(), "actions.dat");

	public static void addData(Client building, String reference, PutAction action, Integer amount) {
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
					if (vars.length == 5) {
						output.add(new Data(new Long(vars[0]), Client.getClient(new Integer(vars[1])), vars[2].replace(";", ":"), PutAction.getAction(new Integer(vars[3])), new Integer(vars[4])));
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
	
	public Data(Long timestamp, Client building, String reference, PutAction action, Integer amount) {
		TIMESTAMP = timestamp;
		CLIENT = building;
		REFERENCE = reference;
		ACTION = action;
		AMOUNT = amount;
	}
	
	public Data(Long timestamp, Integer amount) {
		TIMESTAMP = timestamp;
		AMOUNT = amount;
		REFERENCE = "PAYCHECK";
		ACTION = PutAction.PAYCHECK;
	}
	
	public Long getTimestamp() {
		return TIMESTAMP;
	}
	
	public Client getClient() {
		return CLIENT;
	}
	
	public String getReference() {
		return REFERENCE;
	}
	
	public PutAction getAction() {
		return ACTION;
	}

	public Integer getAmount() {
		return AMOUNT;
	}
	
}
