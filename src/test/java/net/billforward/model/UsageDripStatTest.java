package net.billforward.model;

import java.util.Date;

import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.usage.Usage;
import net.billforward.model.usage.UsagePeriod;
import net.billforward.model.usage.UsageSession;
import net.billforward.model.usage.UsageType;

import org.junit.Test;

public class UsageDripStatTest extends TestBase {


	@Test
	public void createUsage() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
//		Usage usage = new Usage();
//		usage.setSessiondID("session_id");
//		usage.setStart(new Date());
//		usage.setStop(new Date());
//		usage.setSubscriptionID("74C04993-AF41-4812-A5A0-5A44DF7AB02F");
//		usage.setUom("Jvm");
//		usage.setUsageDuration(100);
//		usage.setUsageType(UsageType.Temporal);
//		usage.setUsageValue(100);
//		
//		usage = Usage.create(usage);
//		
//		System.out.println(usage);
		
//		Usage[] usages = Usage.getUsageForSubscription("9DA3818F-D2BC-4C49-88BE-79BBC4EFC9A9");
//		
//		for(Usage usage : usages) {
//			System.out.println(usage);
//		}
		
//		UnitOfMeasure uom = UnitOfMeasure.getByID("39EBC862-738A-4B7A-A0B3-976A28C34311");
//		
//		System.out.println(uom);
//		
//		UsageSession[] sessions = UsageSession.getSessionsForSubscription("74C04993-AF41-4812-A5A0-5A44DF7AB02F");
//		
//		for(UsageSession session : sessions) {
//			session.setStop(new Date());
//			session.stop();
//		}
		
		UsagePeriod[] periods = UsagePeriod.getUsagePeriodsForSubscription("9DA3818F-D2BC-4C49-88BE-79BBC4EFC9A9");

		for(UsagePeriod period : periods) {
			System.out.println(period);
		}
	}
}
