package net.billforward.model.usage;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.APIResponse;
import net.billforward.model.BillingEntity;
import net.billforward.model.ResourcePath;

public class Period extends BillingEntity {
	@Expose protected String subscriptionID;
	@Expose protected String organizationID;
	@Expose protected Date start;
	@Expose protected Date stop;
	@Expose protected int period;
	@Expose protected String usageType;
	@Expose protected String state;
	@Expose protected Date created;
	
	public String getSubscriptionID() {
		return subscriptionID;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public Date getStart() {
		return start;
	}

	public Date getStop() {
		return stop;
	}

	public int getPeriod() {
		return period;
	}

	public String getUsageTypeAsString() {
		return usageType;
	}
	
	public UsageType getUsageType() {
		return UsageType.valueOf(usageType);
	}

	public String getStateAsString() {
		return state;
	}

	public UsageState getState() {
		return UsageState.valueOf(state);
	}

	public Date getCreated() {
		return created;
	}

	public static Period[] getPeriodsForSubscription(String subscriptionID_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(subscriptionID_, ResourcePath());
	}

	public static Period getLatestPeriodsForSubscription(String subscriptionID_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Period[] periods = getByID(subscriptionID_, ResourcePath());
		if(periods == null || periods.length == 0) return null;
		
		Period latestPeriod = null;
		for(Period period : periods) {
			if(latestPeriod == null) {
				latestPeriod = period;
			}
			
			if(period.period > latestPeriod.period) {
				latestPeriod = period;
			}
		}
		
		return latestPeriod;
	}

	public static Period[] getAllPeriods() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}
	
	protected static ResourcePath resourcePath;

	public Period(BillForwardClient client_) {
		super(client_);		
	}
	
	protected Period() {
		
	}

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("periods", "period",  new TypeToken<APIResponse<Period>>() {}.getType());
	}
}
