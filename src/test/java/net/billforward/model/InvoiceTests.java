package net.billforward.model;

import static org.junit.Assert.assertEquals;
import net.billforward.exception.BillforwardException;
import net.billforward.model.Invoice.InvoiceState;
import net.billforward.model.amendments.InvoiceNextExecutionAttemptAmendment;
import net.billforward.model.amendments.InvoiceRecalculationAmendment;
import net.billforward.model.amendments.IssueInvoiceAmendment;

import org.junit.Test;

public class InvoiceTests  extends TestBase {
	@Test
	public void getByID() throws BillforwardException {
		Invoice invoice = Invoice.getByID("95CE489C-0867-4EB0-9086-C09E444B1249");
		
		assertEquals("95CE489C-0867-4EB0-9086-C09E444B1249", invoice.getID());
		System.out.println(invoice.toString());
	}

	@Test
	public void issueInvoice() throws BillforwardException {
		Invoice invoice = Invoice.getByID("B35BA5A6-8FBD-4604-B324-F72E6149C05C");
		
		//assertEquals("B35BA5A6-8FBD-4604-B324-F72E6149C05C", invoice.getID());
		
		IssueInvoiceAmendment issueInvoiceAmendment = invoice.issue().sync();
		
		System.out.println(issueInvoiceAmendment.toString());
	}
	
	@Test
	public void retryTakingPayment() throws BillforwardException {
		Invoice invoice = Invoice.getByID("F227B797-C521-457B-B419-F63854543416");

		InvoiceNextExecutionAttemptAmendment amendment = invoice.retryTakingPayment();
		
		amendment = amendment.sync();
		
		System.out.println(amendment.toString());
	}
	
	@Test
	public void recalcalculateInvoice() throws BillforwardException {
		Invoice invoice = Invoice.getByID("005FC58C-EC20-407A-BE54-0A36AD299C6A");		
		
		InvoiceRecalculationAmendment invoiceRecalculationAmendment = invoice.recalculate();
		
		invoiceRecalculationAmendment = invoiceRecalculationAmendment.sync();
		
		System.out.println(invoiceRecalculationAmendment.toString());
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
