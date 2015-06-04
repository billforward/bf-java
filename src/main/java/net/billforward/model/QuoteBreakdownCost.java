package net.billforward.model;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

import net.billforward.model.PricingComponent.PricingComponentChargeType;

public class QuoteBreakdownCost {

	@Expose protected String pricingComponentName;
	
	@Expose protected String pricingComponentID;
	
	@Expose protected int quantity;

	@Expose protected Integer existingQuantity;
	
	@Expose protected BigDecimal cost;
	
	@Expose protected BigDecimal tax;

	@Expose protected BigDecimal discount;
	
	@Expose protected PricingComponentChargeType pricingComponentType;

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
	public PricingComponentChargeType getPricingComponentType() {
		return pricingComponentType;
	}

	public void setPricingComponentType(PricingComponentChargeType type) {
		this.pricingComponentType = type;
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

	public void setExistingQuantity(Integer existingQuantity) {
		this.existingQuantity = existingQuantity;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}
