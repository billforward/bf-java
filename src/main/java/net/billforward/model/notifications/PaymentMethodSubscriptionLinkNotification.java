package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.InvoiceLine;
import net.billforward.model.PaymentMethodSubscriptionLink;

import com.google.gson.annotations.Expose;

public class PaymentMethodSubscriptionLinkNotification extends Notification {
	@Expose protected PaymentMethodSubscriptionLink paymentMethodSubscriptionLink;
	
	public PaymentMethodSubscriptionLinkNotification() {
		super();
	}
	
	public PaymentMethodSubscriptionLink getPaymentMethodSubscriptionLink() {
		return paymentMethodSubscriptionLink;
	}

	@Override
	protected void buildEntity() {
		paymentMethodSubscriptionLink = BillForwardClient.GSON.fromJson(this.entity, PaymentMethodSubscriptionLink.class);
	}
}