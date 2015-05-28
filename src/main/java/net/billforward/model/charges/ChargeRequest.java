package net.billforward.model.charges;

import java.math.BigDecimal;

import net.billforward.model.BillingEntity;
import net.billforward.model.InvoicingType;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;

public class ChargeRequest extends BillingEntity {
	@Expose private String id;
	
	@Expose private String description;

	@Expose private BigDecimal amount;
	
	private Boolean taxAmount;
	
	@Expose private InvoicingType invoicingType;

	@Expose private ChargeType chargeType;
	
	@Expose private Boolean trial;
	
	@Expose private String pricingComponentName;
	
	@Expose private String pricingComponentID;
	
	@Expose private Integer pricingComponentValue;

	public String getID() {
		return id;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Boolean getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Boolean taxAmount) {
		this.taxAmount = taxAmount;
	}

	public InvoicingType getInvoicingType() {
		return invoicingType;
	}

	public void setInvoicingType(InvoicingType invoicingType) {
		this.invoicingType = invoicingType;
	}

	public ChargeType getChargeType() {
		return chargeType;
	}

	public void setChargeType(ChargeType chargeType) {
		this.chargeType = chargeType;
	}

	public Boolean getTrial() {
		return trial;
	}

	public void setTrial(Boolean trial) {
		this.trial = trial;
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

	public Integer getPricingComponentValue() {
		return pricingComponentValue;
	}

	public void setPricingComponentValue(Integer pricingComponentValue) {
		this.pricingComponentValue = pricingComponentValue;
	}

	@Override
	protected ResourcePath getResourcePath() {
		// TODO Auto-generated method stub
		return null;
	}
}
