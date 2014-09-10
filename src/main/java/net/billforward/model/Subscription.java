package net.billforward.model;

import java.util.ArrayList;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class Subscription extends MutableEntity<Subscription> {
	@Expose protected String id;
	@Expose protected String accountID;
	@Expose protected String organizationID;
	@Expose protected String productID;
	@Expose protected String productRatePlanID;
	@Expose protected String name;
	@Expose protected String description;
//	@SerializedName("@type")
	@Expose protected String type;
	@Expose protected String state;
	@Expose protected String currentPeriodStart;
	@Expose protected String currentPeriodEnd;
	@Expose protected String effectiveStartDate;
	@Expose protected String actualStartDate;
	@Expose protected String subscriptionEnd;
	@Expose protected String currentPeriodEndExplicit;
	@Expose protected String locked;
	@Expose protected String managedBy;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;
	protected RatePlan productRatePlan;
	@Expose protected List<PaymentMethodSubscriptionLink> paymentMethodSubscriptionLinks = new ArrayList<PaymentMethodSubscriptionLink>();
	@Expose protected List<PricingComponentValue>  pricingComponentValues = new ArrayList<PricingComponentValue>();
	
	public String getID() {
		return id;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductRatePlanID() {
		return productRatePlanID;
	}

	public void setProductRatePlanID(String productRatePlanID) {
		this.productRatePlanID = productRatePlanID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypeAsString() {
		return type;
	}

	public SubscriptionType getType() {
		return SubscriptionType.valueOf(type);
	}

	public void setType(SubscriptionType type) {
		this.type = type.toString();
	}

	public String getStateAsString() {
		return state;
	}


	public SubscriptionState getState() {
		return SubscriptionState.valueOf(state);
	}

	public void setState(SubscriptionState state) {
		this.state = state.toString();
	}

	public String getCurrentPeriodStart() {
		return currentPeriodStart;
	}

	public void setCurrentPeriodStart(String currentPeriodStart) {
		this.currentPeriodStart = currentPeriodStart;
	}

	public String getCurrentPeriodEnd() {
		return currentPeriodEnd;
	}

	public void setCurrentPeriodEnd(String currentPeriodEnd) {
		this.currentPeriodEnd = currentPeriodEnd;
	}

	public String getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(String effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public String getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(String actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public String getSubscriptionEnd() {
		return subscriptionEnd;
	}

	public void setSubscriptionEnd(String subscriptionEnd) {
		this.subscriptionEnd = subscriptionEnd;
	}

	public String getCurrentPeriodEndExplicit() {
		return currentPeriodEndExplicit;
	}

	public void setCurrentPeriodEndExplicit(String currentPeriodEndExplicit) {
		this.currentPeriodEndExplicit = currentPeriodEndExplicit;
	}


	public String getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}

	public String getUpdated() {
		return updated;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public String getCreated() {
		return created;
	}

	public RatePlan getProductRatePlan() {
		return productRatePlan;
	}
	
	public List<PricingComponentValue> getPricingComponentValues() {
		return pricingComponentValues;
	}

	public void setPricingComponentValues(
			List<PricingComponentValue> pricingComponentValues) {
		this.pricingComponentValues = pricingComponentValues;
	}

	public static Subscription create(Subscription subscription) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(subscription, ResourcePath())[0];
	}		

	public static Subscription getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Subscription[] subs = getByID(ID, ResourcePath());
		return subs[0];
	}

	public static Subscription[] getByAccountID(String accountID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(accountID, "account", ResourcePath());
	}

	public static Subscription[] getByState(SubscriptionState state_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(state_.toString(), "state", ResourcePath());
	}
	
	public static Subscription[] getByRatePlanID(String ratePlanID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(ratePlanID, "product-rate-plan", ResourcePath());
	}
	
	public static Subscription[] getByProductID(String productID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(productID, "product", ResourcePath());
	}
	
	
	public static Subscription[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}
	
	protected static ResourcePath resourcePath;
	
	public Subscription(BillForwardClient client_) {
		super(client_);		
	}
	
	public Subscription() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("subscriptions", "subscription",  new TypeToken<APIResponse<Subscription>>() {}.getType());
	}
	public enum SubscriptionType {
		Adhoc,
		Subscription,
		FixedTerm,
		Trial;
	}
	
	public enum SubscriptionState {
		Trial,
		Provisioned,
		Paid,
		AwaitingPayment,
		Cancelled,
		Failed,
		Expired;
	}

	public void addPaymentMethod(PaymentMethod paymentMethod) {
		PaymentMethodSubscriptionLink link = new PaymentMethodSubscriptionLink();
		link.setPaymentMethodID(paymentMethod.getID());
		paymentMethodSubscriptionLinks.add(link);
	}
}
