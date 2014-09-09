package net.billforward.model;

import java.math.BigDecimal;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class PricingComponentTier extends MutableEntity<PricingComponentTier> {
	@Expose protected String id;
	@Expose protected String consistentPricingComponentID;
	@Expose protected String organizationID;
	@Expose protected int lowerThreshold;
	@Expose protected int upperThreshold;
	@Expose protected String componentID;
	@Expose protected String pricingType;
	@Expose protected BigDecimal price;
	@Expose protected String changedBy;
	@Expose protected String created;
	
	public int getLowerThreshold() {
		return lowerThreshold;
	}

	public void setLowerThreshold(int lowerThreshold) {
		this.lowerThreshold = lowerThreshold;
	}

	public int getUpperThreshold() {
		return upperThreshold;
	}

	public void setUpperThreshold(int upperThreshold) {
		this.upperThreshold = upperThreshold;
	}

	public PricingComponentTierType getPricingType() {
		return PricingComponentTierType.valueOf(pricingType);
	}
	
	public String getPricingTypeAsString() {
		return pricingType.toString();
	}

	public void setPricingType(PricingComponentTierType pricingType) {
		this.pricingType = pricingType.toString();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public String getCreated() {
		return created;
	}

	protected static ResourcePath resourcePath;
	
	public PricingComponentTier(BillForwardClient client_) {
		super(client_);		
	}
	
	protected PricingComponentTier() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("pricing-component-tiers", "pricing-component-tier",  new TypeToken<APIResponse<PricingComponentTier>>() {}.getType());
	}
	
	public enum PricingComponentTierType {
		unit,
		fixed
	}
}
