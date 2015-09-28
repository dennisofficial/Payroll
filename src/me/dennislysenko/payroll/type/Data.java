package me.dennislysenko.payroll.type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Data {

	Long TIMESTAMP;
	Client CLIENT;
	String REFERENCE;
	PutAction ACTION;
	Integer AMOUNT;

	private static File data = new File("actions.dat");

	public static void addData(Client client, Integer amount, String reference, PutAction action) {
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
			writer.println(System.currentTimeMillis() + ":" + amount + ":" + client.getId() + ":" + reference + ":" + action.getID());
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
						output.add(new Data(new Long(vars[0]), // Time Stamp
								new Integer(vars[1]), // Amount
								Client.getClient(new Integer(vars[2])), // Client
								vars[3].replace(";", ":"), // Description
								PutAction.getAction(new Integer(vars[4])))); // Action
					}
					else {
						output.add(new Data(new Long(vars[0]), // Time Stamp
								new Integer(vars[1]))); // Amount
					}
				}
			}
			reader.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return output;
	}
	
	public Data(Long timestamp, Integer amount, Client client, String reference, PutAction action) {
		TIMESTAMP = timestamp;
		CLIENT = client;
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
