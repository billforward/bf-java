package net.billforward.model.notifications;

import java.util.ArrayList;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.model.BillingEntity;
import net.billforward.model.ResourcePath;
import net.billforward.model.gateways.GatewayTypeMapping;

import com.google.gson.annotations.Expose;

public class Notification extends BillingEntity {

	@Expose protected String id;
	@Expose protected String type;
	@Expose protected String domain;
	@Expose protected String action;
	@Expose protected String organizationID;
	@Expose protected String entityID;
	@Expose protected String entity;
	@Expose protected String created;
	
	protected Notification() {
		
	}
	
	public Notification(BillForwardClient client_) {
		super(client_);		
	}
	
	protected void buildEntity() {
		
	}
	
	@Override
	protected ResourcePath getResourcePath() {
		return null;
	}
	
	public static GatewayTypeMapping[] getTypeMappings() {
		List<GatewayTypeMapping> typeMappings = new ArrayList<GatewayTypeMapping>();
		
		/* Fully Support Notification types */
		typeMappings.add(new GatewayTypeMapping(SubscripitonNotification.class, NotificationDomain.Subscription.toString()));
		typeMappings.add(new GatewayTypeMapping(InvoiceNotification.class, NotificationDomain.Invoice.toString()));
		typeMappings.add(new GatewayTypeMapping(AmendmentNotification.class, NotificationDomain.Amendment.toString()));
		typeMappings.add(new GatewayTypeMapping(InvoiceLineNotification.class, NotificationDomain.InvoiceLine.toString()));
		typeMappings.add(new GatewayTypeMapping(AccountNotification.class, NotificationDomain.Account.toString()));
		typeMappings.add(new GatewayTypeMapping(WebhookNotification.class, NotificationDomain.Webhook.toString()));
		

		/* Catch all for other things */
		typeMappings.add(new GatewayTypeMapping(Notification.class));
		
		return typeMappings.toArray(new GatewayTypeMapping[]{});
	}
	
	public enum NotificationDomain {
		Notification,
		Organization,
		OrganizationGateway,
		Product,
		User,
		Subscription,
		Profile,
		ProductRatePlan,
		Client,
		Invoice,
		PricingComponentValue,
		Account,
		PricingComponentValueChange,
		PricingComponentTier,
		PricingComponent,
		PricingCalculation,
		CouponDefinition,
		CouponInstance,
		CouponModifier,
		CouponRule,
		CouponBookDefinition,
		CouponBook,
		InvoiceLine,
		Webhook,
		SubscriptionCancellation,
		NotificationSnapshot,
		InvoicePayment,
		Payment,
		PaymentMethod,
		PaymentMethodSubscriptionLink,
		DunningLine,
		CybersourceToken,
		Card,
		Alias,
		PaypalSimplePaymentReconciliation,
		FreePaymentReconciliation,
		LocustworldPaymentReconciliation,
		CouponInstanceExistingValue,
		TaxLine,
		TaxationStrategy,
		TaxationLink,
		Address,
		AmendmentPriceNTime,
		Authority,
		UnitOfMeasure,
		SearchResult,
		Amendment,
		AuditLog,
		Password,
		Username,
		FixedTermDefinition,
		FixedTerm,
		Refund,
		CreditNote,
		Receipt,
		AmendmentCompoundConstituent,
		APIConfiguration,
		StripeToken,
		BraintreeToken,
		BalancedToken,
		AuthorizeNetToken,
		PaypalToken,
		GatewayRevenue,
		Migration,
		AdhocSubscription,
		SubscriptionCharge,
		Verification,
		UsageRoundingStrategies,
		Unknown;
	}
	
	public enum NotificationAction {
		Accept,
		Active,
		AwaitingPayment,
		AwaitingRefund,
		Cancelled,
		Completed,
		Created,
		Error,
		Expired,
		Failed,
		NeedsAmendments,
		Paid,
		Pending,
		Provisioned,
		Refunded,
		Reject,
		Trial,
		Unknown,
		Unpaid,
		Updated,
		Voided;
	}
}
