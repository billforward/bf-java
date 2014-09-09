package net.billforward.model.gateways;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import net.billforward.BillForwardClient;
import net.billforward.model.APIResponse;
import net.billforward.model.MutableEntity;
import net.billforward.model.Organization;
import net.billforward.model.ResourcePath;

public class Migration extends MutableEntity<Migration> {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String configurationID;
	@Expose protected String migrationState;
	@Expose protected String updated;
	@Expose protected String migrated;
	@Expose protected String changedBy;
	@Expose protected String created;
	@Expose protected Organization organization;

	public String getID() {
		return id;
	}
	
	protected static ResourcePath resourcePath;
	
	public Migration(BillForwardClient client_) {
		super(client_);		
	}
	
	protected Migration() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("undefined", "undefined",  new TypeToken<APIResponse<Migration>>() {}.getType());
	}
}
