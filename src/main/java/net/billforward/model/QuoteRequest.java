package net.billforward.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class QuoteRequest extends InsertableEntity<QuoteRequest> {
	@Expose protected String product;
	
	@Expose protected String productRatePlan;
	
	@Expose protected String accountID;
	
	@Expose protected String productID;
	
	@Expose protected String subscriptionID;
	
	@Expose protected QuoteType quoteFor;
	
	@Expose protected Boolean prorated;
	
	@Expose protected Boolean free;

	@Expose protected List<QuoteRequestValue> quantities = new ArrayList<QuoteRequestValue>();
	
	@Expose protected List<String> couponCodes = new ArrayList<String>();
	
	@Expose protected Date periodStart;
	@Expose protected Date periodEnd;
	
	public void addQuantity(String pricingComponent, int quantity) {
		QuoteRequestValue comp = new  QuoteRequestValue();
		comp.pricingComponent = pricingComponent;
		comp.quantity = quantity;

		quantities.add(comp);
	}
	
	public void addCoupon(String coupon) {
		couponCodes.add(coupon);
	}

	public static QuoteRequest create(QuoteRequest quoteRequest) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(quoteRequest, ResourcePath())[0];
	}

	protected static ResourcePath resourcePath;

	public QuoteRequest() {
	}
	
	public QuoteRequest(BillForwardClient client_) {
		super(client_);		
	}
	
	@Override
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	public static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("quotes", "quotes",  new TypeToken<APIResponse<QuoteRequest>>() {}.getType());
	}
}
