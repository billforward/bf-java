package net.billforward.model;

import net.billforward.exception.BillforwardException;
import net.billforward.model.usage.UsagePeriod;

import org.junit.Test;

public class UsagePeriodTest extends TestBase {
	@Test
	public void getUsagePeriodsForSubscription() throws BillforwardException {
		//--Get Subscription associated with usage period
		Subscription subscription = Subscription.getByID("9A93B4F4-330F-49AF-8E86-2446BDC609A9");
		
		UsagePeriod[] usagePeriods = UsagePeriod.getUsagePeriodsForSubscription(subscription.getID());
		
		for(UsagePeriod usagePeriod : usagePeriods) {
			System.out.println(usagePeriod.toString());				
		}
	}
	
	@Test
	public void getUsagePeriodsForSubscriptionPeriod() throws BillforwardException {
		//--Get Subscription associated with usage period
		Subscription subscription = Subscription.getByID("9A93B4F4-330F-49AF-8E86-2446BDC609A9");
	
		//First usage period
		int period = 0;
		UsagePeriod[] usagePeriods = UsagePeriod.getUsagePeriodsForSubscriptionPeriod(subscription.getID(), period);
		
		for(UsagePeriod usagePeriod : usagePeriods) {
			System.out.println(usagePeriod.toString());				
		}
	}
	

	@Test
	public void getUsagePeriodsForInvoice() throws BillforwardException {
		//--Get Subscription associated with usage period
		Subscription subscription = Subscription.getByID("9A93B4F4-330F-49AF-8E86-2446BDC609A9");
		
		String invoiceID = "95CE489C-0867-4EB0-9086-C09E444B1249";
		UsagePeriod[] usagePeriods = UsagePeriod.getUsagePeriodsForInvoice(subscription.getID(), invoiceID);
		
		for(UsagePeriod usagePeriod : usagePeriods) {
			System.out.println(usagePeriod.toString());				
		}
	}
}
