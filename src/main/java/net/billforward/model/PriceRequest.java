package net.billforward.model;

import java.util.ArrayList;
import java.util.List;

import net.billforward.BillForwardClient;

import com.google.gson.reflect.TypeToken;

public class PriceRequest extends BillingEntity {
	public String bookCode;
	public String codeType;
	public String couponCode;
	public String instanceID;
	public String bookID;
	public String accountID;
	public String productRatePlanID;
	public String productID;
	public String targetID;
	public String source;
	public boolean amendment;

	public List<PricingComponentValue> existingPricingComponentValues = new ArrayList<PricingComponentValue>();
	public List<PricingComponentValue> updatedPricingComponentValues = new ArrayList<PricingComponentValue>();

	
	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getCodeTypeAsString() {
		return codeType;
	}
	public PriceRequestCodeType getCodeType() {
		return PriceRequestCodeType.valueOf(codeType);
	}

	public void setCodeType(PriceRequestCodeType codeType) {
		this.codeType = codeType.toString();
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
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

	public String getTargetID() {
		return targetID;
	}

	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isAmendment() {
		return amendment;
	}

	public void setAmendment(boolean amendment) {
		this.amendment = amendment;
	}

	public List<PricingComponentValue> getExistingPricingComponentValues() {
		return existingPricingComponentValues;
	}

	public void setExistingPricingComponentValues(List<PricingComponentValue> existingPricingComponentValues) {
		this.existingPricingComponentValues = existingPricingComponentValues;
	}

	public List<PricingComponentValue> getUpdatedPricingComponentValues() {
		return updatedPricingComponentValues;
	}

	public void setUpdatedPricingComponentValues(List<PricingComponentValue> updatedPricingComponentValues) {
		this.updatedPricingComponentValues = updatedPricingComponentValues;
	}

	public PriceRequest(BillForwardClient client_) {
		super(client_);		
	}
	
	public PriceRequest() {
		
	}
	
	protected static ResourcePath resourcePath;
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("pricing-components", "pricing-component",  new TypeToken<APIResponse<PriceRequest>>() {}.getType());
	}
	
	public enum PriceRequestCodeType {
		instance,
		book,
        instanceID,
        bookID
	}

	public void addPricingComponentValue(PricingComponentValue value_) {
		updatedPricingComponentValues.add(value_);		
	}
}
