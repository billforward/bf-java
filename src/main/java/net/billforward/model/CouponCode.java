package net.billforward.model;

import java.util.ArrayList;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;


public class CouponCode extends InsertableEntity<CouponCode> {
	@Expose protected String couponCode;
	@Expose protected int quantity;
	@Expose List<String> codes = new ArrayList<String>();	
		
	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<String> getCodes() {
		return codes;
	}

	protected static ResourcePath resourcePath;

	public CouponCode(BillForwardClient client_) {
		super(client_);		
	}
	
	public CouponCode() {
		
	}

	public static CouponCode getByCouponCode(String couponCode) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		CouponCode[] couponCodes = getByID(couponCode, ResourcePath(), "codes");
		if(couponCodes == null || couponCodes.length == 0) return null;
		return couponCodes[0];
	}

	public static CouponCode create(CouponCode couponCodes) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		String extraPath = String.format("%s/codes", couponCodes.couponCode);
		return create(couponCodes, ResourcePath(), extraPath)[0];
	}
	
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("coupons", "coupon",  new TypeToken<APIResponse<CouponCode>>() {}.getType());
	}
}
