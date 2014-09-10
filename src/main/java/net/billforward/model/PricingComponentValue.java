package net.billforward.model;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class PricingComponentValue extends MutableEntity<PricingComponentValue> {
	@Expose protected String id;
	@Expose protected String subscriptionID;
	@Expose protected String organizationID;
	@Expose protected int value;
	@Expose protected String pricingComponentID;
	@Expose protected String appliesFrom;
	@Expose protected String appliesTill;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;
	
	public String getID() {
		return id;
	}
	
	public String getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(String subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getPricingComponentID() {
		return pricingComponentID;
	}

	public void setPricingComponentID(String pricingComponentID) {
		this.pricingComponentID = pricingComponentID;
	}

	public String getAppliesFrom() {
		return appliesFrom;
	}

	public void setAppliesFrom(String appliesFrom) {
		this.appliesFrom = appliesFrom;
	}

	public String getAppliesTill() {
		return appliesTill;
	}

	public void setAppliesTill(String appliesTill) {
		this.appliesTill = appliesTill;
	}

	public String getUpdated() {
		return updated;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public String getCreated() {
		return created;
	}

	protected static ResourcePath resourcePath;

	public PricingComponentValue(BillForwardClient client_) {
		super(client_);		
	}
	
	public PricingComponentValue() {
		
	}

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("pricing-component-values", "pricingComponentValue",  new TypeToken<APIResponse<Profile>>() {}.getType());
	}
}
