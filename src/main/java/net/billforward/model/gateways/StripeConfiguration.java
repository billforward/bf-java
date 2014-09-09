package net.billforward.model.gateways;

import net.billforward.BillForwardClient;
import net.billforward.model.APIResponse;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class StripeConfiguration extends APIConfiguration {
	@Expose protected String gatewayAccountID;
	@Expose protected String refreshToken;
	@Expose protected String transactionKey;
	
	protected static ResourcePath resourcePath;
	
	public StripeConfiguration(BillForwardClient client_) {
		super(client_);		
	}
	
	protected StripeConfiguration() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("vaulted-gateways/stripe", "vaulted-gateways",  new TypeToken<APIResponse<AuthorizeNetConfiguration>>() {}.getType());
	}
}
