package me.dennislysenko.payroll.core;

public class Main {

	public static final Integer FPS = 20;
	public static final String TITLE = "PayRoll";
	public static final String VERSION = "1.0.0";
	
	public static void main(String[] args) {
		new Thread(new ThreadMain()).start();
	}

}
