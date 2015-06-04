package net.billforward.model;

import java.math.BigDecimal;
import java.util.Currency;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class CreditNote  extends BillingEntity {
	@Expose protected String id;
	@Expose protected String accountID;
	@Expose protected String invoiceID;
	@Expose protected String subscriptionID;
	@Expose protected String organizationID;
	@Expose protected String type;
	@Expose protected String currency;
	@Expose protected String description;
	@Expose protected BigDecimal value;
	@Expose protected BigDecimal nominalValue;
	@Expose protected BigDecimal actualValue;
	@Expose protected BigDecimal remainingNominalValue ;
	@Expose protected BigDecimal remainingValue ;
	@Expose protected String createdBy;
	
	public String getId() {
		return id;
	}

	public String getAccountID() {
		return accountID;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public String getSubscriptionID() {
		return subscriptionID;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public CreditNoteType getType() {
		return CreditNoteType.valueOf(type);
	}

	public String getTypeAsString() {
		return type;
	}

	public String getCurrency() {
		return currency;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public BigDecimal getNominalValue() {
		return nominalValue;
	}

	public BigDecimal getActualValue() {
		return actualValue;
	}

	public BigDecimal getRemainingNominalValue() {
		return remainingNominalValue;
	}

	public BigDecimal getRemainingValue() {
		return remainingValue;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	protected static ResourcePath resourcePath;
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("credit-notes", "credit-note",  new TypeToken<APIResponse<CreditNote>>() {}.getType());
	}
}
