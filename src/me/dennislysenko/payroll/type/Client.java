package me.dennislysenko.payroll.type;

public class Client {

	private Integer ID;
	private String LABEL;
	private String[] ALIAS;
	
	public Client(Integer id, String label, String[] alias) {
		ID = id;
		LABEL = label;
		ALIAS = alias;
	}
	
	public Integer getId() {
		return ID;
	}
	
	public String getLabel() {
		return LABEL;
	}	
	
	public String[] getAlias() {
		return ALIAS;
	}
	
}
