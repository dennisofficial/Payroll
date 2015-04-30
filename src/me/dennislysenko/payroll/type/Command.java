package me.dennislysenko.payroll.type;

public abstract class Command {

	public String LABEL;
	public String USAGE;
	public String DESCRIPTION;
	
	public abstract void execute(String[] args);

	public String getLabel() {
		return LABEL;
	}
	
	public String getUsage() {
		return USAGE;
	}
	
	public String getDescription() {
		return DESCRIPTION;
	}
	
}
