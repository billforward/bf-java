package net.billforward.model;

import net.billforward.BillForwardClient;

import com.google.gson.reflect.TypeToken;

public class PricingCalculator extends BillingEntity {


	public static PriceCalculation requestPriceCalculation(PriceRequest priceRequest_) {
		return null;
	}
	
	public PricingCalculator(BillForwardClient client_) {
		super(client_);		
	}
	
	protected PricingCalculator() {
		
	}
	
	protected static ResourcePath resourcePath;
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("pricing-calculator", "priceCalculation",  new TypeToken<APIResponse<PricingCalculator>>() {}.getType());
	}
	
	public enum PriceRequestCodeType {
		instance,
		book,
	    instanceID,
	    bookID
	}
}
