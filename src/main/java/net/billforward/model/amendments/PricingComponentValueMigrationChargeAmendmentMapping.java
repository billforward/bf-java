package net.billforward.model.amendments;

import com.google.gson.annotations.Expose;

public class PricingComponentValueMigrationChargeAmendmentMapping {
	@Expose int value;
	@Expose String pricingComponentID;
	
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
}
