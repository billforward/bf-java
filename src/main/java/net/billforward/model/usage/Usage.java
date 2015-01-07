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
import net.billforward.model.InsertableEntity;
import net.billforward.model.ResourcePath;

public class Usage extends InsertableEntity<Usage>  {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String sessiondID;
	@Expose protected String subscriptionID;
	@Expose protected String pricingComponentID;
	@Expose protected String pricingComponentName;
	@Expose protected String uom;
	@Expose protected int usagePeriod;
	@Expose protected long usageDuration;
	@Expose protected String usageType;
	@Expose protected long usageValue;
	@Expose protected Date start;
	@Expose protected Date stop;
	@Expose protected Date updated;

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

	public String getPricingComponentID() {
		return pricingComponentID;
	}

	public String getPricingComponentName() {
		return pricingComponentName;
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

	public Date getStart() {
		return start;
	}

	public Date getStop() {
		return stop;
	}

	public Date getUpdated() {
		return updated;
	}	
	
	public void setSessiondID(String sessiondID) {
		this.sessiondID = sessiondID;
	}

	public void setSubscriptionID(String subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public void setUsagePeriod(int usagePeriod) {
		this.usagePeriod = usagePeriod;
	}

	public void setUsageDuration(long usageDuration) {
		this.usageDuration = usageDuration;
	}

	public void setUsageType(UsageType usageType) {
		this.usageType = usageType.toString();
	}

	public void setUsageValue(long usageValue) {
		this.usageValue = usageValue;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setStop(Date stop) {
		this.stop = stop;
	}

	public static Usage create(Usage usage) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(usage, ResourcePath())[0];
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
	
	public Usage() {
		
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
