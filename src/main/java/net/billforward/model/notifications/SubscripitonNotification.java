package net.billforward.model.notifications;

import com.google.gson.annotations.Expose;

import net.billforward.BillForwardClient;
import net.billforward.model.Subscription;

public class SubscripitonNotification extends Notification {
	@Expose protected Subscription subscription;
	
	public SubscripitonNotification() {
		super();
	}
	
	public Subscription getSubscripton() {
		return subscription;
	}

	@Override
	protected void buildEntity() {
		subscription = BillForwardClient.GSON.fromJson(this.entity, Subscription.class);
	}
}
