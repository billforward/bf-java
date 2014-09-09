package net.billforward.model.gateways;

import net.billforward.BillForwardClient;
import net.billforward.model.APIResponse;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class BraintreeMerchantAccount extends APIConfiguration {
	@Expose protected String configurationID;
	@Expose protected String currency;
	@Expose protected String merchantID;
	
	protected static ResourcePath resourcePath;
	
	public BraintreeMerchantAccount(BillForwardClient client_) {
		super(client_);		
	}
	
	protected BraintreeMerchantAccount() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("vaulted-gateways/braintree-merchant", "vaulted-gateways",  new TypeToken<APIResponse<BraintreeMerchantAccount>>() {}.getType());
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
