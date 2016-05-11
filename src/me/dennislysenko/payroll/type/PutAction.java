package me.dennislysenko.payroll.type;

public enum PutAction {

	EMAIL("E-Mail", 2),
	QB("QB", 2),
	PAID("Paid", 1),
	TASK("Task", 0),
	PAYCHECK("PayCheck", 0),
	TAXES("Taxes", 5);
	
	public Integer ID;
	public String LABEL;
	public Integer VALUE;
	
	PutAction(String label, Integer value) {
		ID = this.ordinal();
		LABEL = label;
		VALUE = value;
	}
	
	public static PutAction getAction(Integer id) {
		return values()[id];
	}
	
	public static PutAction getAction(String action) {
		PutAction output = null;
		for (PutAction action1 : values()) {
			if (action1.LABEL.equalsIgnoreCase(action)) {
				output = action1;
			}
		}
		return output;
	}
	
}
