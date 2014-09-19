package net.billforward.model.amendments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.model.InsertableEntity;
import net.billforward.model.gateways.GatewayTypeMapping;

import com.google.gson.annotations.Expose;

public abstract class Amendment extends InsertableEntity<Amendment> {
	@Expose protected String  id;
	@Expose protected String organizationID;
	@Expose protected String subscriptionID;
	@Expose protected Date  actioningTime;
	@Expose protected Date  actionedTime;
	@Expose protected String state;
	@Expose protected Date nextStatusCheckTime;
	@Expose protected Date nextOverdueCheckTime;
	@Expose protected Boolean deleted;
	@Expose protected Date created;
	@Expose protected Date updated;
	@Expose protected String changedBy;	

	public String getID() {
		return id;
	}
	
	public String getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(String subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public Date getActioningTime() {
		return actioningTime;
	}

	public void setActioningTime(Date actioningTime) {
		this.actioningTime = actioningTime;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public Date getActionedTime() {
		return actionedTime;
	}
	
	public String getStateAsString() {
		return state;
	}
	
	public AmendmentState getState() {
		return AmendmentState.valueOf(state);
	}

	public Date getNextStatusCheckTime() {
		return nextStatusCheckTime;
	}

	public Date getNextOverdueCheckTime() {
		return nextOverdueCheckTime;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public Amendment(BillForwardClient client_) {
		super(client_);
	}
	
	protected Amendment() {	
	}
	
	public enum AmendmentType {
		invoiceNextExecutionAttemptAmendment,
		cancellationAmendment,
		pricingComponentValueAmendment,
		amendmentDiscardAmendment,
		CompoundAmendment,
	    FixedTermExpiryAmendment,
	    GatewayMigrationAmendment,
	    DefaultQuantityAmendment,
	    InvoiceRecalculationAmendment,
	    EndTrialAmendment,
	    CreateSubscriptionChargeAmendment,
	    InvoiceOutstandingChargesAmendment,
	    IssueInvoiceAmendment;
	}
	
	public enum AmendmentState {
		Pending,
		Succeeded,
		Failed,
		Discarded
	}
	
	public static GatewayTypeMapping[] getTypeMappings() {
		List<GatewayTypeMapping> typeMappings = new ArrayList<GatewayTypeMapping>();
		typeMappings.add(new GatewayTypeMapping(InvoiceNextExecutionAttemptAmendment.class, InvoiceNextExecutionAttemptAmendment.class.getSimpleName()));
		typeMappings.add(new GatewayTypeMapping(InvoiceRecalculationAmendment.class, InvoiceRecalculationAmendment.class.getSimpleName()));
		typeMappings.add(new GatewayTypeMapping(IssueInvoiceAmendment.class, IssueInvoiceAmendment.class.getSimpleName()));
		typeMappings.add(new GatewayTypeMapping(CancellationAmendment.class, CancellationAmendment.class.getSimpleName()));
		
		return typeMappings.toArray(new GatewayTypeMapping[]{});
	}
}
