package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.Webhook;
import net.billforward.model.notifications.Notification.NotificationDomain;

import com.google.gson.annotations.Expose;

public class WebhookNotification extends Notification {
	@Expose protected Webhook webHook;
	
	public WebhookNotification() {
		super();
		this.domain = NotificationDomain.Webhook.toString();
	}
	
	public Webhook getWebhook() {
		return webHook;
	}

	@Override
	protected void buildEntity() {
		super.buildEntity();
		webHook = BillForwardClient.GSON_NOTIFICATION_ENTITY.fromJson(this.entity, Webhook.class);
	}
}