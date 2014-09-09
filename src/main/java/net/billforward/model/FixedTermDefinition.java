package net.billforward.model;

import java.math.BigDecimal;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class FixedTermDefinition extends MutableEntity<TaxationStrategy> {
	@Expose protected String id;
	@Expose protected String organizationID;
	@Expose protected String productRatePlanID;
	@Expose protected String expiryBehaviour;
	@Expose protected int firstTermPeriods;
	@Expose protected int subsequentTermPeriods;
	@Expose protected String productRatePlanAsOfTime;
	@Expose protected BigDecimal uplift;
	@Expose protected Boolean deleted;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;

	public String getID() {
		return id;
	}
	
	protected static ResourcePath resourcePath;
	
	public FixedTermDefinition(BillForwardClient client_) {
		super(client_);		
	}
	
	protected FixedTermDefinition() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("fixed-term-definitions", "fixed-term-definition",  new TypeToken<APIResponse<FixedTermDefinition>>() {}.getType());
	}
}
