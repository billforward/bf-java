package net.billforward.model;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

/**
 * An account represents a customer or employee within an organization.
 * All data for a particular customer or employee in an organization is associated with their account, for example, a user's subscriptions, invoices or payment methods.
 * @see <a href="https://app.billforward.net/api/#/method/accounts">https://app.billforward.net/api/#/method/accounts</a>
 * @author Ian
 *
 */
public class Account extends MutableEntity<Account> {
	@Expose protected String  id;
	@Expose protected String organizationID;
	@Expose protected String userID;
	@Expose protected int successfulSubscriptions;
	@Expose protected boolean  deleted;
	@Expose protected String created;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected Profile profile;
	@Expose protected PaymentMethod[] paymentMethods;
	
	/**
	 * @return ID of the account. 
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * @return Organization associated with the account. 
	 */
	public String getOrganizationID() {
		return organizationID;
	}
	
	/**
	 * User associated with the account. 
	 * If this is null, no user is currently associated with the account. A user is only set when an account is associated with a user account. 
	 * @return User associated with the account.
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Associated a user with account
	 * @param  User associated with the account. 
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/**
	 *  Number of distinct, paid subscriptions associated with this account. Initially the value will be 0 when no successful subscriptions have been taken. A subscription cancelled whilst in trial is counted as successful. 
	 * @return Number of distinct, paid subscriptions associated with this account.
	 */
	public int getSuccessfulSubscriptions() {
		return successfulSubscriptions;
	}
	
	/**
	 * Indicates if an account has been retired. If an account has been retired it can still be retrieved using the appropriate flag on API requests. 
	 * @return Indicates if an account has been retired.
	 */
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	/**
	 * @return The UTC DateTime when the object was created. 
	 */
	public String getCreated() {
		return created;
	}
	
	/**
	 * @return The UTC DateTime when the object was last updated. 
	 */
	public String getUpdated() {
		return updated;
	}
	
	/**
	 * @return ID of the user who last updated the entity. 
	 */
	public String getChangedBy() {
		return changedBy;
	}
	
	/**
	 * @return
	 */
	public Profile getProfile() {
		if(profile.m_client == null) {
			profile.setClient(m_client);
		}
		return profile;
	}
	
	public void setProfile(Profile profile_) {
		this.profile = profile_;
	}
	
	public PaymentMethod[] getPaymentMethods() {
		return paymentMethods;
	}
	
	public static Account create(Account account) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(account, ResourcePath())[0];
	}
	
	public static Account getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Account[] accs = getByID(ID, ResourcePath());
		return accs[0];
	}
	
	public static Account[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}
	
	protected static ResourcePath resourcePath;

	public Account(BillForwardClient client_) {
		super(client_);		
	}
	
	protected Account() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("accounts", "account",  new TypeToken<APIResponse<Account>>() {}.getType());
	}
}
