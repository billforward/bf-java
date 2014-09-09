package net.billforward.model.usage;

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

public class UsagePeriod extends BillingEntity {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String sessiondID;
	@Expose protected String subscriptionID;
	@Expose protected String uom;
	@Expose protected String invoiceID;
	@Expose protected String state;
	@Expose protected int period;
	@Expose protected String start;
	@Expose protected String stop;
	@Expose protected String durationResolution;
	@Expose protected String usageDuration;
	@Expose protected long usageValue;
	@Expose protected String usageType;

	public String getID() {
		return id;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public String getSessiondID() {
		return sessiondID;
	}

	public String getSubscriptionID() {
		return subscriptionID;
	}

	public String getUom() {
		return uom;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public String getState() {
		return state;
	}

	public int getPeriod() {
		return period;
	}

	public String getStart() {
		return start;
	}

	public String getStop() {
		return stop;
	}

	public String getDurationResolutionAsString() {
		return durationResolution;
	}

	public DurationResolution getDurationResolution() {
		return DurationResolution.valueOf(durationResolution);
	}

	public String getUsageDuration() {
		return usageDuration;
	}

	public long getUsageValue() {
		return usageValue;
	}

	public String getUsageTypeAsString() {
		return usageType;
	}

	public UsageType getUsageType() {
		return UsageType.valueOf(usageType);
	}


	public static UsagePeriod[] getUsagePeriodsForSubscription(String subscriptionID_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(subscriptionID_, ResourcePath());
	}
	

	public static UsagePeriod[] getUsagePeriodsForInvoice(String subscriptionID_, String invoiceID_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		String postPath = String.format("invoice/%s", invoiceID_);
		return getByIDPostPath(subscriptionID_, postPath, ResourcePath());
	}
	
	public static UsagePeriod[] getUsagePeriodsForSubscriptionPeriod(String subscriptionID_, int period_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByIDPostPath(subscriptionID_, ((Integer)period_).toString(), ResourcePath());
	}
	
	protected static ResourcePath resourcePath;

	public UsagePeriod(BillForwardClient client_) {
		super(client_);		
	}
	
	protected UsagePeriod() {
		
	}

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("usage-periods", "usagePeriod",  new TypeToken<APIResponse<UsagePeriod>>() {}.getType());
	}
}
