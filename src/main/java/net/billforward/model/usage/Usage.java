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

public class Usage extends BillingEntity {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String sessiondID;
	@Expose protected String subscriptionID;
	@Expose protected String uom;
	@Expose protected int usagePeriod;
	@Expose protected long usageDuration;
	@Expose protected String usageType;
	@Expose protected long usageValue;
	@Expose protected String start;
	@Expose protected String stop;
	@Expose protected String updated;

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

	public int getUsagePeriod() {
		return usagePeriod;
	}

	public long getUsageDuration() {
		return usageDuration;
	}

	public UsageType getUsageType() {
		return UsageType.valueOf(usageType);
	}
	
	public String getUsageTypeAsString() {
		return usageType;
	}

	public long getUsageValue() {
		return usageValue;
	}

	public String getStart() {
		return start;
	}

	public String getStop() {
		return stop;
	}

	public String getUpdated() {
		return updated;
	}
	
	public static Usage[] getUsageForSubscription(String subscriptionID_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(subscriptionID_, ResourcePath());
	}
	
	public static Usage[] getUsageForSubscriptionPeriod(String subscriptionID_, int period_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByIDPostPath(subscriptionID_, ((Integer)period_).toString(), ResourcePath());
	}

	protected static ResourcePath resourcePath;

	public Usage(BillForwardClient client_) {
		super(client_);		
	}
	
	protected Usage() {
		
	}

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("usage", "usage",  new TypeToken<APIResponse<Usage>>() {}.getType());
	}
}
