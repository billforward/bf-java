package net.billforward.model.gateways;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.APIResponse;
import net.billforward.model.MutableEntity;
import net.billforward.model.ResourcePath;

public class StripeToken extends MutableEntity<StripeToken> {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String accountID;
	@Expose protected String cardDetailsID;
	@Expose protected String stripeCustomerID;
	@Expose protected String created;
	@Expose protected String updated;
	@Expose protected String changedBy;
	
	public String getID() {
		return id;
	}
	
	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getCardDetailsID() {
		return cardDetailsID;
	}

	public void setCardDetailsID(String cardDetailsID) {
		this.cardDetailsID = cardDetailsID;
	}

	public String getStripeCustomerID() {
		return stripeCustomerID;
	}

	public void setStripeCustomerID(String stripeCustomerID) {
		this.stripeCustomerID = stripeCustomerID;
	}

	public static StripeToken getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		StripeToken[] tokens = getByID(ID, ResourcePath());
		return tokens[0];
	}

	public StripeToken(String cardDetailsID, String stripeCustomerID) {
		this(cardDetailsID, stripeCustomerID, null);
	}
	
	public StripeToken(String cardDetailsID, String stripeCustomerID, String accountID) {
		this.cardDetailsID = cardDetailsID;
		this.stripeCustomerID = stripeCustomerID;
		this.accountID = accountID;
	}
	
	public StripeToken() {
		
	}
	
	public StripeToken(BillForwardClient m_BfClient) {
		super(m_BfClient);
	}

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	public static StripeToken create(StripeToken stripeToken) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(stripeToken, ResourcePath())[0];
	}
	
	protected static ResourcePath resourcePath;
	
	protected static ResourcePath ResourcePath() {
		
		return resourcePath;
	}
		
	static {
		resourcePath = new ResourcePath("vaulted-gateways/stripe", "vaulted-gateways",  new TypeToken<APIResponse<AuthorizeNetToken>>() {}.getType());
	}
}
