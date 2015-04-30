package me.dennislysenko.payroll.core;

import java.io.File;

public class Main {

	public static final Integer FPS = 20;
	public static final String TITLE = "PayRoll";
	public static final String VERSION = "1.0.0";
	
	public static void main(String[] args) {
		new Thread(new ThreadMain()).start();
		new Thread(new ThreadInput()).start();
	}

	public static File getDataFolder() {
		return new File("PayRoll/");
	}

}
