package net.billforward.model;

import com.google.gson.annotations.Expose;

public class CouponDiscount {
	@Expose protected String pricingComponentName;
	@Expose protected String pricingComponentID;
	@Expose protected String unitOfMeasureName;
	@Expose protected String unitOfMeasureID;
	@Expose protected Integer unitsFree;
	@Expose protected Integer percentageDiscount;
	@Expose protected Integer cashDiscount;
	
	public String getPricingComponentName() {
		return pricingComponentName;
	}
	
	public void setPricingComponentName(String pricingComponentName) {
		this.pricingComponentName = pricingComponentName;
	}
	
	public String getPricingComponentID() {
		return pricingComponentID;
	}
	
	public void setPricingComponentID(String pricingComponentID) {
		this.pricingComponentID = pricingComponentID;
	}
	
	public String getUnitOfMeasureName() {
		return unitOfMeasureName;
	}
	
	public void setUnitOfMeasureName(String unitOfMeasureName) {
		this.unitOfMeasureName = unitOfMeasureName;
	}
	
	public String getUnitOfMeasureID() {
		return unitOfMeasureID;
	}
	
	public void setUnitOfMeasureID(String unitOfMeasureID) {
		this.unitOfMeasureID = unitOfMeasureID;
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
