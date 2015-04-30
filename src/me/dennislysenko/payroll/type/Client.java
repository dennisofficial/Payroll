package me.dennislysenko.payroll.type;

public class Client {

	private Integer ID;
	private String LABEL;
	private String[] ALIAS;
	
	public Integer getId() {
		return ID;
	}
	
	public void setId(Integer id) {
		ID = id;
	}
	
	public String getLabel() {
		return LABEL;
	}	
	
	public void setLabel(String label) {
		LABEL = label;
	}
	
	public String[] getAlias() {
		return ALIAS;
	}
	
	public void setAlias(String[] alias) {
		ALIAS = alias;
	}
	
}
