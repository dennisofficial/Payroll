package me.dennislysenko.payroll.api;

import java.util.Date;

@SuppressWarnings("deprecation")
public class TimeStamp {

	public Date date;
	
	public TimeStamp(Long timestamp) {
		date = new Date(timestamp);
	}
	
	public static Long getTimeStamp() {
		return System.currentTimeMillis();
	}
	
	public Integer getMonth() {
		return date.getMonth() + 1;
	}
	
	public Integer getDay() {
		return date.getDay();
	}
	
	public Integer getYear() {
		return date.getYear();
	}
	
	public String getMonth(Boolean abreviate) {
		String output = null;
		if (getMonth() == 1) { if (abreviate) { output = "Jan"; } else { output = "January"; } }
		else if (getMonth() == 2) { if (abreviate) { output = "Feb"; } else { output = "Feburary"; } }
		else if (getMonth() == 3) { if (abreviate) { output = "Mar"; } else { output = "March"; } }
		else if (getMonth() == 4) { if (abreviate) { output = "Apr"; } else { output = "April"; } }
		else if (getMonth() == 5) { if (abreviate) { output = "May"; } else { output = "May"; } }
		else if (getMonth() == 6) { if (abreviate) { output = "June"; } else { output = "June"; } }
		else if (getMonth() == 7) { if (abreviate) { output = "July"; } else { output = "July"; } }
		else if (getMonth() == 8) { if (abreviate) { output = "Aug"; } else { output = "August"; } }
		else if (getMonth() == 9) { if (abreviate) { output = "Sep"; } else { output = "September"; } }
		else if (getMonth() == 10) { if (abreviate) { output = "Oct"; } else { output = "October"; } }
		else if (getMonth() == 11) { if (abreviate) { output = "Nov"; } else { output = "November"; } }
		else if (getMonth() == 12) { if (abreviate) { output = "Dec"; } else { output = "December"; } }
		return output;
	}
	
}

