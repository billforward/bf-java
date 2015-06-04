package net.billforward.model.notifications;

import com.google.gson.annotations.Expose;

import net.billforward.BillForwardClient;
import net.billforward.model.Subscription;
import net.billforward.model.notifications.Notification.NotificationDomain;

public class SubscripitonNotification extends Notification {
	@Expose protected Subscription subscription;
	
	public SubscripitonNotification() {
		super();
		this.domain = NotificationDomain.Subscription.toString();
	}
	
	public Subscription getSubscripton() {
		return subscription;
	}

	@Override
	protected void buildEntity() {
		super.buildEntity();
		subscription = BillForwardClient.GSON_NOTIFICATION_ENTITY.fromJson(this.entity, Subscription.class);
	}
}
