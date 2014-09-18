package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.InvoiceLine;

import com.google.gson.annotations.Expose;

public class InvoiceLineNotification extends Notification {
	@Expose protected InvoiceLine invoiceLine;
	
	public InvoiceLineNotification() {
		super();
	}
	
	public InvoiceLine getInvoiceLine() {
		return invoiceLine;
	}

	@Override
	protected void buildEntity() {
		invoiceLine = BillForwardClient.GSON.fromJson(this.entity, InvoiceLine.class);
	}
}