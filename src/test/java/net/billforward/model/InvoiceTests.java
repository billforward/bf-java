package net.billforward.model;

import static org.junit.Assert.assertEquals;
import net.billforward.exception.BillforwardException;
import net.billforward.model.Invoice.InvoiceState;

import org.junit.Test;

public class InvoiceTests  extends TestBase {
	@Test
	public void getByID() throws BillforwardException {
		Invoice invoice = Invoice.getByID("95CE489C-0867-4EB0-9086-C09E444B1249");
		
		assertEquals("95CE489C-0867-4EB0-9086-C09E444B1249", invoice.getID());
		System.out.println(invoice.toString());
	}

	@Test
	public void getBySubscriptionID() throws BillforwardException {
		//--Get Subscription by ID
		Subscription subscription = Subscription.getByID("79AA1229-4E97-4368-A513-8CFE8C0BDD95");
		
		Invoice[] invoices = Invoice.getBySubscriptionID(subscription.getID());
		
		
		for(Invoice invoice : invoices) {
			assertEquals(subscription.getID(), invoice.getSubscriptionID());	
			System.out.println(invoice.toString());
		}		
	}
	
	@Test
	public void getByState() throws BillforwardException {
		//--Get Invoice by State
		InvoiceState invoiceState = InvoiceState.Unpaid;
		
		Invoice[] invoices = Invoice.getByState(invoiceState);
		
		for(Invoice invoice : invoices) {
			assertEquals(invoiceState, invoice.getState());		
			System.out.println(invoice.toString());	
		}
	}
}
