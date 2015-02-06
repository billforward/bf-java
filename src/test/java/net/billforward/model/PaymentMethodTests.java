package net.billforward.model;

import org.junit.Test;

import net.billforward.exception.BillforwardException;

public class PaymentMethodTests  extends TestBase {
	//@Test
	public void addPaymentMethod() throws BillforwardException {
		Account acc = Account.getByID("ACC-A5C6FD27-2FBB-42A1-BE57-D6ABD880");
		PaymentMethod[] paymentMethods = acc.getPaymentMethods();
				
		Subscription sub = Subscription.getByID("SUB-105D5BFE-C9E2-4C9A-BFD0-70AF961E");
		
		for(PaymentMethod paymentMethod : paymentMethods) {
			sub.addPaymentMethod(paymentMethod.getID());
			System.out.println(paymentMethod);
		}
	}
	
	@Test
	public void remove() throws BillforwardException {				
		Subscription sub = Subscription.getByID("SUB-105D5BFE-C9E2-4C9A-BFD0-70AF961E");

		PaymentMethod[] paymentMethods = sub.getPaymentMethods();
		for(PaymentMethod paymentMethod : paymentMethods) {
			sub.removePaymentMethod(paymentMethod.getID());
			System.out.println(paymentMethod);
		}
	}
	

	
	//@Test
	public void getPaymentMethods() throws BillforwardException {
				
		Subscription sub = Subscription.getByID("SUB-105D5BFE-C9E2-4C9A-BFD0-70AF961E");
		PaymentMethod[] paymentMethods = sub.getPaymentMethods();
		
		for(PaymentMethod paymentMethod : paymentMethods) {
			sub.removePaymentMethod(paymentMethod.getID());
			System.out.println(paymentMethod);
		}
	}
}
