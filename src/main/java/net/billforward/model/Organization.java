package net.billforward.model;

import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.gateways.APIConfiguration;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class Organization extends MutableEntity<Organization> {
	@Expose protected String id;
	@Expose protected String name;
	@Expose protected boolean deleted;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;
	@Expose protected List<Webhook> webhooks;
	@Expose protected List<DunningLine> dunningLines;
	@Expose protected List<APIConfiguration> apiConfigurations;
	@Expose protected List<TaxationStrategy> taxationStrategies;
	@Expose protected List<OrganizationGateway> organizationGateways;

	public String getID() {		
		return id;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public String getUpdated() {
		return updated;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public String getCreated() {
		return created;
	}

	public List<Webhook> getWebhooks() {
		return webhooks;
	}

	public List<DunningLine> getDunningLines() {
		return dunningLines;
	}

	public List<APIConfiguration> getApiConfigurations() {
		return apiConfigurations;
	}

	public List<TaxationStrategy> getTaxationStrategies() {
		return taxationStrategies;
	}

	public List<OrganizationGateway> getOrganizationGateways() {
		return organizationGateways;
	}
	
	public static Organization getMine() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Organization[] orgs = getByID("mine", ResourcePath());
		return orgs[0];
	}
	
	public static Organization getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Organization[] orgs = getByID(ID, ResourcePath());
		return orgs[0];
	}
	
	public static Webhook[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}


	protected static ResourcePath resourcePath;
	
	protected Organization(BillForwardClient client_) {
		super(client_);		
	}
	
	protected Organization() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("organizations", "organization",  new TypeToken<APIResponse<Organization>>() {}.getType());
	}
}