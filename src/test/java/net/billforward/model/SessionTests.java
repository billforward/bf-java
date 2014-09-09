package net.billforward.model;

import static org.junit.Assert.assertEquals;

import net.billforward.exception.BillforwardException;
import net.billforward.model.usage.UsageSession;

import org.junit.Test;

public class SessionTests extends TestBase {
	@Test
	public void startUsageSession() throws BillforwardException {
		//--Get Subscription to associate with session
		Subscription subscription = Subscription.getByID("9A93B4F4-330F-49AF-8E86-2446BDC609A9");
		// Get UnitOfMeasure for usage sessions
		UnitOfMeasure uom = UnitOfMeasure.getByID("0B430C73-3983-40EA-A9F9-FDBD72567597");

		String sessionID = "Session ID 1";
		UsageSession usageSession = UsageSession.startSession(uom, subscription.getID(), sessionID);
		
		System.out.println(usageSession.toString());
	}
	
	@Test
	public void stopUsageSession() throws BillforwardException {
		//--Get Subscription associated with session
		Subscription subscription = Subscription.getByID("9A93B4F4-330F-49AF-8E86-2446BDC609A9");
		// Get UnitOfMeasure of usage sessions
		UnitOfMeasure uom = UnitOfMeasure.getByID("0B430C73-3983-40EA-A9F9-FDBD72567597");

		String sessionID = "Session ID 1";
		UsageSession usageSession = UsageSession.stopSession(uom, subscription.getID(), sessionID);
		
		System.out.println(usageSession.toString());
	}
	
	@Test
	public void getSessionsForSubscription() throws BillforwardException {
		//--Get Subscription associated with sessions
		Subscription subscription = Subscription.getByID("9A93B4F4-330F-49AF-8E86-2446BDC609A9");
		
		assertEquals("9A93B4F4-330F-49AF-8E86-2446BDC609A9", subscription.getID());
		System.out.println(subscription.toString());
		
		UsageSession[] usageSessions = UsageSession.getSessionsForSubscription(subscription.getID());
		
		for(UsageSession usageSession : usageSessions) {
			System.out.println(usageSession.toString());				
		}
	}
	
	@Test
	public void getActiveSessionsForSubscription() throws BillforwardException {
		//--Get Subscription associated with sessions
		Subscription subscription = Subscription.getByID("9A93B4F4-330F-49AF-8E86-2446BDC609A9");
		
		UsageSession[] usageSessions = UsageSession.getActiveSessionsForSubscription(subscription.getID());
		
		if(usageSessions == null) return;
		
		for(UsageSession usageSession : usageSessions) {
			System.out.println(usageSession.toString());				
		}
	}
}
