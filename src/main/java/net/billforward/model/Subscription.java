package net.billforward.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.amendments.CancellationAmendment;
import net.billforward.model.amendments.CancellationAmendment.ServiceEndState;
import net.billforward.model.amendments.ComponentChange;
import net.billforward.model.amendments.PricingComponentValueChangeAmendment;

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
	@Expose protected String type;
	@Expose protected String state;
	@Expose protected Date currentPeriodStart;
	@Expose protected Date currentPeriodEnd;
	@Expose protected Date effectiveStartDate;
	@Expose protected Date actualStartDate;
	@Expose protected Date subscriptionEnd;
	@Expose protected Date currentPeriodEndExplicit;
	@Expose protected String locked;
	@Expose protected String managedBy;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;
	@Expose protected RatePlan productRatePlan;
	
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

	public Date getCurrentPeriodStart() {
		return currentPeriodStart;
	}

	public void setCurrentPeriodStart(Date currentPeriodStart) {
		this.currentPeriodStart = currentPeriodStart;
	}

	public Date getCurrentPeriodEnd() {
		return currentPeriodEnd;
	}

	public void setCurrentPeriodEnd(Date currentPeriodEnd) {
		this.currentPeriodEnd = currentPeriodEnd;
	}

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getSubscriptionEnd() {
		return subscriptionEnd;
	}

	public void setSubscriptionEnd(Date subscriptionEnd) {
		this.subscriptionEnd = subscriptionEnd;
	}

	public Date getCurrentPeriodEndExplicit() {
		return currentPeriodEndExplicit;
	}

	public void setCurrentPeriodEndExplicit(Date currentPeriodEndExplicit) {
		this.currentPeriodEndExplicit = currentPeriodEndExplicit;
	}


	public String getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
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

		if(subs == null || subs.length == 0) return null;
		
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

	/** Cancels a subscription immediately
	 * @return The amendment associated with the cancellation
	 **/
	public CancellationAmendment cancelImmediately() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		CancellationAmendment amendment = new CancellationAmendment();
		amendment.setSubscriptionID(this.getID());
		amendment.setServiceEnd(ServiceEndState.Immediate);
		amendment = CancellationAmendment.create(amendment);
		return amendment;
	}
	
	/***
	 * Cancel subscription at the current period end
	 * @return The amendment associated with the cancellation
	 */
	public CancellationAmendment cancelAtPeriodEnd() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		CancellationAmendment amendment = new CancellationAmendment();
		amendment.setSubscriptionID(this.getID());
		amendment.setServiceEnd(ServiceEndState.AtPeriodEnd);
		amendment = CancellationAmendment.create(amendment);
		return amendment;
	}

	/**
	 * Upgrade or downgrade the subscription to the new value.
	 * 
	 * <p>
	 * If the new value is greater than the existing value a downgrade will occur.
	 * If the new value is less than the existing value an upgrade will occur.
	 * 
	 * By default upgrades happen immediately and the upgrade cost is added to the next invoice.
	 * Downgrades happen at the end of the current billing period, with no refunds.  
	 * </p>
	 * 
	 * @param name The name of the Pricing Component to upgrade
	 * @param newValue The new value of the pricing component
	 * @return The component change amendment which encapsulates the upgrade. 
	 */
	public PricingComponentValueChangeAmendment upgrade(String name, int newValue) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		HashMap<String, Integer> changes = new HashMap<String, Integer>();
		changes.put(name, newValue);
		return this.upgrade(changes);
	}

	/**
	 * Upgrade or downgrade the subscription to the new value.
	 * 
	 * <p>
	 * If the new value is greater than the existing value a downgrade will occur.
	 * If the new value is less than the existing value an upgrade will occur.
	 * 
	 * By default upgrades happen immediately and the upgrade cost is added to the next invoice.
	 * Downgrades happen at the end of the current billing period, with no refunds.  
	 * </p>
	 * 
	 * @param name The name of the Pricing Component to upgrade
	 * @param newValue The new value of the pricing component
	 * @return The component change amendment which encapsulates the upgrade. 
	 */
	public PricingComponentValueChangeAmendment upgrade(HashMap<String, Integer> changes_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {

		PricingComponentValueChangeAmendment pricingComponentValueChangeAmendment = new PricingComponentValueChangeAmendment();
		
		for (Map.Entry<String, Integer> entry : changes_.entrySet()) {
			ComponentChange change = new ComponentChange(entry.getKey(), entry.getValue());

			pricingComponentValueChangeAmendment.addComponentChange(change);
		}

		pricingComponentValueChangeAmendment.setSubscriptionID(this.getID());
		
		return PricingComponentValueChangeAmendment.create(pricingComponentValueChangeAmendment);
				
	}
	
	/**
	 * Upgrade or downgrade the subscription to the new value.
	 * 
	 * <p>
	 * If the new value is greater than the existing value a downgrade will occur.
	 * If the new value is less than the existing value an upgrade will occur.
	 * 
	 * By default upgrades happen immediately and the upgrade cost is added to the next invoice.
	 * Downgrades happen at the end of the current billing period, with no refunds.  
	 * </p>
	 * 
	 * @param name The name of the Pricing Component to upgrade
	 * @param newValue The new value of the pricing component
	 * @return The component change amendment which encapsulates the upgrade. 
	 */
	public PricingComponentValueChangeAmendment upgrade(List<ComponentChange> changes_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		PricingComponentValueChangeAmendment pricingComponentValueChangeAmendment = new PricingComponentValueChangeAmendment();
		for(ComponentChange change : changes_) {
			pricingComponentValueChangeAmendment.addComponentChange(change);
		}
		
		pricingComponentValueChangeAmendment.setSubscriptionID(this.getID());
		
		return PricingComponentValueChangeAmendment.create(pricingComponentValueChangeAmendment);
	}
}
