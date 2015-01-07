package net.billforward.model.tokenization;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.APIResponse;
import net.billforward.model.PaymentMethod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class StripeTokenCapture {
	@SerializedName("@type")
	@Expose protected String captureType = "StripeAuthCaptureRequest";
	@Expose protected String stripeToken;
	@Expose protected String cardID;
	@Expose protected String accountID;
	

	public PaymentMethod create() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		String fullRoute =  "tokenization/auth-capture";
				
		APIResponse<PaymentMethod> apiResponse = client.requestUntyped(BillForwardClient.RequestMethod.POST, fullRoute, this, new TypeToken<APIResponse<PaymentMethod>>() {}.getType());
		
		if(apiResponse.getResults() != null && apiResponse.getResults().length > 0) {
			return apiResponse.getResults()[0];
		} else {
			String message = "Error in capturing token";
			if(apiResponse.getErrorMessage() != null) {
				message = apiResponse.getErrorMessage();
			}
			
			throw new CardException(message, null, null, null);
		}
	}
	
	public StripeTokenCapture(String stripeToken_, String cardID_) {
		this(stripeToken_, cardID_, null);
	}
	
	public StripeTokenCapture(String stripeToken_, String cardID_, String accountID_) {
		stripeToken  = stripeToken_;
		cardID  = cardID_;
		accountID = accountID_;
	}

	public String getStripeToken() {
		return stripeToken;
	}

	public void setStripeToken(String stripeToken) {
		this.stripeToken = stripeToken;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
}
