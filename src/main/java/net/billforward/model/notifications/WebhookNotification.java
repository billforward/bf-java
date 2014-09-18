package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.Webhook;

import com.google.gson.annotations.Expose;

public class WebhookNotification extends Notification {
	@Expose protected Webhook webHook;
	
	public WebhookNotification() {
		super();
	}
	
	public Webhook getWebhook() {
		return webHook;
	}

	@Override
	protected void buildEntity() {
		webHook = BillForwardClient.GSON.fromJson(this.entity, Webhook.class);
	}
}