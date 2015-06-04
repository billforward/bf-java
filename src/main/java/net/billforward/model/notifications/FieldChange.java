package net.billforward.model.notifications;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import net.billforward.model.BillingEntity;
import net.billforward.model.ResourcePath;

import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;

public class FieldChange extends BillingEntity {
	@Expose String attributeName;
	@Expose String previousValue;
	@Expose String  newValue;
	
	public String getAttributeName() {
		return attributeName;
	}
	
	public String getPreviousValue() {
		return previousValue;
	}
	
	public String getNewValue() {
		return newValue;
	}
	

	public Date getPreviousValueAsDate() {
		return deserialize(previousValue);
	}
	
	public Date getNewValueAsDate() {
		return deserialize(newValue);
	}

	static java.text.SimpleDateFormat historicFormat;
	static java.text.SimpleDateFormat dateFormat;
	
	static {
		historicFormat = new java.text.SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		historicFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	}
	
	public Date deserialize(String dateString) throws JsonParseException {
		Date date = null;
		boolean parsed = false;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
		}
		
		if(!parsed) {
			try {
				date = historicFormat.parse(dateString);
			} catch (ParseException e) {
			}		
		}
		return date;
	}

	@Override
	protected ResourcePath getResourcePath() {
		return null;
	}
}
