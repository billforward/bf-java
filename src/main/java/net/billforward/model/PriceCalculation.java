package net.billforward.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class PriceCalculation extends BillingEntity {
	@Expose public Boolean couponApplied;
	@Expose public Date calculated;
	@Expose public BigDecimal unalteredCost;
	@Expose public BigDecimal totalDiscounts;
	@Expose public BigDecimal discountedCost;
	
	@Expose public List<PricingCalculationCost> costs;
	@Expose public List<PriceCalculationCouponDiscount> discounts;
	@Expose public PriceRequest priceRequest;

	/***
	 * Returns if a coupon was applied to this price calculation
	 * @return
	 */
	public Boolean getCouponApplied() {
		return couponApplied;
	}

	/***
	 * Time that the pricing calculation was created
	 * @return
	 */
	public Date getCalculated() {
		return calculated;
	}

	public BigDecimal getUnalteredCost() {
		return unalteredCost;
	}

	public BigDecimal getTotalDiscounts() {
		return totalDiscounts;
	}

	public BigDecimal getDiscountedCost() {
		return discountedCost;
	}

	public List<PricingCalculationCost> getCosts() {
		return costs;
	}

	public List<PriceCalculationCouponDiscount> getDiscounts() {
		return discounts;
	}

	public PriceRequest getPriceRequest() {
		return priceRequest;
	}

	protected PriceCalculation() {
		
	}
	
	protected static ResourcePath resourcePath;
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("pricing-components", "pricing-component",  new TypeToken<APIResponse<PriceCalculation>>() {}.getType());
	}
	
	public enum PriceRequestCodeType {
		instance,
		book,
        instanceID,
        bookID
	}
}
