package net.billforward.model;

import com.google.gson.reflect.TypeToken;

public class PriceCalculation extends BillingEntity {
	protected PriceCalculation() {
		
	}
	
	protected static ResourcePath resourcePath;
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("pricing-components", "pricing-component",  new TypeToken<APIResponse<PriceCalculation>>() {}.getType());
	}
	
	public enum PriceRequestCodeType {
		instance,
		book,
        instanceID,
        bookID
	}
}
