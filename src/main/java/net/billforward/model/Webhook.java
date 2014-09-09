package net.billforward.model;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class Webhook extends MutableEntity<Webhook> {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String URL;
	@Expose protected String format;
	@Expose protected boolean ackEnabled;
	@Expose protected boolean metadata;
	@Expose protected boolean verified;
	@Expose protected String verificationID;
	@Expose protected boolean deleted;
	@Expose protected String changedBy;
	@Expose protected String created;
	@Expose protected WebhookSubscription[] webhookSubscriptions;
	
	public String getID() {
		return id;
	}
	
	
	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isAckEnabled() {
		return ackEnabled;
	}

	public void setAckEnabled(boolean ackEnabled) {
		this.ackEnabled = ackEnabled;
	}

	public boolean isMetadata() {
		return metadata;
	}

	public void setMetadata(boolean metadata) {
		this.metadata = metadata;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getVerificationID() {
		return verificationID;
	}

	public void setVerificationID(String verificationID) {
		this.verificationID = verificationID;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public WebhookSubscription[] getWebhookSubscriptions() {
		return webhookSubscriptions;
	}

	public void setWebhookSubscriptions(WebhookSubscription[] webhookSubscriptions) {
		this.webhookSubscriptions = webhookSubscriptions;
	}

	public String getId() {
		return id;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public String getCreated() {
		return created;
	}

	protected static ResourcePath resourcePath;
	
	public static Webhook create(Webhook webhook) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return webhook;
	}	
	
	public static Webhook getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Webhook[] webhooks = getByID(ID, ResourcePath());
		return webhooks[0];
	}
	
	public static Webhook[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}
	
	public Webhook(BillForwardClient client_) {
		super(client_);		
	}
	
	protected Webhook() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("webhooks", "webhook",  new TypeToken<APIResponse<Webhook>>() {}.getType());
	}
}