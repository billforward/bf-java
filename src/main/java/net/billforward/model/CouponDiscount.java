package net.billforward.model;

import com.google.gson.annotations.Expose;

public class CouponDiscount {
	@Expose protected String pricingComponent;
	@Expose protected String pricingComponentName;
	@Expose protected String pricingComponentID;
	@Expose protected Integer unitsFree;
	@Expose protected Integer percentageDiscount;
	@Expose protected Integer cashDiscount;

	public CouponDiscount() {
		
	}
	
	public CouponDiscount(String pricingComponent) {
		this.pricingComponent = pricingComponent;
	}
	
	public String getPricingComponentName() {
		return pricingComponentName;
	}
	
	public void setPricingComponent(String pricingComponent) {
		this.pricingComponent = pricingComponentName;
	}
	
	public String getPricingComponentID() {
		return pricingComponentID;
	}
		
	public int getUnitsFree() {
		return unitsFree;
	}
	
	public void setUnitsFree(int unitsFree) {
		this.unitsFree = unitsFree;
	}
	
	public int getPercentageDiscount() {
		return percentageDiscount;
	}
	
	public void setPercentageDiscount(int percentOff) {
		this.percentageDiscount = percentOff;
	}
	
	public int getCashDiscount() {
		return cashDiscount;
	}
	
	public void setCashDiscount(int amountOff) {
		this.cashDiscount = amountOff;
	}	
}
