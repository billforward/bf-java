package net.billforward.model;

import java.util.Date;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class PricingComponentValue extends MutableEntity<PricingComponentValue> {
	@Expose protected String id;
	@Expose protected String subscriptionID;
	@Expose protected String organizationID;
	@Expose protected int value;
	@Expose protected String pricingComponentID;
	@Expose protected Date appliesFrom;
	@Expose protected Date appliesTill;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;
	
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

	public Date getAppliesFrom() {
		return appliesFrom;
	}

	public void setAppliesFrom(Date appliesFrom) {
		this.appliesFrom = appliesFrom;
	}

	public Date getAppliesTill() {
		return appliesTill;
	}

	public void setAppliesTill(Date appliesTill) {
		this.appliesTill = appliesTill;
	}

	public Date getUpdated() {
		return updated;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public Date getCreated() {
		return created;
	}

	protected static ResourcePath resourcePath;


	public static PricingComponentValue create(PricingComponentValue pricingComponentValue) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(pricingComponentValue, ResourcePath())[0];
	}		

	
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
		resourcePath = new ResourcePath("pricing-component-values", "pricingComponentValue",  new TypeToken<APIResponse<PricingComponentValue>>() {}.getType());
	}
}
