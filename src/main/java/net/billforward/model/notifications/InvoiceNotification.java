package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.Invoice;

import com.google.gson.annotations.Expose;

public class InvoiceNotification extends Notification {
	@Expose protected Invoice invoice;
	
	public InvoiceNotification() {
		super();
	}
	
	public Invoice getInvoice() {
		return invoice;
	}

	@Override
	protected void buildEntity() {
		invoice = BillForwardClient.GSON.fromJson(this.entity, Invoice.class);
	}
}
