package net.billforward.model;

import java.util.Date;

import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.usage.Period;
import net.billforward.model.usage.Usage;
import net.billforward.model.usage.UsageSession;

public class RealtimeUsagePriceCalculatorTest {
	public static PriceCalculation GetPriceForSubscription(String subscriptionID_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Subscription subscription = Subscription.getByID(subscriptionID_);		
		
		Period period = Period.getLatestPeriodsForSubscription(subscription.getID());
		
		if(period == null) {
			return null;
		}

		System.out.println(period);
		System.out.println(subscription);
		if(subscription.getCurrentPeriodStart().getTime() > period.getStart().getTime() &&
		   period.getStart().getTime() <= subscription.getCurrentPeriodEnd().getTime()){
			return null;
		}
		
		Usage[] usages = Usage.getUsageForSubscriptionPeriod(subscription.getID(), period.getPeriod());		
		
		long length = 0;
		if(usages != null) {
			for(Usage usage : usages) {
					length += usage.getUsageValue();
			}
		}
		
		UsageSession[] usageSessions = UsageSession.getActiveSessionsForSubscription(subscription.getID());
		
		if(usageSessions != null) {
			for(UsageSession usageSession : usageSessions) {
				Date start = usageSession.getStart();
				Date stop = usageSession.getStop();
				
				if(stop == null) {
					stop = new Date();
				}
				
				
				//Deal with ongoing session
				if(period.getStart().getTime() > start.getTime()) {
					start = period.getStart();
				}
				
				long lengthDifferene = stop.getTime() - start.getTime();
				if(lengthDifferene > 0) {
					length += lengthDifferene;
				}
			}			
		}
		
		long currentUsage = aggregateSecondsToHours(length);
		
		RatePlan plan = RatePlan.getByID(subscription.getProductRatePlanID());
		
		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setProductRatePlanID(plan.getID());		
		
		for(PricingComponent pricingComponent : plan.getPricingComponents()) {
			PricingComponentValue value = new PricingComponentValue();
			value.setSubscriptionID(pricingComponent.getID());
			value.setPricingComponentID(pricingComponent.getID());
			value.setValue((int)currentUsage);
			
			priceRequest.addPricingComponentValue(value);
		}		
		
		PriceCalculation price = PricingCalculator.requestPriceCalculation(priceRequest);
		
		return price;
	}
	
	public static long aggregateSecondsToHours(long usageDurationInMilliseconds_) {
		long hour = (usageDurationInMilliseconds_ / SECOND);
		long rem = (usageDurationInMilliseconds_ % SECOND);
		
		if(rem > 0) {
			hour++;
		}
		return hour;
	}
	
	private static final long SECOND = 1000;
	private static final long MINUTE = 60 * SECOND;
	private static final long HOUR   = 60 * MINUTE;
}
