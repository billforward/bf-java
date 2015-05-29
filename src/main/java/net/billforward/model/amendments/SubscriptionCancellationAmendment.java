package net.billforward.model.amendments;

import net.billforward.model.APIResponse;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class SubscriptionCancellationAmendment extends Amendment {
	@Expose protected String serviceEnd;

	public String getServiceEndAsString() {
		return serviceEnd;
	}

	public void setServiceEnd(String serviceEnd) {
		this.serviceEnd = serviceEnd;
	}
	
	public SubscriptionCancellationState getServiceEnd() {
		return SubscriptionCancellationState.valueOf(state);
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath resourcePath;
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
		
	static {
		resourcePath = new ResourcePath("amendments", "amendments",  new TypeToken<APIResponse<SubscriptionCancellationAmendment>>() {}.getType());
	}

	public String getOrganizationID() {
		return organizationID;
	}
}
