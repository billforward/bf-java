package net.billforward.model;

import java.util.Date;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class WebhookSubscription extends BillingEntity {
	@Expose protected String id;
	@Expose protected String webhookID;
	@Expose protected String organizationID;
	@Expose protected String domain;
	@Expose protected String action;
	@Expose protected boolean deleted;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;
	
	public String getID() {
		return id;
	}
	
	protected static ResourcePath resourcePath;
	
	public WebhookSubscription(BillForwardClient client_) {
		super(client_);		
	}
	
	public WebhookSubscription() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("undefined", "undefined",  new TypeToken<APIResponse<WebhookSubscription>>() {}.getType());
	}
}