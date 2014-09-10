package net.billforward.model;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class Alias extends BillingEntity {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String alias;
	@Expose protected boolean deleted;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;
	
	public String getID() {
		return id;
	}
	
	protected static ResourcePath resourcePath;
	
	public Alias(BillForwardClient client_) {
		super(client_);		
	}
	
	public Alias() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("alias", "alias",  new TypeToken<APIResponse<Alias>>() {}.getType());
	}
}