package net.billforward.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.billforward.exception.BillforwardException;
import net.billforward.model.Subscription.SubscriptionState;
import net.billforward.model.Subscription.SubscriptionType;
import net.billforward.model.amendments.CancellationAmendment;
import net.billforward.model.amendments.ComponentChange;
import net.billforward.model.amendments.PricingComponentValueChangeAmendment;
import net.billforward.model.charges.ChargeRequest;
import net.billforward.model.charges.Charge;

import org.junit.Test;

public class SubscriptionTests extends TestBase {


//	@Test
	public void UpgradeSubscription() throws BillforwardException {
		//--Get Subscription by ID
		Subscription subscription = Subscription.getByID("SUB-F5A88586-6C31-4FA0-8131-2F6BFB0B");

		
		//ChargeRequest r = subscription.addCharge(new BigDecimal("10.00"));

		//ChargeRequest r = subscription.addCharge(50, "Bandwidth");
		Charge r = subscription.addCharge(50, "Speed");
		
		
		System.out.println(r.toString());
	}
	
	@Test
	public void getCharges() throws BillforwardException {
		//--Get Subscription by ID
		Subscription subscription = Subscription.getByID("SUB-F5A88586-6C31-4FA0-8131-2F6BFB0B");
	
		
		Charge[] r = subscription.getPendingCharges();
		
			
		for(Charge rr : r) {
			System.out.println(rr.toString());
		}
	}

	//@Test
	public void GetAllSubscriptions() throws BillforwardException {
		//--Get all GetAllSubscriptions		
		Subscription[] subscriptions = Subscription.getAll();
		
		assertNotNull(subscriptions);
	}

	//@Test
	public void CancelSubscription() throws BillforwardException {
		//--Get subscription to cancel
		Subscription subscription = Subscription.getByID("2A8DFF25-801E-465C-8921-1182BDA6CD07");
		
		CancellationAmendment cancellationAmendment = subscription.cancelImmediately();
		
		assertNotNull(cancellationAmendment);
		

		System.out.println(cancellationAmendment.toString());
	}

	//@Test
	public void GetSubscriptionByID() throws BillforwardException {
		//--Get Subscription by ID
		Subscription subscription = Subscription.getByID("79AA1229-4E97-4368-A513-8CFE8C0BDD95");
		
		assertEquals("79AA1229-4E97-4368-A513-8CFE8C0BDD95", subscription.getID());
		System.out.println(subscription.toString());
	}
	
	//@Test
	public void GetSubscriptionByAccountID() throws BillforwardException {
		//--Get Account to get subscriptions for
		Account account = Account.getByID("F15490C4-FC6B-4E40-90B2-1A16A66B619F");
		
		//  Get Subscription by Account ID
		Subscription subscriptions[] = Subscription.getByAccountID(account.getID());
		
		for(Subscription subscription : subscriptions) {
			assertEquals(account.getID(), subscription.getAccountID());			
		}
		
		assertNotNull(subscriptions);
	}

	//@Test
	public void GetSubscriptionByRatePlan() throws BillforwardException {
		//--Get RatePlan to get subscriptions for
		RatePlan ratePlan = RatePlan.getByID("1234CC32-B6E6-4972-BB28-965323148D9F");
		
		//  Get Subscription by Account ID
		Subscription subscriptions[] = Subscription.getByRatePlanID(ratePlan.getID());
		
		for(Subscription subscription : subscriptions) {
			assertEquals(ratePlan.getID(), subscription.getProductRatePlan());			
		}
		
		assertNotNull(subscriptions);
	}
	
	//@Test
	public void GetSubscriptionByProduct() throws BillforwardException {
		//--Get Product to get subscriptions for
		Product product = Product.getByID("D3E0F064-9E67-492E-8CFC-73E97B0B006A");
		
		//  Get Subscription by Account ID
		Subscription subscriptions[] = Subscription.getByProductID(product.getID());
		
		for(Subscription subscription : subscriptions) {
			assertEquals(product.getID(), subscription.getProductID());			
		}
		
		assertNotNull(subscriptions);
	}
	
	//@Test
	public void GetSubscriptionByState() throws BillforwardException {		
		//  Get Subscription by state
		Subscription subscriptions[] = Subscription.getByState(SubscriptionState.Provisioned);
		
		for(Subscription subscription : subscriptions) {
			assertEquals(subscription.getState(), subscription.getState());			
		}
		
		assertNotNull(subscriptions);
	}

	//@Test
	public void UpdateSubscription() throws BillforwardException {
		//--Get Subscription by ID
		Subscription subscription = Subscription.getByID("5F66E6F1-22DB-4F46-8EF8-0DD6CB34F05A");

		System.out.println(subscription.toString());
		
		// Update subscription state
		subscription.setState(SubscriptionState.AwaitingPayment);
		
		subscription = subscription.save();

		System.out.println(subscription.toString());
		
		assertEquals(SubscriptionState.AwaitingPayment, subscription.getState());
	}
	
	//@Test
	public void SubscriptionCreate() throws BillforwardException {
		//--Get rate-plan for cost
		RatePlan ratePlan = RatePlan.getByID("1234CC32-B6E6-4972-BB28-965323148D9F");

		// Get account who is subscribing
		Account account = Account.getByID("F15490C4-FC6B-4E40-90B2-1A16A66B619F");

		//Set how much this account is initially subscribing for
		PricingComponentValue value = new PricingComponentValue();
		value.setPricingComponentID(ratePlan.getPricingComponents().get(0).getID());
		value.setValue(3);
		
		//Create the subscription
		Subscription subscription = new Subscription();
		subscription.setType(SubscriptionType.Subscription);
		subscription.setProductID(ratePlan.getProductID());
		subscription.setProductRatePlanID(ratePlan.getID());
		subscription.setDescription("Test Subscription");
		subscription.setState(SubscriptionState.Provisioned);
		subscription.setName("API Sub");
		subscription.setDescription("Test Description");
		subscription.setAccountID(account.getID());
		subscription.getPricingComponentValues().add(value);
		
		subscription = Subscription.create(subscription);

		assertNotNull(subscription);
		
		System.out.println(subscription.toString());
	}
	

	//@Test
	public void getActivePricingComponentValues() throws BillforwardException {
		//--Get Subscription by ID
		Subscription subscription = Subscription.getByID("SUB-105D5BFE-C9E2-4C9A-BFD0-70AF961E");

		PricingComponentValue[] values = subscription.getActivePricingComponentValues();
		
		for(PricingComponentValue value : values) {
			System.out.println(value.toString());
			
		}
	}
	

//	@Test
//	public void setActivePricingComponentValues() throws BillforwardException {
//		//--Get Subscription by ID
//		Subscription subscription = Subscription.getByID("SUB-105D5BFE-C9E2-4C9A-BFD0-70AF961E");
//
//		PricingComponentValue value = subscription.setActivePricingComponentValue("Users", 31);
//		
//		System.out.println(value.toString());
//	}
}
