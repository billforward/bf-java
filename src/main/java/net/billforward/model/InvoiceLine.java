package net.billforward.model;

import java.math.BigDecimal;
import java.util.Date;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class InvoiceLine extends BillingEntity {
	@Expose protected String id;
	@Expose protected String invoiceID;
	@Expose protected String unitOfMeasureID;
	@Expose protected String organizationID;
	@Expose protected String name;
	@Expose protected String description;
	@Expose protected String calculation;
	@Expose protected BigDecimal cost;
	@Expose protected BigDecimal tax;
	@Expose protected int componentValue;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;	
	
	protected UnitOfMeasure unitOfMeasure;
	
	public String getID() {
		return id;
	}

	public String getInvoiceID() {
		return invoiceID;
	}

	public String getUnitOfMeasureID() {
		return unitOfMeasureID;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCalculation() {
		return calculation;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public int getComponentValue() {
		return componentValue;
	}

	public Date getUpdated() {
		return updated;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public Date getCreated() {
		return created;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	protected static ResourcePath resourcePath;

	protected InvoiceLine(BillForwardClient client_) {
		super(client_);		
	}
	
	protected InvoiceLine() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("none", "none",  new TypeToken<APIResponse<InvoiceLine>>() {}.getType());
	}

}
