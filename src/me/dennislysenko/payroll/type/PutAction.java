package me.dennislysenko.payroll.type;

import java.util.HashMap;

public enum PutAction {

	EMAIL("E-Mail"),
	QB("QB"),
	PAID("Paid"),
	TASK("Task"),
	PAYCHECK("PayCheck"),
	TAXES("Taxes");
	
	private static HashMap<Integer, PutAction> actions = new HashMap<Integer, PutAction>();
	
	public static void setupActions() {
		actions.put(0, EMAIL);
		actions.put(1, QB);
		actions.put(2, PAID);
		actions.put(3, TASK);
		actions.put(4, PAYCHECK);
	}

	public Integer ID;
	public String LABEL;
	
	PutAction(String label) {
		ID = this.ordinal();
		LABEL = label;
	}
	
	public static PutAction getAction(Integer id) {
		return actions.get(id);
	}
	
	public static PutAction getAction(String action) {
		PutAction output = null;
		for (PutAction action1 : actions.values()) {
			if (action1.LABEL.equalsIgnoreCase(action)) {
				output = action1;
			}
		}
		return output;
	}
	
}
