package me.dennislysenko.payroll.type;

public class PutAction {

	public static final PutAction EMAIL = new PutAction(0, "E-Mail");
	public static final PutAction QB = new PutAction(1, "QB");
	public static final PutAction PAID = new PutAction(2, "Paid");
	public static final PutAction TASK = new PutAction(3, "Task");

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
	
}
