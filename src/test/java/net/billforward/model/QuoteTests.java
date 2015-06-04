package net.billforward.model;

import net.billforward.exception.BillforwardException;

import org.junit.Test;

public class QuoteTests extends TestBase {
	@Test
	public void quoteTest() throws BillforwardException {
		QuoteRequest quoteRequest = new QuoteRequest();
		quoteRequest.productRatePlan = "bar";
		quoteRequest.product = "foo";
		quoteRequest.addQuantity("Bandwidth", 500);
		quoteRequest.addQuantity("Foo", 150);

//
//		quoteRequest.addCoupon("simple0641E704");
//		quoteRequest.addCoupon("simpleBD3D399C");
////		quoteRequest.addCoupon("coupon37E7C6F5");
////		quoteRequest.addCoupon("simpleBD3D399C");
////		quoteRequest.addCoupon("coupon37E7C6F5");
////		quoteRequest.addCoupon("simpleBD3D399C");
////		quoteRequest.addCoupon("coupon37E7C6F5");
		
		//QuoteRequest q2 = QuoteRequest.create(quote);
		
		Quote quote = Quote.create(quoteRequest);
		
		System.out.println(quote);
	}
}
