package net.billforward.model;

import org.junit.Test;

import net.billforward.exception.BillforwardException;
import net.billforward.model.usage.Period;

public class PeriodTests  extends TestBase {
	@Test
	public void getPeriodsForSubscription() throws BillforwardException {
		//--Get Subscription associated with period
		Subscription subscription = Subscription.getByID("9A93B4F4-330F-49AF-8E86-2446BDC609A9");
		
		Period[] periods = Period.getPeriodsForSubscription(subscription.getID());
		
		for(Period period : periods) {
			System.out.println(period.toString());				
		}
	}
	
	@Test
	public void getAllPeriods() throws BillforwardException {
		//--Get all periods
		Period[] periods = Period.getAllPeriods();
		
		for(Period period : periods) {
			System.out.println(period.toString());				
		}
	}
}
