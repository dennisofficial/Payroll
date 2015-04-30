package me.dennislysenko.payroll.core;

import me.dennislysenko.payroll.engine.CommandManager;
import me.dennislysenko.payroll.type.PutAction;

public class ThreadMain implements Runnable {

	@Override
	public void run() {
		System.out.println(Main.TITLE + " v" + Main.VERSION);
		CommandManager.registerCommands();
		PutAction.setupActions();
	}

}
