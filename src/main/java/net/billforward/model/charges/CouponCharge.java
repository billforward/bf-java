package net.billforward.model.charges;

public class CouponCharge {
    private String pricingComponentID;

    private String pricingComponentName;

    private String couponDefinitionID;

    private String couponInstanceID;

    private String couponModifierID;

    private String originatingChargeID;

    private String couponCode;

	public String getPricingComponentID() {
		return pricingComponentID;
	}

	public String getPricingComponentName() {
		return pricingComponentName;
	}

	public String getCouponDefinitionID() {
		return couponDefinitionID;
	}

	public String getCouponInstanceID() {
		return couponInstanceID;
	}

	public String getCouponModifierID() {
		return couponModifierID;
	}

	public String getOriginatingChargeID() {
		return originatingChargeID;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public static String getChargeTypeName() {
		return "Coupon";
	}
}
