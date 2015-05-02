package me.dennislysenko.payroll.type;

import java.util.HashMap;

public class PutAction {

	private static HashMap<Integer, PutAction> actions = new HashMap<Integer, PutAction>();
	
	public static final PutAction EMAIL = new PutAction(0, "E-Mail");
	public static final PutAction QB = new PutAction(1, "QB");
	public static final PutAction PAID = new PutAction(2, "Paid");
	public static final PutAction TASK = new PutAction(3, "Task");
	public static final PutAction PAYCHECK = new PutAction(4, "PayCheck");

	public static void setupActions() {
		actions.put(0, EMAIL);
		actions.put(1, QB);
		actions.put(2, PAID);
		actions.put(3, TASK);
		actions.put(4, PAYCHECK);
	}

	public Integer ID;
	public String LABEL;
	
	public PutAction(Integer id, String label) {
		ID = id;
		LABEL = label;
	}
	
	public String getLabel() {
		return LABEL;
	}
	
	public Integer getID() {
		return ID;
	}
	
	public static PutAction getAction(Integer id) {
		return actions.get(id);
	}
	
	public static PutAction getAction(String action) {
		PutAction output = null;
		for (PutAction action1 : actions.values()) {
			if (action1.getLabel().equalsIgnoreCase(action)) {
				output = action1;
			}
		}
		return output;
	}
	
}
