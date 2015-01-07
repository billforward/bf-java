package net.billforward.model;

import org.junit.Test;

import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.tokenization.StripeTokenCapture;

public class CaptureTests extends TestBase {
	
	@Test
	public void testCapture() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		String cardID = "card_15IIlACql6sPc0L6mp1g8MTD";
		String tokenID = "tok_15IIlACql6sPc0L6TX5BU8OK";
		String accountID = "17E5DF9F-583A-4506-8033-A94A260E82E0"; //Optional
		
		StripeTokenCapture tokenToCapture = new StripeTokenCapture(tokenID, cardID, accountID);
		
		PaymentMethod paymentMethod = PaymentMethod.capture(tokenToCapture);

		System.out.println(paymentMethod);
	}
}
