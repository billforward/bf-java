package net.billforward.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class TaxationStrategy extends MutableEntity<TaxationStrategy> {
	@Expose protected String id;
	@Expose protected String versionID;
	@Expose protected String organizationID;
	@Expose protected String country;
	@Expose protected String province;
	@Expose protected String currency;
	@Expose protected String name;
	@Expose protected BigDecimal percentage;
	@Expose protected boolean defaultTaxationStrategy;
	@Expose protected Date validTill;
	@Expose protected Date validFrom;
	@Expose protected Boolean deleted;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;

	public String getID() {
		return id;
	}

	public String getCountryAsString() {
		return country;
	}
	
	public TaxationCountry getCountry() {
		return TaxationCountry.valueOf(country);
	}

	public void setCountry(TaxationCountry country) {
		this.country = country.toString();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCurrencyAsString() {
		return currency;
	}
	
	public Currency getCurrency() {
		return Currency.getInstance(currency);
	}

	public void setCurrency(Currency currency) {
		this.currency = currency.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}
	
	public boolean isDefaultTaxationStrategy() {
		return defaultTaxationStrategy;
	}

	public void setDefaultTaxationStrategy(boolean defaultTaxationStrategy) {
		this.defaultTaxationStrategy = defaultTaxationStrategy;
	}

	public Date getValidTill() {
		return validTill;
	}

	public void setValidTill(Date validTill) {
		this.validTill = validTill;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getVersionID() {
		return versionID;
	}

	public String getOrganizationID() {
		return organizationID;
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
		
	public static TaxationStrategy create(TaxationStrategy taxationStrategy) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(taxationStrategy, ResourcePath())[0];
	}	

	public static TaxationStrategy removeFromRatePlan(String ratePlanID, String taxationStrategyID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		String path = String.format("product-rate-plans/%s/tax/%s", ratePlanID, taxationStrategyID);
		return retireExplicitPath(path, ResourcePath());
	}
	
	public static TaxationStrategy addToRatePlan(String ratePlanID, String taxationStrategyID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		TaxationStrategy taxationStrategy = new TaxationStrategy();
		taxationStrategy.id = taxationStrategyID;
		
		String explicitPath = String.format("product-rate-plans/%s/tax", ratePlanID);				
		return createExplicitPath(taxationStrategy, ResourcePath(), explicitPath)[0];
	}
	
	public static TaxationStrategy[] getForProductRatePlan(String ratePlanID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		String path = String.format("product-rate-plans/%s/tax", ratePlanID);
		TaxationStrategy[] taxes = getAll(ResourcePath(), path);
		if(taxes == null) {
			return new TaxationStrategy[0];
		}
		return taxes;
	}

	public static TaxationStrategy getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		TaxationStrategy[] taxStrategies = getByID(ID, ResourcePath());
		return taxStrategies[0];
	}
	
	public static TaxationStrategy getByVersionID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		TaxationStrategy[] taxStrategies = getByID(ID, "version", ResourcePath());
		return taxStrategies[0];
	}
	
	public static TaxationStrategy[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}
	
	protected static ResourcePath resourcePath;
	
	public TaxationStrategy(BillForwardClient client_) {
		super(client_);		
	}
	
	public TaxationStrategy() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("taxation-strategies", "taxation-strategy",  new TypeToken<APIResponse<TaxationStrategy>>() {}.getType());
	}
	
	public enum TaxationCountry {
		UK,
		USA,
		Canada,
		Mexico,
		Brazil,
		Argentina,
		China,
		France,
		Germany,
		Ireland
	}

	public void setID(String id) {
		this.id = id;		
	}

	public TaxationStrategy retire() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return retire(this.versionID, "version", this.getResourcePath());
	}
}
