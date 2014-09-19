package net.billforward.model;



import org.junit.Test;

import net.billforward.exception.BillforwardException;
import net.billforward.model.Invoice.InvoiceState;
import net.billforward.model.amendments.*;
import net.billforward.model.amendments.CancellationAmendment.ServiceEndState;

public class AmendmentTests extends TestBase {
	//@Test
	public void InvoiceNextExecutionAttemptAmendment() throws BillforwardException {

		InvoiceNextExecutionAttemptAmendment amendment = new InvoiceNextExecutionAttemptAmendment();
		Subscription subscription = Subscription.getByID("457C2D7E-C695-4169-BE2B-702253B11FE5");
		Invoice invoice = Invoice.getByID("6605BEA5-3772-4B0D-A991-EC88C2AEE46D");
		
		amendment.setSubscriptionID(subscription.getID());
		amendment.setInvoiceID(invoice.getID());		
		//amendment.setNextExecutionAttempt(new Date());
		amendment = InvoiceNextExecutionAttemptAmendment.create(amendment);
		
		System.out.println(amendment);
	}	
	
	///@Test
	public void IssueInvoiceAmendment() throws BillforwardException {
		Subscription subscription = Subscription.getByID("457C2D7E-C695-4169-BE2B-702253B11FE5");
		Invoice invoice = Invoice.getByID("6605BEA5-3772-4B0D-A991-EC88C2AEE46D");
		
		IssueInvoiceAmendment amendment = new IssueInvoiceAmendment();
		amendment.setSubscriptionID(subscription.getID());
		amendment.setInvoiceID(invoice.getID());
		amendment = IssueInvoiceAmendment.create(amendment);
		
		System.out.println(amendment);
	}
	
	//@Test
	public void InvoiceRecalculationAmendment() throws BillforwardException {
		Subscription subscription = Subscription.getByID("457C2D7E-C695-4169-BE2B-702253B11FE5");
		Invoice invoice = Invoice.getByID("6605BEA5-3772-4B0D-A991-EC88C2AEE46D");
		
		InvoiceRecalculationAmendment amendment = new InvoiceRecalculationAmendment();
		amendment.setSubscriptionID(subscription.getID());
		amendment.setInvoiceID(invoice.getID());
		amendment.setNewInvoiceState(InvoiceState.Paid);
		amendment = InvoiceRecalculationAmendment.create(amendment);
		
		
		System.out.println(amendment);
	}

	//@Test
	public void CancellationAmendment() throws BillforwardException {
		Subscription subscription = Subscription.getByID("1441699F-DF36-4B24-8B59-6DC4271E0BD4");
				
		CancellationAmendment amendment = new CancellationAmendment();
		amendment.setSubscriptionID(subscription.getID());
		amendment.setServiceEnd(ServiceEndState.Immediate);
		amendment = CancellationAmendment.create(amendment);
		
		
		System.out.println(amendment);
	}

	//@Test
	public void AmendmentDiscardAmendment() throws BillforwardException {				
		AmendmentDiscardAmendment amendment = new AmendmentDiscardAmendment();
		amendment.setAmendmentToDiscardID("588BA730-0EDD-41F0-9C49-AD5ABCC7823F");
		amendment = AmendmentDiscardAmendment.create(amendment);		
		
		System.out.println(amendment);
	}

	//@Test
	public void CancelASub() throws BillforwardException {				
		Subscription subscription = Subscription.getByID("A58E586C-3F55-4422-AEA3-B405D7CF4DD6");
		
		Amendment amendment = subscription.cancelImmediately();
		
		System.out.println(amendment);
	}
	
	@Test
	public void issueInvoice() throws BillforwardException {				
		Invoice invoice = Invoice.getByID("04116AE4-2256-4B7A-8C25-650048D15269");
		
		Amendment amendment = invoice.issue();
		
		System.out.println(amendment);
	}
}
