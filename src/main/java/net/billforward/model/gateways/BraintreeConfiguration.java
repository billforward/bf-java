package net.billforward.model.gateways;

import net.billforward.BillForwardClient;
import net.billforward.model.APIResponse;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class BraintreeConfiguration extends APIConfiguration {
	@Expose protected String merchantID;
	@Expose protected String publicKey;
	@Expose protected String privateKey;
	
	protected static ResourcePath resourcePath;
	
	public BraintreeConfiguration(BillForwardClient client_) {
		super(client_);		
	}
	
	protected BraintreeConfiguration() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("vaulted-gateways/braintree", "vaulted-gateways",  new TypeToken<APIResponse<BraintreeConfiguration>>() {}.getType());
	}
}