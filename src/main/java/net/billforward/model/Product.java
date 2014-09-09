package net.billforward.model;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class Product extends MutableEntity<Product> {	
	@Expose protected String id;
	@Expose protected String accountID;
	@Expose protected String organizationID;
	@Expose protected String name;
	@Expose protected String description;
	@Expose protected String durationPeriod;
	@Expose protected int duration;
	@Expose protected String trial;
	@Expose protected String trialPeriod;
	@Expose protected String productType;
	@Expose protected String state = "prod";
	@Expose protected Boolean primaryAddress;
	@Expose protected Boolean deleted = false;
	@Expose protected Boolean startDate;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;	
	
	public String getID() {
		return id;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductPeriod getDurationPeriod() {
		return ProductPeriod.valueOf(durationPeriod);
	}
	
	public String getDurationPeriodAsString() {
		return durationPeriod;
	}

	public void setDurationPeriod(ProductPeriod durationPeriod) {
		this.durationPeriod = durationPeriod.toString();
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getTrial() {
		return trial;
	}

	public void setTrial(String trial) {
		this.trial = trial;
	}

	public String getTrialPeriod() {
		return trialPeriod;
	}

	public void setTrialPeriod(String trialPeriod) {
		this.trialPeriod = trialPeriod;
	}

	public ProductType getProductType() {
		return ProductType.valueOf(productType);
	}
	public String getProductTypeAsString() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType.name();
	}

	public String getState() {
		return state;
	}

	public Boolean getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(Boolean primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Boolean getStartDate() {
		return startDate;
	}

	public void setStartDate(Boolean startDate) {
		this.startDate = startDate;
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

	protected static ResourcePath resourcePath;

	public Product(BillForwardClient client_) {
		super(client_);		
	}
	
	protected Product() {
		
	}


	public Product retire() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return retire(this.getID(), ResourcePath());
	}
	
	public static Product create(Product product) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(product, ResourcePath())[0];
	}
	
	public static Product getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Product[] products = getByID(ID, ResourcePath());
		return products[0];
	}
	
	public static Product[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("products", "product",  new TypeToken<APIResponse<Product>>() {}.getType());
	}

	public enum DurationPeriod {
		minutes,
		days,
		months,
		years
	}

	public enum ProductType implements StringEnum {
		nonrecurring("non-recurring"), recurring("recurring");
	
		private final String name;
	
		private ProductType(String s) {
			name = s;
		}
	
		public boolean equalsName(String otherName) {
			return (otherName != null) && name.equals(otherName);
		}
	
		public String toString() {
			return name;
		}
	}

	public enum ProductPeriod {
		minutes,
		days,
		months,
		years
	}
}
