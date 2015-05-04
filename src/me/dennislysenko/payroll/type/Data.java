package me.dennislysenko.payroll.type;

import java.util.Calendar;

public class Data {

	Long TIMESTAMP;
	Client CLIENT;
	String REFERENCE;
	PutAction ACTION;
	Integer AMOUNT;
	
	public Data(Long timestamp, Client building, String reference, PutAction action, Integer amount) {
		TIMESTAMP = timestamp;
		CLIENT = building;
		REFERENCE = reference;
		ACTION = action;
		AMOUNT = amount;
	}
	
	public Data(Long timestamp, Integer amount) {
		TIMESTAMP = timestamp;
		AMOUNT = amount;
		REFERENCE = "PAYCHECK";
		ACTION = PutAction.PAYCHECK;
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

	public Integer getAmount() {
		return AMOUNT;
	}
	
}
