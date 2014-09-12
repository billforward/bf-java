package net.billforward.model;

import java.math.BigDecimal;
import java.util.Date;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class InvoicePayment extends BillingEntity {
	@Expose protected String id;
	@Expose protected String paymentID;
	@Expose protected String invoiceID;
	@Expose protected String organizationID;
	@Expose protected String currency;
	@Expose protected BigDecimal nominalAmount;
	@Expose protected BigDecimal actualAmount;
	@Expose protected BigDecimal refundedAmount;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;	
	
	public String getID() {
		return id;
	}

	public String getPaymentID() {
		return paymentID;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getNominalAmount() {
		return nominalAmount;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public BigDecimal getRefundedAmount() {
		return refundedAmount;
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

	public InvoicePayment(BillForwardClient client_) {
		super(client_);		
	}
	
	protected InvoicePayment() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("none", "none",  new TypeToken<APIResponse<InvoicePayment>>() {}.getType());
	}
}
