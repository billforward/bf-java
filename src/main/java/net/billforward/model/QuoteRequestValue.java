package net.billforward.model;

import com.google.gson.annotations.Expose;

public class QuoteRequestValue {
	@Expose public String pricingComponent;
	
	@Expose public int quantity;
	
	@Expose private Integer existingQuantity;
	
	public String getPricingComponentName() {
		return pricingComponent;
	}
	
	public void setPricingComponentName(String name) {
		this.pricingComponent = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Integer getExistingQuantity() {
		return existingQuantity;
	}
}
