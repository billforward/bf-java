package net.billforward.model;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.usage.UsageRoundingStrategy;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class RatePlan extends MutableEntity<RatePlan> {	
	@Expose protected String id;
	@Expose protected String productID;
	@Expose protected String name;
	@Expose protected String organizationID;
	@Expose protected String currency;
	@Expose protected String taxStatus;
	@Expose protected String proRataMode;
	@Expose protected Boolean localisedTax;
	@Expose protected String validFrom;
	@Expose protected String validTill;
	@Expose protected String status;
	@Expose protected Boolean deleted;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;	
	
	/* Concrete objects returned */
	@Expose protected Product product;
	@Expose protected List<PricingComponent> pricingComponents = new ArrayList<PricingComponent>();
	@Expose protected List<TaxationLink> taxation = new ArrayList<TaxationLink>();
	@Expose protected List<FixedTermDefinition> fixedTermDefinitions = new ArrayList<FixedTermDefinition>();
	@Expose protected UsageRoundingStrategy strategy;
	
	public String getID() {
		return id;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public String getCurrencyAsString() {
		return currency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency.toString();
	}

	public TaxStatus getTaxStatus() {
		return TaxStatus.valueOf(taxStatus);
	}
	public String getTaxStatusAsString() {
		return taxStatus;
	}

	public void setTaxStatus(TaxStatus taxStatus) {
		this.taxStatus = taxStatus.toString();
	}

	public String getProRataMode() {
		return proRataMode;
	}

	public void setProRataMode(String proRataMode) {
		this.proRataMode = proRataMode;
	}

	public Boolean getLocalisedTax() {
		return localisedTax;
	}

	public void setLocalisedTax(Boolean localisedTax) {
		this.localisedTax = localisedTax;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTill() {
		return validTill;
	}

	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public String getCreated() {
		return created;
	}

	public Product getProduct() {
		return product;
	}

	public List<PricingComponent> getPricingComponents() {
		return pricingComponents;
	}	
	
	public UsageRoundingStrategy getUsageRoundingStrategy() {
		return strategy;
	}

	public void setUsageRoundingStrategy(UsageRoundingStrategy strategy) {
		this.strategy = strategy;
	}

	public PricingComponent[] getLatestPricingComponents() {
		List<PricingComponent> latestPricingComponents = new ArrayList<PricingComponent>();
		
		for(PricingComponent pricingComponent : pricingComponents) {
			if(pricingComponent.validTill == null) {
				latestPricingComponents.add(pricingComponent);
			}
		}
		return latestPricingComponents.toArray(new PricingComponent[]{});
	}

	public RatePlan retire() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return retire(this.getID(), ResourcePath());
	}
	
	public static RatePlan create(RatePlan ratePlan) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(ratePlan, ResourcePath())[0];
	}
				
	public static RatePlan getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		RatePlan[] rateplans = getByID(ID, ResourcePath());
		return rateplans[0];		
	}


	public static RatePlan[] getByProductID(String productID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(productID, "product", ResourcePath());
	}
	
	public static RatePlan[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}
	
	protected static ResourcePath resourcePath;
	
	public RatePlan(BillForwardClient client_) {
		super(client_);		
	}
	
	public RatePlan() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("product-rate-plans", "productRatePlan",  new TypeToken<APIResponse<RatePlan>>() {}.getType());
	}
	
	public enum TaxStatus {
		inclusive,
		exclusive
	}
	
	public enum ProRataMode {
		None,
		WithCoupon,
		WithoutCoupon,
		Full
	}
}
