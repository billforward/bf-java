package net.billforward.model.charges;

import com.google.gson.annotations.Expose;

import net.billforward.model.PricingComponent.PricingComponentChargeType;
import net.billforward.model.ResourcePath;

public class PricingComponentCharge extends Charge {
	@Expose private String pricingComponentName;
	
	@Expose private String pricingComponentID;

	@Expose private String unitOfMeasureID;

	@Expose private String unitOfMeasureName;

	@Expose private int pricingComponentValue;

	@Expose private PricingComponentChargeType pricingComponentType;

	@Expose private ChargeCreationType creationType;

	public String getPricingComponentName() {
		return pricingComponentName;
	}

	public String getPricingComponentID() {
		return pricingComponentID;
	}

	public String getUnitOfMeasureID() {
		return unitOfMeasureID;
	}

	public String getUnitOfMeasureName() {
		return unitOfMeasureName;
	}

	public int getPricingComponentValue() {
		return pricingComponentValue;
	}

	public PricingComponentChargeType getPricingComponentType() {
		return pricingComponentType;
	}

	public ChargeCreationType getCreationType() {
		return creationType;
	}

	public static String getChargeTypeName() {
		return "PricingComponent";
	}

	@Override
	protected ResourcePath getResourcePath() {
		// TODO Auto-generated method stub
		return null;
	}

}
