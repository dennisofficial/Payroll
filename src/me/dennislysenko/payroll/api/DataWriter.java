package me.dennislysenko.payroll.api;

import java.io.File;

import me.dennislysenko.payroll.core.Main;
import me.dennislysenko.payroll.type.Client;
import me.dennislysenko.payroll.type.PutAction;

public class DataWriter {

	private static File data = new File(Main.getDataFolder(), "actions.dat");
	
	public static void addData(Client building, PutAction action, String reference) {
		
	}
	
}
