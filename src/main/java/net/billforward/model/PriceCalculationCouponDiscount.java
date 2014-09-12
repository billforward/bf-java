package net.billforward.model;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

public class PriceCalculationCouponDiscount {
	@Expose public Boolean couponApplied;
	@Expose public String calculated;
	@Expose public BigDecimal unalteredValue;
	@Expose public BigDecimal discountValue;
	@Expose public int affectedSpecifierCount;
	@Expose public int discountStartValue;
	@Expose public int discountEndValue;

}
