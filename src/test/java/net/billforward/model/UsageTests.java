package net.billforward.model;


import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.BillforwardException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.usage.Usage;
import net.billforward.model.usage.UsageSession;

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
		PriceCalculation priceCalculation =  RealtimeUsagePriceCalculatorTest.GetPriceForSubscription("988C2978-2BE1-4E62-8349-EED609402E8B");
		System.out.println(priceCalculation);		
	}
	
	public void updateSession(String subscriptionID, long timeStamp, String jvmid, String jvmLabel) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		UsageSession[] usageSessions = UsageSession.getActiveSessionsForSubscription(subscriptionID);
		
		UsageSession session = null;
		for(UsageSession tmpSession : usageSessions) {
			if(tmpSession.getSessionID().equals(jvmid)) {
				session = tmpSession;
				break;
			}
		}
	}
}
