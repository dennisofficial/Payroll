package me.dennislysenko.payroll.core;

import me.dennislysenko.payroll.engine.CommandManager;

public class ThreadMain implements Runnable {

	@Override
	public void run() {
		System.out.println(Main.TITLE + " v" + Main.VERSION);
		CommandManager.registerCommands();
	}

}
