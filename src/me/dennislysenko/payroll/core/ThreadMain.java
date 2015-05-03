package me.dennislysenko.payroll.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.dennislysenko.payroll.api.DataWriter;
import me.dennislysenko.payroll.api.Table;
import me.dennislysenko.payroll.managers.ClientManager;
import me.dennislysenko.payroll.managers.CommandManager;
import me.dennislysenko.payroll.type.Data;
import me.dennislysenko.payroll.type.PutAction;

public class ThreadMain implements Runnable {

	@Override
	public void run() {
		setupFiles();
		System.out.println(Main.TITLE + " v" + Main.VERSION);
		
		CommandManager.registerCommands();
		PutAction.setupActions();
		ClientManager.loadClients();
		
		printMOTD();
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
	
	private void printMOTD() {
		Integer paycheck = 0;
		Integer monthaction = 0;
		
		List<Data> datas = DataWriter.getData();
		for (Data data : datas) {
			if (data.getAction().equals(PutAction.PAYCHECK)) {
				paycheck -= data.getPayCheck();
			}
			else {
				paycheck += 3;
				
				// 1 variable are Current, and 2 variable are Data time stamp.
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				cal2.setTimeInMillis(System.currentTimeMillis());
				
				Integer year1 = cal1.get(Calendar.YEAR);
				Integer year2 = cal2.get(Calendar.YEAR);
				Integer month1 = cal1.get(Calendar.MONTH);
				Integer month2 = cal2.get(Calendar.MONTH);

				if (year1.toString().equals(year2.toString())) {
					if (month1 == month2) {
						monthaction++;
					}
				}
			}
		}
		
		Calendar cal = Calendar.getInstance();
		Integer month = cal.get(Calendar.MONTH) + 1;
		Integer day = cal.get(Calendar.DAY_OF_MONTH);
		
		String[] h = {"Date", "Pay Check", "Month Actions"};
		Table t = new Table(h);
		t.setMarginRight(1);
		t.addData(0, month + "/" + day);
		t.addData(1, "$" + paycheck);
		t.addData(2, monthaction.toString());
		t.print();
	}

}
