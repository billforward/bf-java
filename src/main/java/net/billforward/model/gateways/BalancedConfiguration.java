package net.billforward.model.gateways;

import net.billforward.BillForwardClient;
import net.billforward.model.APIResponse;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class BalancedConfiguration extends APIConfiguration {
	@Expose protected String APIKey;
	
	protected static ResourcePath resourcePath;
	
	public BalancedConfiguration(BillForwardClient client_) {
		super(client_);		
	}
	
	protected BalancedConfiguration() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("vaulted-gateways/balanced", "vaulted-gateways",  new TypeToken<APIResponse<BalancedConfiguration>>() {}.getType());
	}
	
	/**
	 * 
	@Expose protected String APIKey;
	@Expose protected String merchantID;
	@Expose protected String publicKey;
	@Expose protected String privateKey;
	@Expose protected String keyFilename;
	@Expose protected String endpoint;
	@Expose protected String clientID;
	@Expose protected String clientSecret;
	@Expose protected String gatewayAccountID;
	@Expose protected String refreshToken;
	@Expose protected String APILoginID;
	@Expose protected String transactionKey;
	*/
}
