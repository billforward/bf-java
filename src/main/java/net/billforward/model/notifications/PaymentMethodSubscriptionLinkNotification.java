package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.InvoiceLine;
import net.billforward.model.PaymentMethodSubscriptionLink;
import net.billforward.model.notifications.Notification.NotificationDomain;

import com.google.gson.annotations.Expose;

public class PaymentMethodSubscriptionLinkNotification extends Notification {
	@Expose protected PaymentMethodSubscriptionLink paymentMethodSubscriptionLink;
	
	public PaymentMethodSubscriptionLinkNotification() {
		super();
		this.domain = NotificationDomain.PaymentMethodSubscriptionLink.toString();
	}
	
	public PaymentMethodSubscriptionLink getPaymentMethodSubscriptionLink() {
		return paymentMethodSubscriptionLink;
	}

	@Override
	protected void buildEntity() {
		paymentMethodSubscriptionLink = BillForwardClient.GSON.fromJson(this.entity, PaymentMethodSubscriptionLink.class);
	}
}