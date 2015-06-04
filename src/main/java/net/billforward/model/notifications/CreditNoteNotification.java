package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.CreditNote;

import com.google.gson.annotations.Expose;

public class CreditNoteNotification  extends Notification {
	@Expose protected CreditNote creditNote;
	
	public CreditNoteNotification() {
		super();
		this.domain = NotificationDomain.CreditNote.toString();
	}
	
	public CreditNote getInvoiceLine() {
		return creditNote;
	}

	@Override
	protected void buildEntity() {
		super.buildEntity();
		creditNote = BillForwardClient.GSON_NOTIFICATION_ENTITY.fromJson(this.entity, CreditNote.class);
	}
}