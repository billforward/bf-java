package net.billforward.model;

import java.util.Currency;

import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import org.junit.Test;

public class CouponTests extends TestBase {
	
	//@Test
	public void testCreation() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon coupon = new Coupon();
		coupon.setCouponCode("SUMMERFUN");
		coupon.setCoupons(10);
		coupon.setUses(10);
		coupon.setCurrency(Currency.getInstance("USD"));
		
		coupon.unitsFree("Users", 10).percentDiscount("Users", 25).cashDiscount(10);
		
		Coupon newCoupon = Coupon.create(coupon);
		
		System.out.println(newCoupon);
	}
	
	//@Test
	public void testCreationSimple() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon coupon = new Coupon().setCouponCode("SUMMERFUN").
				setCoupons(10).
				setUses(10).
				setCurrency(Currency.getInstance("USD")).
				unitsFree("Users", 10);
		
		Coupon newCoupon = Coupon.create(coupon);
		
		System.out.println(newCoupon);
	}
	
	

	//@Test
	public void testUniqueFetch() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		String[] codes = Coupon.getCouponCodes("ian3");
		
		for(String code : codes) {
			System.out.println(code);			
		}
	}
	
	//@Test
	public void createCouponCodes() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		String[] codes = Coupon.createCouponCodes("ian6", 1);
		
		for(String code : codes) {
			System.out.println(code);			
		}
	}
	
	//@Test
	public void getCoupon() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon coupon = Coupon.getByCode("ian6");
		
		System.out.println(coupon);
	}

	//@Test
	public void getCouponCode() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon coupon = Coupon.getByCode("ian60E145F70");
		
		System.out.println(coupon);
	}
	
	//@Test
	public void addCouponCodeToSubscription() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		String couponCode = "ian6";
		Subscription subscription = Subscription.getByID("B51BF769-24BE-432B-B592-66E257ABEBF2");
		
		Coupon coupon = subscription.addCouponCode(couponCode);
		
		System.out.println(coupon);
	}

	//@Test
	public void getCouponCodes() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Subscription subscription = Subscription.getByID("B51BF769-24BE-432B-B592-66E257ABEBF2");
		Coupon[] coupons = subscription.getCouponCodes();
		
		
		for(Coupon coupon : coupons) {
			System.out.println(coupon);			
		}
	}

	//@Test
	public void removeCouponCode() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Subscription subscription = Subscription.getByID("B51BF769-24BE-432B-B592-66E257ABEBF2");
		Coupon coupon = subscription.removeCouponCode("ian60E145F70");
				
		System.out.println(coupon);
	}
	


	@Test
	public void addCoupon() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Subscription subscription = Subscription.getByID("D4F6464B-C423-4601-9633-AA83B4DF76F2");
		String[] couponCodes = Coupon.createCouponCodes("ian6", 1);
		String code = couponCodes[0];
		
		Coupon coupon = subscription.addCouponCode(code);
				
		//subscription.removeCouponCode(code);
		
		System.out.println(coupon);
	}
	
}
