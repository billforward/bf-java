package net.billforward.model.gateways;

import java.util.ArrayList;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.model.MutableEntity;

import com.google.gson.annotations.Expose;

public abstract class APIConfiguration extends MutableEntity<APIConfiguration> {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String type;
	@Expose protected String environment;
	@Expose protected String deleted;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;
	@Expose protected Migration[] migrations;

	public String getID() {
		return id;
	}
	
	public GatewayEnvironment getEnvironment() {
		return GatewayEnvironment.valueOf(environment);
	}

	public void setEnvironment(GatewayEnvironment environment) {
		this.environment = environment.toString();
	}

	public APIConfiguration(BillForwardClient client_) {
		super(client_);		
	}
	
	protected APIConfiguration() {
		
	}
	
	public static GatewayTypeMapping[] getTypeMappings() {
		List<GatewayTypeMapping> typeMappings = new ArrayList<GatewayTypeMapping>();
		typeMappings.add(new GatewayTypeMapping(AuthorizeNetConfiguration.class, AuthorizeNetConfiguration.class.getSimpleName()));
		typeMappings.add(new GatewayTypeMapping(BalancedConfiguration.class, BalancedConfiguration.class.getSimpleName()));
		typeMappings.add(new GatewayTypeMapping(BraintreeConfiguration.class, BraintreeConfiguration.class.getSimpleName()));
		typeMappings.add(new GatewayTypeMapping(BraintreeMerchantAccount.class, BraintreeMerchantAccount.class.getSimpleName()));
		typeMappings.add(new GatewayTypeMapping(CybersourceConfiguration.class, CybersourceConfiguration.class.getSimpleName()));
		typeMappings.add(new GatewayTypeMapping(PaypalConfiguration.class, PaypalConfiguration.class.getSimpleName()));
		typeMappings.add(new GatewayTypeMapping(StripeConfiguration.class, StripeConfiguration.class.getSimpleName()));
		
		return typeMappings.toArray(new GatewayTypeMapping[]{});
	}
}