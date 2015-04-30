package me.dennislysenko.payroll.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.dennislysenko.payroll.managers.ClientManager;
import me.dennislysenko.payroll.managers.CommandManager;
import me.dennislysenko.payroll.type.PutAction;

public class ThreadMain implements Runnable {

	@Override
	public void run() {
		setupFiles();
		System.out.println(Main.TITLE + " v" + Main.VERSION);
		CommandManager.registerCommands();
		PutAction.setupActions();
		ClientManager.loadClients();
	}
	
	private void setupFiles() {
		// Make sure to go in order!
		List<File> folders = new ArrayList<File>();
		folders.add(Main.getDataFolder());
		for (File folder : folders) {
			if (!folder.exists()) {
				folder.mkdir();
			}
		}
		
		List<File> files = new ArrayList<File>();
		files.add(new File(Main.getDataFolder(), "clients.dat"));
		files.add(new File(Main.getDataFolder(), "actions.dat"));
		for (File file : files) {
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
