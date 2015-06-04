package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.Invoice;

import com.google.gson.annotations.Expose;

public class InvoiceNotification extends Notification {
	@Expose protected Invoice invoice;
	
	public InvoiceNotification() {
		super();
		this.domain = NotificationDomain.Invoice.toString();
	}
	
	public Invoice getInvoice() {
		return invoice;
	}

	@Override
	protected void buildEntity() {
		super.buildEntity();
		invoice = BillForwardClient.GSON_NOTIFICATION_ENTITY.fromJson(this.entity, Invoice.class);
	}
}
