package net.billforward.model;

import java.math.BigDecimal;
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

public class Quote  extends InsertableEntity<QuoteRequest> {
	@Expose protected BigDecimal subtotal;
	
	@Expose protected BigDecimal total;
	
	@Expose protected BigDecimal tax;
	
	@Expose protected BigDecimal discount;
	
	@Expose protected String currency;

	@Expose protected String productName;

	@Expose protected String productRatePlanName;
	
	@Expose protected String productID;
	
	@Expose protected String productRatePlanID;
	
	@Expose protected String subscriptionID;

	@Expose protected String accountID;
	
	@Expose protected List<QuoteBreakdownCost> quantities;
	
	@Expose protected List<QuoteBreakdownDiscount> discounts;
	
	@Expose protected QuoteType quoteFor = QuoteType.RecurringPeriod;
	
	@Expose protected Date periodStart;

	@Expose protected Date periodEnd;
	
	@Expose protected BigDecimal totalPeriods;
	
	@Expose protected boolean prorationEnabled;	
	
	@Expose protected BigDecimal uplift;	
	
	public static Quote create(QuoteRequest quoteRequest) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		 
		APIResponse<Quote> response = client.requestUntyped(BillForwardClient.RequestMethod.POST, resourcePath.getPath(), quoteRequest, resourcePath.getResponseType());
		
		return response.getResults()[0];
	}

	
	protected static ResourcePath resourcePath;
	
	@Override
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	public static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("quotes", "quotes",  new TypeToken<APIResponse<Quote>>() {}.getType());
	}
}
