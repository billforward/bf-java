package net.billforward.model;

import net.billforward.exception.BillforwardException;

import org.junit.Test;

public class PricingCalculatorTest extends TestBase {
	@Test
	public void GetRatePlan() throws BillforwardException {

		RatePlan plan = RatePlan.getByID("2B9621AC-606A-49F3-970C-DE0B421470C4");
		
		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setProductRatePlanID(plan.getID());		
		
		for(PricingComponent pricingComponent : plan.getPricingComponents()) {
			PricingComponentValue value = new PricingComponentValue();
			value.setSubscriptionID(pricingComponent.getID());
			value.setPricingComponentID(pricingComponent.getID());
			value.setValue(5);
			
			priceRequest.addPricingComponentValue(value);
		}		
		
		PriceCalculation price = PricingCalculator.requestPriceCalculation(priceRequest);
		
		System.out.println(price.toString());
	}
}
