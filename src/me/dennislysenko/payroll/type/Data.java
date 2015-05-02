package me.dennislysenko.payroll.type;

public class Data {

	Long TIMESTAMP;
	Client CLIENT;
	String REFERENCE;
	PutAction ACTION;
	Integer PAYCHECK;
	
	public Data(Long timestamp, Client building, String reference, PutAction action) {
		TIMESTAMP = timestamp;
		CLIENT = building;
		REFERENCE = reference;
		ACTION = action;
	}
	
	public Data(Long timestamp, Integer paycheck) {
		TIMESTAMP = timestamp;
		
	}
	
	public Long getTimestamp() {
		return TIMESTAMP;
	}
	
	public Client getClient() {
		return CLIENT;
	}
	
	public String getReference() {
		return REFERENCE;
	}
	
	public PutAction getAction() {
		return ACTION;
	}
	
}
