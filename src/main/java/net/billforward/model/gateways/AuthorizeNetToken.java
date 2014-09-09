package net.billforward.model.gateways;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.APIResponse;
import net.billforward.model.Account;
import net.billforward.model.MutableEntity;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class AuthorizeNetToken extends MutableEntity<Account> {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String accountID;
	@Expose protected String customerProfileID;
	@Expose protected String customerPaymentProfileID;
	@Expose protected String cardDetailsID;
	@Expose protected String lastFourDigits;
	@Expose protected String created;
	@Expose protected String updated;
	@Expose protected String changedBy;

	public String getID() {
		return id;
	}
	
	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getCustomerProfileID() {
		return customerProfileID;
	}

	public void setCustomerProfileID(String customerProfileID) {
		this.customerProfileID = customerProfileID;
	}

	public String getCustomerPaymentProfileID() {
		return customerPaymentProfileID;
	}

	public void setCustomerPaymentProfileID(String customerPaymentProfileID) {
		this.customerPaymentProfileID = customerPaymentProfileID;
	}

	public String getCardDetailsID() {
		return cardDetailsID;
	}

	public void setCardDetailsID(String cardDetailsID) {
		this.cardDetailsID = cardDetailsID;
	}

	public String getLastFourDigits() {
		return lastFourDigits;
	}

	public void setLastFourDigits(String lastFourDigits) {
		this.lastFourDigits = lastFourDigits;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public String getCreated() {
		return created;
	}

	public String getUpdated() {
		return updated;
	}

	public String getChangedBy() {
		return changedBy;
	}
	
	public static AuthorizeNetToken getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		AuthorizeNetToken[] tokens = getByID(ID, ResourcePath());
		return tokens[0];
	}
	
	public AuthorizeNetToken(BillForwardClient m_BfClient) {
		super(m_BfClient);
	}

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath resourcePath;
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
		
	static {
		resourcePath = new ResourcePath("vaulted-gateways/authorize-net", "vaulted-gateways",  new TypeToken<APIResponse<AuthorizeNetToken>>() {}.getType());
	}
}
