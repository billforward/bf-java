package net.billforward.model;


import net.billforward.exception.BillforwardException;
import net.billforward.model.usage.Usage;

import org.junit.Test;

public class UsageTests extends TestBase {
	@Test
	public void getUsageForSubscription() throws BillforwardException {
		//--Get Subscription associated with usage period
		Subscription subscription = Subscription.getByID("9A93B4F4-330F-49AF-8E86-2446BDC609A9");
		
		Usage[] usages = Usage.getUsageForSubscription(subscription.getID());
		
		for(Usage usage : usages) {
			System.out.println(usage.toString());				
		}
	}
	
	@Test
	public void getUsageForSubscriptionPeriod() throws BillforwardException {
		//--Get Subscription associated with usage period
		Subscription subscription = Subscription.getByID("9A93B4F4-330F-49AF-8E86-2446BDC609A9");
	
		//First usage period
		int period = 0;
		Usage[] usages = Usage.getUsageForSubscriptionPeriod(subscription.getID(), period);
		
		for(Usage usage : usages) {
			System.out.println(usage.toString());				
		}
	}

	

	@Test
	public void generatePrice() throws BillforwardException {
		PriceCalculation priceCalculation =  RealtimeUsagePriceCalculatorTest.GetPriceForSubscription("B26E8790-6624-4F2E-BA06-0CB537AA7211");
		System.out.println(priceCalculation);		
	}
}
