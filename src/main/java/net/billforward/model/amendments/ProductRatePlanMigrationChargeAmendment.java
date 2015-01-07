package net.billforward.model.amendments;

import java.util.ArrayList;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.APIResponse;
import net.billforward.model.InvoicingType;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class ProductRatePlanMigrationChargeAmendment extends Amendment {
	@SerializedName("@type")
	@Expose protected String amendmentType = AmendmentType.ProductRatePlanMigrationChargeAmendment.toString();
	@Expose String productRatePlanID;
	@Expose InvoicingType invoicingType = InvoicingType.Immediate ;
	@Expose List<PricingComponentValueMigrationChargeAmendmentMapping> mappings = new ArrayList<PricingComponentValueMigrationChargeAmendmentMapping>();
	
	
	public String getProductRatePlanID() {
		return productRatePlanID;
	}

	public void setProductRatePlanID(String productRatePlanID) {
		this.productRatePlanID = productRatePlanID;
	}

	public InvoicingType getInvoicingType() {
		return invoicingType;
	}

	public void setInvoicingType(InvoicingType invoicingType) {
		this.invoicingType = invoicingType;
	}

	public List<PricingComponentValueMigrationChargeAmendmentMapping> getMappings() {
		return mappings;
	}

	public void addapping(PricingComponentValueMigrationChargeAmendmentMapping mapping) {
		this.mappings.add(mapping);
	}

	public ProductRatePlanMigrationChargeAmendment(BillForwardClient client_) {
		super(client_);
	}
	
	public ProductRatePlanMigrationChargeAmendment() {
		super();
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath resourcePath;
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
		
	static {
		resourcePath = new ResourcePath("amendments", "amendments",  new TypeToken<APIResponse<ProductRatePlanMigrationChargeAmendment>>() {}.getType());
	}

	public static ProductRatePlanMigrationChargeAmendment create(ProductRatePlanMigrationChargeAmendment amendment) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(amendment, ResourcePath())[0];
	}

	public void addMapping(String pricingComponentID, int value) {
		PricingComponentValueMigrationChargeAmendmentMapping mapping = new PricingComponentValueMigrationChargeAmendmentMapping();
		mapping.setPricingComponentID(pricingComponentID);
		mapping.setValue(value);
		
		
		this.addapping(mapping);
	}
}
