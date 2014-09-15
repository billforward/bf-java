package net.billforward.model.usage;

import java.util.Date;

import net.billforward.BillForwardClient;
import net.billforward.model.APIResponse;
import net.billforward.model.BillingEntity;
import net.billforward.model.PricingComponent;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class UsageRoundingStrategy extends BillingEntity {
	@Expose protected String id;
	@Expose protected String thresholdComponentID;
	@Expose protected String organizationID;
	@Expose protected String aggregationLevel;
	@Expose protected String uom;
	@Expose protected String roundingLevel;
	@Expose protected String threshold;
	@Expose protected String thresholdValue;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;
	
	protected PricingComponent thresholdComponent;

	public String getID() {
		return id;
	}
	
	public String getThresholdComponentID() {
		return thresholdComponentID;
	}

	public void setThresholdComponentID(String thresholdComponentID) {
		this.thresholdComponentID = thresholdComponentID;
	}

	public String getAggregationLevel() {
		return aggregationLevel;
	}

	public void setAggregationLevel(String aggregationLevel) {
		this.aggregationLevel = aggregationLevel;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getRoundingLevel() {
		return roundingLevel;
	}

	public void setRoundingLevel(String roundingLevel) {
		this.roundingLevel = roundingLevel;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getThresholdValue() {
		return thresholdValue;
	}

	public void setThresholdValue(String thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	public PricingComponent getThresholdComponent() {
		return thresholdComponent;
	}

	public void setThresholdComponent(PricingComponent thresholdComponent) {
		this.thresholdComponent = thresholdComponent;
	}

	public String getOrganizationID() {
		return organizationID;
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

	public UsageRoundingStrategy(BillForwardClient client_) {
		super(client_);		
	}
	
	protected UsageRoundingStrategy() {
		
	}

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("usage-sessions", "usageSession",  new TypeToken<APIResponse<Period>>() {}.getType());
	}
	
	public enum AggregationLevel {
		Seconds,
		Minutes,
		Hours,
		Days
	}

	public enum Threshold {
		None,
		Fixed,
		Variable
	}
}
