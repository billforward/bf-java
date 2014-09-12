package net.billforward.model;

import java.util.Date;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class OrganizationGateway extends MutableEntity<OrganizationGateway> {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String gateway;
	@Expose protected String enabled;
	@Expose protected String deleted;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;	
	
	protected static ResourcePath resourcePath;
	
	public OrganizationGateway(BillForwardClient client_) {
		super(client_);		
	}
	
	public OrganizationGateway() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("undefined", "undefined",  new TypeToken<APIResponse<OrganizationGateway>>() {}.getType());
	}
}
