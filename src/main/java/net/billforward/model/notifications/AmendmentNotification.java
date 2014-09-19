package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.amendments.Amendment;

import com.google.gson.annotations.Expose;

public class AmendmentNotification extends Notification {
	@Expose protected Amendment amendment;
	
	public AmendmentNotification() {
		super();
		this.domain = NotificationDomain.Amendment.toString();
	}
	
	public Amendment getInvoice() {
		return amendment;
	}

	@Override
	protected void buildEntity() {
		amendment = BillForwardClient.GSON.fromJson(this.entity, Amendment.class);
	}
}