package net.billforward.model;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class PaymentMethodSubscriptionLink extends MutableEntity<PaymentMethodSubscriptionLink> {
	@Expose protected String id;
	@Expose protected String subscriptionID;
	@Expose protected String organizationID;
	@Expose protected String paymentMethodID;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;

	public String getID() {
		return id;
	}
	
	public String getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(String subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public String getPaymentMethodID() {
		return paymentMethodID;
	}

	public void setPaymentMethodID(String paymentMethodID) {
		this.paymentMethodID = paymentMethodID;
	}

	public String getOrganizationID() {
		return organizationID;
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
	
	protected static ResourcePath resourcePath;
	
	public PaymentMethodSubscriptionLink(BillForwardClient client_) {
		super(client_);		
	}
	
	protected PaymentMethodSubscriptionLink() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("payment-method-subscription-links", "payment-method-subscription-link",  new TypeToken<APIResponse<PaymentMethodSubscriptionLink>>() {}.getType());
	}
}
