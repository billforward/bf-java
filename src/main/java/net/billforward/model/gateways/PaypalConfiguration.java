package net.billforward.model.gateways;

import net.billforward.BillForwardClient;
import net.billforward.model.APIResponse;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class PaypalConfiguration extends APIConfiguration {
	@Expose protected String endpoint;
	@Expose protected String clientID;
	@Expose protected String clientSecret;
	
	protected static ResourcePath resourcePath;
	
	public PaypalConfiguration(BillForwardClient client_) {
		super(client_);		
	}
	
	protected PaypalConfiguration() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("vaulted-gateways/paypal", "vaulted-gateways",  new TypeToken<APIResponse<PaypalConfiguration>>() {}.getType());
	}
}
