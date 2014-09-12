package net.billforward.model;

import java.math.BigDecimal;
import java.util.Date;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class Invoice extends BillingEntity {
	@Expose protected String id;
	@Expose protected String subscriptionID;
	@Expose protected String accountID;
	@Expose protected String organizationID;
	@Expose protected String state;
	@Expose protected Date periodStart;
	@Expose protected Date periodEnd;
	@Expose protected Boolean deleted = false;
	@Expose protected int totalExecutionAttempts;
	@Expose protected Date lastExecutionAttempt;
	@Expose protected Date nextExecutionAttempt;
	@Expose protected Date finalExecutionAttempt;
	@Expose protected Date paymentReceived;
	@Expose protected String currency;
	@Expose protected BigDecimal costExcludingTax;
	@Expose protected BigDecimal invoiceCost;
	@Expose protected BigDecimal invoicePaid;
	@Expose protected BigDecimal discountAmount;
	@Expose protected BigDecimal invoiceRefunded;
	@Expose protected String type;
	@Expose protected String locked;
	@Expose protected String managedBy;
	@Expose protected Boolean initialInvoice;
	@Expose protected int versionNumber;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;	
	
	protected InvoiceLine[] invoiceLines;
	protected InvoicePayment[] invoicePayments;
	
	public String getID() {
		return id;
	}

	public String getSubscriptionID() {
		return subscriptionID;
	}

	public String getAccountID() {
		return accountID;
	}

	public String getOrganizationID() {
		return organizationID;
	}
	
	public String getStateAsString() {
		return state;
	}
	
	public InvoiceState getState() {
		return InvoiceState.valueOf(state);
	}

	public Date getPeriodStart() {
		return periodStart;
	}

	public Date getPeriodEnd() {
		return periodEnd;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public int getTotalExecutionAttempts() {
		return totalExecutionAttempts;
	}

	public Date getLastExecutionAttempt() {
		return lastExecutionAttempt;
	}

	public Date getNextExecutionAttempt() {
		return nextExecutionAttempt;
	}

	public Date getFinalExecutionAttempt() {
		return finalExecutionAttempt;
	}

	public Date getPaymentReceived() {
		return paymentReceived;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getCostExcludingTax() {
		return costExcludingTax;
	}

	public BigDecimal getInvoiceCost() {
		return invoiceCost;
	}

	public BigDecimal getInvoicePaid() {
		return invoicePaid;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public BigDecimal getInvoiceRefunded() {
		return invoiceRefunded;
	}

	public String getTypeAsString() {
		return type;
	}
	
	public InvoiceType getType() {
		return InvoiceType.valueOf(type);
	}	
	
	public String getLocked() {
		return locked;
	}

	public String getManagedBy() {
		return managedBy;
	}

	public Boolean getInitialInvoice() {
		return initialInvoice;
	}

	public int getVersionNumber() {
		return versionNumber;
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

	public InvoiceLine[] getInvoiceLines() {
		return invoiceLines;
	}

	public InvoicePayment[] getInvoicePayments() {
		return invoicePayments;
	}
	
	public static Invoice getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Invoice[] invoices = getByID(ID, ResourcePath());
		return invoices[0];
	}
	
	public static Invoice[] getBySubscriptionID(String subscriptionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(subscriptionID, "subscription", ResourcePath());
	}
	
	public static Invoice[] getByState(InvoiceState invoiceState_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(invoiceState_.name(), "state", ResourcePath());
	}
	
	protected static ResourcePath resourcePath;

	protected Invoice(BillForwardClient client_) {
		super(client_);		
	}
	
	protected Invoice() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("invoices", "invoice",  new TypeToken<APIResponse<Invoice>>() {}.getType());
	}
	
	public enum InvoiceState {
		Paid,
		Unpaid,
		Pending,
		Voided
	}
	
	public enum InvoiceType {
		Subscription,
		Amendment,
		Adhoc,
		Trial,
		FinalArrears
	}
}
