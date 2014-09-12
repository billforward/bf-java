package net.billforward.model;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.reflect.TypeToken;

public class PricingCalculator extends BillingEntity {


	public static PriceCalculation requestPriceCalculation(PriceRequest priceRequest_) throws APIException, AuthenticationException, InvalidRequestException, APIConnectionException, CardException {
		BillForwardClient client = BillForwardClient.getClient();
		ResourcePath path = ResourcePath();
		
		String apiRoute = path.getPath();
		String url = String.format("%s/%s", apiRoute, "product-rate-plan");
		
		

		APIResponse<PriceCalculation> resp =  client.requestUntyped(BillForwardClient.RequestMethod.POST, url, priceRequest_, path.getResponseType());
				
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}

		for(PriceCalculation res : resp.results) {
			res.setClient(client);
		}
		
		return resp.results[0];
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
		resourcePath = new ResourcePath("pricing-calculator", "priceCalculation",  new TypeToken<APIResponse<PriceCalculation>>() {}.getType());
	}
	
	public enum PriceRequestCodeType {
        instance,
        book,
        instanceID,
        bookID
    }
}
