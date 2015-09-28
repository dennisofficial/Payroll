package me.dennislysenko.payroll.core;

import me.dennislysenko.payroll.command.CmdClear;

public class Main {

	public static final Integer FPS = 20;
	public static final String TITLE = "PayRoll";
	public static final String VERSION = "1.1.0";
	
	public static void main(String[] args) {
		new CmdClear().execute(args);
		new Thread(new ThreadMain()).start();
		new Thread(new ThreadInput()).start();
	}

}
