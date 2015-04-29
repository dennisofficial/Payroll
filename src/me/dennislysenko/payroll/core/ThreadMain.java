package me.dennislysenko.payroll.core;



public class ThreadMain implements Runnable {

	@Override
	public void run() {
		System.out.println(Main.TITLE + " v" + Main.VERSION);
	}

}
