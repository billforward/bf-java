package net.billforward.model.gateways;

import net.billforward.BillForwardClient;
import net.billforward.model.APIResponse;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class AuthorizeNetConfiguration extends APIConfiguration {
	@Expose protected String APILoginID;
	@Expose protected String transactionKey;
	
	public String getAPILoginID() {
		return APILoginID;
	}

	public void setAPILoginID(String aPILoginID) {
		APILoginID = aPILoginID;
	}

	public String getTransactionKey() {
		return transactionKey;
	}

	public void setTransactionKey(String transactionKey) {
		this.transactionKey = transactionKey;
	}

	protected static ResourcePath resourcePath;
	
	public AuthorizeNetConfiguration(BillForwardClient client_) {
		super(client_);		
	}
	
	protected AuthorizeNetConfiguration() {
		
	}
	
	public AuthorizeNetConfiguration(BillForwardClient m_BfClient,
									 String loginID,
									 String transactionKey,
									 GatewayEnvironment sandbox) {
		this.setClient(m_BfClient);
		this.setAPILoginID(loginID);
		this.setTransactionKey(transactionKey);
	}

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("vaulted-gateways/authorize-net", "vaulted-gateways",  new TypeToken<APIResponse<AuthorizeNetConfiguration>>() {}.getType());
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
