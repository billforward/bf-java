package net.billforward.model;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class SubscriptionCancellation extends BillingEntity {
	@Expose protected String id;
	@Expose protected String subscriptionID;
	@Expose protected String source;
	@Expose protected String serviceEnd;
	@Expose protected String cancellationCredit;
	@Expose protected String preCancellationState;
	@Expose protected String state;
	
	public String getId() {
		return id;
	}

	public String getSubscriptionID() {
		return subscriptionID;
	}

	public String getSource() {
		return source;
	}

	public ServiceEndState getServiceEnd() {
		return ServiceEndState.valueOf(serviceEnd);
	}


	public CancellationCredit getServiceEndAsString() {
		return CancellationCredit.valueOf(serviceEnd);
	}

	public String getCancellationCredit() {
		return cancellationCredit;
	}

	public String getCancellationCreditAsString() {
		return cancellationCredit;
	}

	public SubscriptionState getPreCancellationState() {
		return SubscriptionState.valueOf(preCancellationState);
	}
	
	public String getPreCancellationStateAsString() {
		return preCancellationState;
	}

	public SubscriptionCancellationState getState() {
		return SubscriptionCancellationState.valueOf(state);
	}
	
	public String getStateAsString() {
		return state;
	}


	protected static ResourcePath resourcePath;
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("subscriptions", "subscription",  new TypeToken<APIResponse<SubscriptionCancellation>>() {}.getType());
	}
}
