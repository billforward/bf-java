package net.billforward.model;

import org.junit.Test;

import net.billforward.exception.BillforwardException;

public class TestPaymentMethod extends TestBase {

	@Test
	public void CreateCreditNotePaymentMethod() throws BillforwardException {
		Account retrievedAccount = Account.getByID("F15490C4-FC6B-4E40-90B2-1A16A66B619F");
	    
		
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setAccountID(retrievedAccount.getID());
		paymentMethod.setName("Credit Note");
		paymentMethod.setDescription("Credit Note");
		paymentMethod.setCardHolderName("Brooke Shade");
		paymentMethod.setCardType(CardType.None);
		paymentMethod.setGateway(PaymentGateway.credit_note);
		paymentMethod.setReusable(true);
		paymentMethod.setLinkID("");
		
		PaymentMethod.create(paymentMethod);
	}
}
