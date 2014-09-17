package net.billforward.amendments;

import com.google.gson.annotations.Expose;

public class SubscriptionCancellation {
	@Expose protected String id;
	@Expose protected String subscriptionID;
	@Expose protected String organizationID;
	@Expose protected String serviceEnd;
	@Expose protected String state;

	public String getID() {
		return id;
	}
	
	public String getSubscriptionID() {
		return subscriptionID;
	}
	
	public void setSubscriptionID(String subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public String getServiceEnd() {
		return serviceEnd;
	}

	public void setServiceEnd(String serviceEnd) {
		this.serviceEnd = serviceEnd;
	}

	public String getStateAsString() {
		return state;
	}
	
	public SubscriptionCancellationState getState() {
		return SubscriptionCancellationState.valueOf(state);
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public enum SubscriptionCancellationState {
		Pending,
		Completed,
		Cancelled
	}
}
