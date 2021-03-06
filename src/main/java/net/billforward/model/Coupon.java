package net.billforward.model;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class Coupon extends InsertableEntity<Coupon> {
	@Expose protected String name;
	@Expose protected String couponCode;
	@Expose protected Integer coupons;
	@Expose protected Integer uses;
	@Expose protected String product;
	@Expose protected String productRatePlan;
	@Expose protected String currency;
	@Expose protected boolean parentCouponCodeRedeemable = true;
	
	@Expose protected String productRatePlanName;
	@Expose protected String productRatePlanID;
	@Expose protected String productID;
	@Expose protected String productName;
	@Expose protected boolean deleted = false;
	
	@Expose protected List<CouponDiscount> discounts = new ArrayList<CouponDiscount>();
	
	//Coupon code values
	@Expose protected String parentCouponCode;
	@Expose protected CouponTarget appliesTo;
	@Expose protected String appliesToID;
	@Expose protected Integer remainingUses;
	@Expose protected Integer used;
	@Expose protected Date validUntil;

	//How many unique coupon codes to generate
	@Expose protected int quantity;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCouponCode() {
		return couponCode;
	}
	
	public Coupon setCouponCode(String couponCode) {
		this.couponCode = couponCode;
		return this;
	}
	
	public int getCoupons() {
		return coupons;
	}
	
	public Coupon setCoupons(int coupons) {
		this.coupons = coupons;
		return this;
	}
	
	public int getUses() {
		return uses;
	}
	public Coupon setUses(int uses) {
		this.uses = uses;
		return this;
	}
	
	public String getCurrencyAsString() {
		return currency;
	}

	public Currency getCurrency() {
		return Currency.getInstance(this.currency);
	}

	public Coupon setCurrency(Currency currency) {
		this.currency = currency.toString();
		return this;
	}
	
	public boolean isParentCouponCodeRedeemable() {
		return parentCouponCodeRedeemable;
	}
	
	public Coupon setParentCouponCodeRedeemable(boolean parentCouponCodeRedeemable) {
		this.parentCouponCodeRedeemable = parentCouponCodeRedeemable;
		return this;
	}

	public String getProductRatePlan() {
		return productRatePlan;
	}

	public void setProductRatePlan(String productRatePlan) {
		this.productRatePlan = productRatePlan;
	}

	public String getProductRatePlanName() {
		return productRatePlanName;
	}

	public void setProductRatePlanName(String productRatePlanName) {
		this.productRatePlanName = productRatePlanName;
	}

	public String getProductRatePlanID() {
		return productRatePlanID;
	}

	public void setProductRatePlanID(String productRatePlanID) {
		this.productRatePlanID = productRatePlanID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getParentCouponCode() {
		return parentCouponCode;
	}

	public CouponTarget getAppliesTo() {
		return appliesTo;
	}

	public String getAppliesToID() {
		return appliesToID;
	}

	public Integer getRemainingUses() {
		return remainingUses;
	}

	
	public Integer getUsed() {
		return used;
	}

	public Coupon setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public List<CouponDiscount> getDiscounts() {
		return discounts;
	}

	public Coupon addDiscount(CouponDiscount discount) {
		this.discounts.add(discount);
		return this;
	}
	
	public Coupon removeDiscount(CouponDiscount discount) {
		this.discounts.remove(discount);
		return this;
	}
	
	public static Coupon create(Coupon coupon) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(coupon, ResourcePath())[0];
	}
	
	public static Coupon getByCode(String code) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon[] coupon = getByID(code, ResourcePath());
		if(coupon == null || coupon.length == 0) return null;
		return coupon[0];
	}
	
	public static Coupon[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}
	
	protected static ResourcePath resourcePath;

	public Coupon(BillForwardClient client_) {
		super(client_);		
	}

	public Coupon() {
		
	}
	
	public Coupon(String productRateID_) {
		this.productRatePlan = productRateID_;
	}
	
	public Coupon(String product_, String productRatePlan_) {
		this.product = product_;
		this.productRatePlan = productRatePlan_;
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("coupons", "coupon",  new TypeToken<APIResponse<Coupon>>() {}.getType());
	}
	
	public Coupon[] createUniqueCodes(int quantity) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {		
		return Coupon.createCouponCodes(couponCode, quantity);
	}
	
	public static Coupon[] createCouponCodes(String couponCode, int quantity) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon codeRequest = new Coupon();
		codeRequest.quantity = quantity;
		
		String extraPath = String.format("%s/codes", couponCode);
		return create(codeRequest, ResourcePath(), extraPath);
	}
	

	public Coupon[] getCouponCodes() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return Coupon.getCouponCodes(this.couponCode);
	}
	
	public static Coupon[] getCouponCodes(String couponCode) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getByID(couponCode, ResourcePath(), "codes");
	}

	public Coupon addUnitsFree(String pricingComponent, int unitsFree) {
		CouponDiscount discount = new CouponDiscount(pricingComponent);
		discount.setUnitsFree(unitsFree);		
		this.discounts.add(discount);
		return this;
	}

	public Coupon addPercentageDiscount(String pricingComponent, int percentOff) {
		CouponDiscount discount = new CouponDiscount(pricingComponent);
		discount.setPercentageDiscount(percentOff);		
		this.discounts.add(discount);
		return this;
	}
	
	public Coupon addPercentageDiscount(int percentOff) {
		CouponDiscount discount = new CouponDiscount();
		discount.setPercentageDiscount(percentOff);		
		this.discounts.add(discount);
		return this;
	}

	public Coupon addCashDiscount(String pricingComponent, int discountAmount) {
		CouponDiscount discount = new CouponDiscount(pricingComponent);
		discount.setCashDiscount(discountAmount);
		this.discounts.add(discount);
		return this;
	}

	public Coupon addCashDiscount(int discountAmount) {
		CouponDiscount discount = new CouponDiscount();
		discount.setCashDiscount(discountAmount);
		this.discounts.add(discount);
		return this;
	}
	
	public enum CouponTarget {
		none,
		subscription,
		account
	}

	public Coupon addToSubscription(String subscriptionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return addCouponCodeToSubscription(this.couponCode, subscriptionID);
	}
	
	public static Coupon addCouponCodeToSubscription(String couponCode, String subscriptionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon coupon = new Coupon();
		coupon.setCouponCode(couponCode);
		String path = String.format("subscriptions/%s/coupons", subscriptionID); 
		return createExplicitPath(coupon, ResourcePath(), path)[0];
	}

	public static Coupon[] getCouponsForSubscription(String subscriptionID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		String path = String.format("subscriptions/%s/coupons", subscriptionID); 
		return getAll(ResourcePath(), path);
	}

	public static Coupon removeCouponCode(String couponCode) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return retire(couponCode, ResourcePath());
	}
}
