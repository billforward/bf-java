package net.billforward.model;

import java.util.ArrayList;
import java.util.List;

import net.billforward.BillForwardClient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class PricingComponent extends MutableEntity<PricingComponent> {
	
	@Expose protected String id;
	@SerializedName("@type")
	@Expose protected String type;
	@Expose protected String versionID;
	@Expose protected String organizationID;
	@Expose protected String description;
	@Expose protected String productRatePlanID;
	@Expose protected String unitOfMeasureID;
	@Expose protected String validTill;
	@Expose protected String chargeType;
	@Expose protected String chargeModel;
	@Expose protected String upgradeMode;
	@Expose protected String downgradeMode;
	@Expose protected int defaultQuantity;
	@Expose protected int minQuantity;
	@Expose protected int maxQuantity;	
	@Expose protected String validFrom;	
	@Expose protected String name;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;
	@Expose protected List<PricingComponentTier> tiers = new ArrayList<PricingComponentTier>();
	@Expose protected UnitOfMeasure unitOfMeasure;
	//sage-rounding-strategies () 	

	public String getID() {
		return id;
	}

	public String getVersionID() {
		return versionID;
	}

	public String getTypeAsString() {
		return type;
	}

	public PricingComponentType getType() {
		return PricingComponentType.valueOf(type);
	}

	public void setType(PricingComponentType type) {
		this.type = type.toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitOfMeasureID() {
		return unitOfMeasureID;
	}

	public void setUnitOfMeasureID(String unitOfMeasureID) {
		this.unitOfMeasureID = unitOfMeasureID;
	}

	public String getValidTill() {
		return validTill;
	}

	public void setValidTill(String validTill) {
		this.validTill = validTill;
	}
	
	public String getChargeTypeAsString() {
		return chargeType;
	}
	
	public PricingComponentChargeType getChargeType() {
		return PricingComponentChargeType.valueOf(chargeType);
	}

	public void setChargeType(PricingComponentChargeType chargeType) {
		this.chargeType = chargeType.toString();
	}

	public PricingComponentChargeModel getChargeModel() {
		return PricingComponentChargeModel.valueOf(chargeModel);
	}
	
	public String getChargeModelAsString() {
		return chargeModel;
	}

	public void setChargeModel(PricingComponentChargeModel chargeModel) {
		this.chargeModel = chargeModel.toString();
	}

	public String getUpgradeModeAsString() {
		return upgradeMode;
	}

	public ValueChangeMode getUpgradeMode() {
		return ValueChangeMode.valueOf(upgradeMode);
	}

	public void setUpgradeMode(ValueChangeMode upgradeMode) {
		this.upgradeMode = upgradeMode.toString();
	}

	public String getDowngradeModeAsString() {
		return downgradeMode;
	}

	public ValueChangeMode getDowngradeMode() {
		return ValueChangeMode.valueOf(downgradeMode);
	}

	public void setDowngradeMode(ValueChangeMode downgradeMode) {
		this.downgradeMode = downgradeMode.toString();
	}

	public int getDefaultQuantity() {
		return defaultQuantity;
	}

	public void setDefaultQuantity(int defaultQuantity) {
		this.defaultQuantity = defaultQuantity;
	}

	public int getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
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

	public String getProductRatePlanID() {
		return productRatePlanID;
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

	public List<PricingComponentTier> getTiers() {
		return tiers;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public PricingComponent(BillForwardClient client_) {
		super(client_);		
	}
	
	protected PricingComponent() {
		
	}
	
	protected static ResourcePath resourcePath;
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("pricing-components", "pricing-component",  new TypeToken<APIResponse<RatePlan>>() {}.getType());
	}
	
	public enum PricingComponentChargeType {
		setup,
		subscription,
		arrears,
		usage
	}
	
	public enum PricingComponentChargeModel {
		flat,
		tiered,
		tiered_volume
	}
	
	public enum ValueChangeMode {
		immediate,
		delayed
	}
	
	public enum PricingComponentType {
		tieredPricingComponent,
		tieredVolumePricingComponent,
		flatPricingComponent
	}
}
