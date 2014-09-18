package net.billforward.model;

import java.io.FileNotFoundException;

import net.billforward.exception.BillforwardException;
import net.billforward.model.Invoice.InvoiceState;
import net.billforward.model.notifications.InvoiceNotification;
import net.billforward.model.notifications.Notification;
import net.billforward.model.notifications.NotificationHelper;

import org.junit.Test;

public class NotificationTests extends TestBase {
	@Test
	public void parseProvisionedSubscription() throws BillforwardException, FileNotFoundException {
		String content = getResourceData("notificationExamples/subscription/Provisioned.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
	
	@Test
	public void parsePendingInvoice() throws BillforwardException, FileNotFoundException {;
		
		String content = getResourceData("notificationExamples/invoice/Pending.json");
		
		Notification notification = NotificationHelper.parse(content);
		InvoiceNotification invoiceNotification = (InvoiceNotification)notification;
		if(invoiceNotification.getInvoice().getState() == InvoiceState.Pending) {
			invoiceNotification.getInvoice().issue();
		}
		
		System.out.println(notification);
	}	

	@Test
	public void parseAccountUpdated() throws BillforwardException, FileNotFoundException {;
		
		String content = getResourceData("notificationExamples/account/Updated.json");
		Notification notificaiton = NotificationHelper.parse(content);
		
		System.out.println(notificaiton);
	}
}
