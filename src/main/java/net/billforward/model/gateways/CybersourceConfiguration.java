package net.billforward.model.gateways;

import net.billforward.BillForwardClient;
import net.billforward.model.APIResponse;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class CybersourceConfiguration extends APIConfiguration {
	@Expose protected String merchantID;
	@Expose protected String production;
	@Expose protected String keyFilename;
	
	protected static ResourcePath resourcePath;
	
	public CybersourceConfiguration(BillForwardClient client_) {
		super(client_);		
	}
	
	protected CybersourceConfiguration() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("vaulted-gateways/cybersource", "vaulted-gateways",  new TypeToken<APIResponse<CybersourceConfiguration>>() {}.getType());
	}
}
