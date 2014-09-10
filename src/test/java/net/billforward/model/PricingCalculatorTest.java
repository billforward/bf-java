package net.billforward.model;

import net.billforward.exception.BillforwardException;

import org.junit.Test;

public class PricingCalculatorTest extends TestBase {
	@Test
	public void GetRatePlan() throws BillforwardException {

		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setProductRatePlanID("");
		
		PricingComponentValue value = new PricingComponentValue();
		priceRequest.addPricingComponentValue(value);
		
		PriceCalculation price = PricingCalculator.requestPriceCalculation(priceRequest);
		
		System.out.println(price.toString());
	}
}
