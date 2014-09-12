package net.billforward.model;

import java.util.Date;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class DunningLine extends MutableEntity<DunningLine> {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected int attemptIx;
	@Expose protected int minutesDelay;
	@Expose protected boolean deleted;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;
	@Expose protected Organization organization;

	public String getID() {
		return id;
	}
	
	protected static ResourcePath resourcePath;
	
	public DunningLine(BillForwardClient client_) {
		super(client_);		
	}
	
	public DunningLine() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("dunning-lines", "dunning-line",  new TypeToken<APIResponse<DunningLine>>() {}.getType());
	}
}