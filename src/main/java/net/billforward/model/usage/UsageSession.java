package net.billforward.model.usage;

import java.util.Date;
import java.util.UUID;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.APIResponse;
import net.billforward.model.InsertableEntity;
import net.billforward.model.ResourcePath;
import net.billforward.model.UnitOfMeasure;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class UsageSession extends InsertableEntity<UsageSession> {
	@Expose protected UsageSession[] usageSessions;
	@Expose protected String subscriptionID;
	@Expose protected String organizationID;
	@Expose protected String sessionID;
	@Expose protected String pricingComponentID;
	@Expose protected String pricingComponentName;
	@Expose protected String uom;
	@Expose protected String description;
	@Expose protected long sessionix;
	@Expose protected Date start;
	@Expose protected Date stop;
	@Expose protected String state;
	@Expose protected String changedBy;
	@Expose protected Date created;	

	public String getSubscriptionID() {
		return subscriptionID;
	}

	public void setSubscriptionID(String subscriptionID) {
		this.subscriptionID = subscriptionID;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getPricingComponentID() {
		return pricingComponentID;
	}
	
	public void setPricingComponentID(String pricingComponentID_) {
		pricingComponentID = pricingComponentID_;
	}
	
	public String getPricingComponentName() {
		return pricingComponentName;
	}
	
	public void setPricingComponenName(String pricingComponentName_) {
		pricingComponentName = pricingComponentName_;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getStop() {
		return stop;
	}

	public void setStop(Date stop) {
		this.stop = stop;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public long getSessionix() {
		return sessionix;
	}

	public String getStateAsString() {
		return state;
	}

	public UsageState getState() {
		return UsageState.valueOf(state);
	}

	public String getChangedBy() {
		return changedBy;
	}

	public Date getCreated() {
		return created;
	}

	public static UsageSession startSession(UnitOfMeasure uom, String subscriptionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return startSession(uom, subscriptionID, UUID.randomUUID().toString().toUpperCase());
	}

	public static UsageSession startSession(String pricingComponentName, String subscriptionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return startSession(pricingComponentName, subscriptionID, UUID.randomUUID().toString().toUpperCase());
	}
	
	public static UsageSession startSessionByPricingComponentID(String pricingComponentID, String subscriptionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return startSessionByPricingComponentID(pricingComponentID, subscriptionID, UUID.randomUUID().toString().toUpperCase());
	}


	public static UsageSession startSessionByPricingComponentID(String pricingComponentID, String subscriptionID, String sessionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		
		UsageSession session = new UsageSession(client);
		session.setPricingComponentID(pricingComponentID);
		session.setSubscriptionID(subscriptionID);
		session.setSessionID(sessionID);
		
		
		UsageSession[] sessions = startSessions(new UsageSession[] { session });
		return sessions[0];
	}
	
	public static UsageSession startSession(String pricingComponentName, String subscriptionID, String sessionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		
		UsageSession session = new UsageSession(client);
		session.setPricingComponenName(pricingComponentName);
		session.setSubscriptionID(subscriptionID);
		session.setSessionID(sessionID);
		
		
		UsageSession[] sessions = startSessions(new UsageSession[] { session });
		return sessions[0];
	}
	
	public static UsageSession startSession(UnitOfMeasure uom, String subscriptionID, String sessionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		
		UsageSession session = new UsageSession(client);
		session.setUom(uom.getName());
		session.setSubscriptionID(subscriptionID);
		session.setSessionID(sessionID);
		
		
		UsageSession[] sessions = startSessions(new UsageSession[] { session });
		return sessions[0];
	}
	
	public static UsageSession[] startSessions(UsageSession[] sessions_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(new UsageSession(sessions_), ResourcePath(), "start");
	}

	public static UsageSession[] getSessionsForSubscription(String subscriptionID_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(subscriptionID_, ResourcePath());
	}

	public static UsageSession[] getActiveSessionsForSubscription(String subscriptionID_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByIDPostPath(subscriptionID_, "active", ResourcePath());
	}

	public static UsageSession stopSession(UnitOfMeasure uom, String subscriptionID, String sessionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		UsageSession session = new UsageSession(client);
		session.setUom(uom.getName());
		session.setSubscriptionID(subscriptionID);
		session.setSessionID(sessionID);
		
		
		UsageSession[] sessions = stopSession(new UsageSession[] { session });
		return sessions[0];
	}
	
	public static UsageSession[] stopSession(UsageSession[] sessions_) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		UsageSession tmpUsageSession = new UsageSession(sessions_);		
		return create(tmpUsageSession, ResourcePath(), "stop");
	}
	
	public UsageSession stop() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		UsageSession[] sessions = stopSession(new UsageSession[] { this });
		return sessions[0];
	}
	
	public UsageSession(UsageSession[] sessions) {
		this.usageSessions = sessions;
	}
	
	public UsageSession(BillForwardClient client_) {
		super(client_);		
	}
	
	protected UsageSession() {
		
	}
	
	protected static ResourcePath resourcePath;

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("usage-sessions", "usageSession",  new TypeToken<APIResponse<UsageSession>>() {}.getType());
	}
}
