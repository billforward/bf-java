package net.billforward.model.usage;

import net.billforward.model.APIResponse;
import net.billforward.model.BillingEntity;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

class UsageSessions extends BillingEntity {
	@Expose  protected UsageSession[] usageSessions;
	
	protected UsageSessions() {
		
	}
	
	protected UsageSession[] getSessions() {
		return usageSessions;
	}
	
	protected UsageSessions(UsageSession[] usageSessions_) {
		usageSessions = usageSessions_;
	}

	protected static ResourcePath resourcePath;

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("usage-sessions", "usageSession",  new TypeToken<APIResponse<Period>>() {}.getType());
	}
}
