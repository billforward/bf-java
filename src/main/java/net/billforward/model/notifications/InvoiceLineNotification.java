package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.InvoiceLine;

import com.google.gson.annotations.Expose;

public class InvoiceLineNotification extends Notification {
	@Expose protected InvoiceLine invoiceLine;
	
	public InvoiceLineNotification() {
		super();
		this.domain = NotificationDomain.InvoiceLine.toString();
	}
	
	public InvoiceLine getInvoiceLine() {
		return invoiceLine;
	}

	@Override
	protected void buildEntity() {
		invoiceLine = BillForwardClient.GSON_NOTIFICATION_ENTITY.fromJson(this.entity, InvoiceLine.class);
	}
}