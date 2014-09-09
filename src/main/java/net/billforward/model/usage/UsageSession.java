package net.billforward.model.usage;

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
	@Expose protected String uom;
	@Expose protected String description;
	@Expose protected int sessionix;
	@Expose protected String start;
	@Expose protected String stop;
	@Expose protected String state;
	@Expose protected String changedBy;
	@Expose protected String created;	

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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public int getSessionix() {
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

	public String getCreated() {
		return created;
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
