package net.billforward.model;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

public class QuoteBreakdownDiscount {
	@Expose protected String couponCode;
	
	@Expose protected BigDecimal discount;
	
	@Expose protected String calculation;
	
    @Expose protected String pricingComponentName;
    
    @Expose protected String pricingComponentID;

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getCalculation() {
		return calculation;
	}

	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}

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
}
