package me.dennislysenko.payroll.type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map.Entry;

public class Config {

	private static final File file = new File("settings.cfg");
	private static final HashMap<String, Object> settings = new HashMap<String, Object>();
	
	public static final Key KEY_QB_RATE = new Key("qb-rate");
	public static final Key KEY_EMAIL_RATE = new Key("email-rate");
	public static final Key KEY_PAID_RATE = new Key("paid-rate");
	
	public static void loadConfig() throws Exception {
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {
			if (!line.isEmpty()) {
				String[] var = line.split(":");
				settings.put(var[0], var[1]);
			}
		}
		reader.close();
	}
	
	public static Integer getInt(Key key) {
		for (Entry<String, Object> entry : settings.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(key.toString())) {
				if (entry.getValue() instanceof Integer) {
					return (Integer) entry.getValue();
				}
				else {
					System.out.println("There has been an error with settings.");
					System.out.println("getInt(Key) cannot get value from: " + key.toString());
				}
			}
		}
		return null;
	}
	
	private static class Key {
		
		private String node;
		
		public Key(String node) {
			this.node = node;
		}
		
		public String toString() {
			return node;
		}
		
	}
	
}
