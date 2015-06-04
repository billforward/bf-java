package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.SubscriptionCancellation;

import com.google.gson.annotations.Expose;

public class SubscriptionCancellationNotification extends Notification {
	@Expose protected SubscriptionCancellation subscriptionCancellation;
	
	public SubscriptionCancellationNotification() {
		super();
		this.domain = NotificationDomain.SubscriptionCancellation.toString();
	}
	
	public SubscriptionCancellation getSubscriptionCancellation() {
		return subscriptionCancellation;
	}

	@Override
	protected void buildEntity() {
		super.buildEntity();
		
		subscriptionCancellation = BillForwardClient.GSON_NOTIFICATION_ENTITY.fromJson(this.entity, SubscriptionCancellation.class);
	}
}

