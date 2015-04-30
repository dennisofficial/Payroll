package me.dennislysenko.payroll.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import me.dennislysenko.payroll.managers.CommandManager;

public class ThreadInput implements Runnable {

	private static Boolean running = true;
	
	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (running) {
			try {
				String line = reader.readLine();
				if (line.startsWith("/")) {
					line = line.substring(1);
				}
				CommandManager.execute(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void startThread() {
		running = true;
	}
	
	public static void stopThread() {
		running = false;
	}

}
