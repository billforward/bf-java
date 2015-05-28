package net.billforward.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.PricingComponent.PricingComponentChargeType;
import net.billforward.model.amendments.InvoiceNextExecutionAttemptAmendment;
import net.billforward.model.amendments.InvoiceRecalculationAmendment;
import net.billforward.model.amendments.IssueInvoiceAmendment;
import net.billforward.model.charges.Charge;
import net.billforward.model.charges.ChargeRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class Invoice extends BillingEntity {
	@Expose protected String id;
	@Expose protected String versionID;
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
	
	public String getVersionID() {
		return versionID;
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

	public InvoiceRecalculationAmendment recalculate() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return this.recalculate(this.getState());
	}
	
	public InvoiceRecalculationAmendment recalculate(InvoiceState newInvoiceState) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		InvoiceRecalculationAmendment amendment = new InvoiceRecalculationAmendment();
		amendment.setSubscriptionID(this.subscriptionID);
		amendment.setInvoiceID(this.id);
		amendment.setNewInvoiceState(newInvoiceState);
		amendment = InvoiceRecalculationAmendment.create(amendment);
		return amendment;
	}

	public InvoiceNextExecutionAttemptAmendment retryTakingPayment() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return retryTakingPayment(null);
	}
	
	public InvoiceNextExecutionAttemptAmendment retryTakingPayment(Date dateToExecute_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		InvoiceNextExecutionAttemptAmendment amendment = new InvoiceNextExecutionAttemptAmendment();
		amendment.setSubscriptionID(this.subscriptionID);
		amendment.setInvoiceID(this.id);
		
		if(dateToExecute_ != null) {
			amendment.setActioningTime(dateToExecute_);			
		}
		
		amendment = InvoiceNextExecutionAttemptAmendment.create(amendment);
		return amendment;
	}
	
	public IssueInvoiceAmendment issue() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {		
		IssueInvoiceAmendment amendment = new IssueInvoiceAmendment();
		amendment.setSubscriptionID(this.subscriptionID);
		amendment.setInvoiceID(this.id);
		amendment = IssueInvoiceAmendment.create(amendment);
		return amendment ;
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

	public void setUsage(int usage) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Subscription subscription = Subscription.getByID(this.subscriptionID);
		RatePlan ratePlan = subscription.getProductRatePlan();
		
		List<PricingComponentValue> newPricingComponentValues = new ArrayList<PricingComponentValue>();
		
		for(PricingComponent pricingComponent: ratePlan.getPricingComponents()) {
			if(pricingComponent.getChargeType() == PricingComponentChargeType.usage) {

				PricingComponentValue value = new PricingComponentValue();
				value.setSubscriptionID(this.subscriptionID);
				value.setPricingComponentID(pricingComponent.getID());
				value.setValue(usage);
				value.setAppliesFrom(this.getPeriodStart());
				value.setAppliesTill(this.getPeriodStart());
				newPricingComponentValues.add(value);
			}
		}
		
		for(PricingComponentValue value : newPricingComponentValues) {
			
			boolean update = false;
			for(PricingComponentValue currentValue : subscription.getPricingComponentValues()) {
				if(currentValue.getAppliesFrom().equals(value.getAppliesFrom()) && 
				   currentValue.getAppliesTill().equals(value.getAppliesTill())) {
					update = true;
					value = currentValue;
					break;
				}
			}
			
			if(update) {
				value.save();
			} else {
				PricingComponentValue.create(value);			
			}
		}
	}
	
	public Charge addCharge(BigDecimal value, String description) throws APIException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException {
		ChargeRequest request = new ChargeRequest();
		request.setAmount(value);
		request.setDescription(description);
		
		return addCharge(request);
	}

	public Charge addCharge(String chargeID) throws APIException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException {
		ChargeRequest request = new ChargeRequest();
		request.setID(chargeID);
		
		return addCharge(request);
	}
	
	public Charge addCharge(int quantity, String pricingComponentName) throws APIException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException {
		ChargeRequest request = new ChargeRequest();
		request.setPricingComponentValue(quantity);
		request.setPricingComponentName(pricingComponentName);
		
		return addCharge(request);
	}
	
	private Charge addCharge(ChargeRequest request) throws APIException,
			AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException {
		BillForwardClient client = BillForwardClient.getClient();

		String path = String.format("invoices/%s/charge", this.getID());
		
		APIResponse<Charge> resp = client.requestUntyped(BillForwardClient.RequestMethod.POST, path, request, new TypeToken<APIResponse<Charge>>(){}.getType());
		
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		return resp.results[0];
	}
	
	public Charge removeCharge(Charge charge_) throws APIException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException {
		BillForwardClient client = BillForwardClient.getClient();
		String path = String.format("invoices/%s/charge/%s", this.getID(), charge_.getID());
		
		APIResponse<Charge> resp = client.requestUntyped(BillForwardClient.RequestMethod.DELETE, path, "", new TypeToken<APIResponse<Charge>>(){}.getType());
		
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		return resp.results[0];
	}

	public Charge[] getCharges() throws APIException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException {
		String path = String.format("invoices/%s/charge", this.getID());

		BillForwardClient client = BillForwardClient.getClient();
		
		APIResponse<Charge> resp = client.requestUntyped(BillForwardClient.RequestMethod.GET, path, null, new TypeToken<APIResponse<Charge>>(){}.getType());
		
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		return resp.results;
	}
}
