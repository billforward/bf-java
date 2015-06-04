package net.billforward.model;

import java.io.FileNotFoundException;
import java.util.List;

import net.billforward.exception.BillforwardException;
import net.billforward.model.notifications.AmendmentNotification;
import net.billforward.model.notifications.FieldChange;
import net.billforward.model.notifications.Notification;
import net.billforward.model.notifications.NotificationHelper;

import org.junit.Test;

public class NotificationTests extends TestBase {
	@Test
	public void parseCancelledSubscription() throws BillforwardException, FileNotFoundException {
		String content = getResourceData("notificationExamples/subscription/cancelled.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	@Test
	public void parseProvisionedSubscription() throws BillforwardException, FileNotFoundException {
		String content = getResourceData("notificationExamples/subscription/provisioned.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	
	@Test
	public void parsePendingInvoice() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/invoice/Pending.json");
		
		Notification notification = NotificationHelper.parse(content);
		
		System.out.println(notification);
	}	

	@Test
	public void parseAccountUpdated() throws BillforwardException, FileNotFoundException {;
		
		String content = getResourceData("notificationExamples/account/updated.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}

	@Test
	public void cancellationAmendmentUpdated() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/amendments/CancellationAmendment.Updated.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}

	@Test
	public void cancellationAmendmentUpdated2() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/amendments/CancellationAmendment.Updated2.json");
		Notification notificaiton = NotificationHelper.parse(content);
		AmendmentNotification amendmentNotification = (AmendmentNotification)notificaiton;

		System.out.println(amendmentNotification);
		System.out.println(amendmentNotification.getAmendment());
	}

	@Test
	public void cancellationAmendmentUpdated3() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/amendments/CancellationAmendment.Updated3.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}

	@Test
	public void InvoiceRecalculationAmendment() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/amendments/InvoiceRecalculationAmendment.Created.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	
	@Test
	public void ServiceEndAmendmentCreated() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/amendments/ServiceEndAmendment.Created.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	
	@Test
	public void ServiceEndAmendmentPending() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/amendments/ServiceEndAmendment.Pending.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	
	@Test
	public void ServiceEndAmendmentUpdated() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/amendments/ServiceEndAmendment.Updated.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	
	@Test
	public void creditNoteCreated() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/creditnote/created.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	
	@Test
	public void subscriptioncancellationCancelled() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/subscriptioncancellation/cancelled.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		List<FieldChange> changes = notificaiton.getAuditFieldChanges();
		
		for(FieldChange change : changes) {
			System.out.println(change);
		}
		System.out.println(notificaiton);
	}

	@Test
	public void subscriptioncancellationCancelled2() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/subscriptioncancellation/cancelled2.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	
	@Test
	public void subscriptioncancellationPending() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/subscriptioncancellation/pending.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	
	@Test
	public void subscriptioncancellationPending2() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/subscriptioncancellation/pending2.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	
	@Test
	public void subscriptioncancellationPending3() throws BillforwardException, FileNotFoundException {
		
		String content = getResourceData("notificationExamples/subscriptioncancellation/pending3.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		
		System.out.println(notificaiton);
	}
}
