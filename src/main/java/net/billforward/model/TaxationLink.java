package net.billforward.model;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class TaxationLink extends MutableEntity<TaxationLink> {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String taxationStrategyID;
	@Expose protected String productRatePlanID;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;

	public String getID() {
		return id;
	}
	
	protected static ResourcePath resourcePath;
	
	public TaxationLink(BillForwardClient client_) {
		super(client_);		
	}
	
	public TaxationLink() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("taxation-links", "taxation-link",  new TypeToken<APIResponse<TaxationLink>>() {}.getType());
	}
}
