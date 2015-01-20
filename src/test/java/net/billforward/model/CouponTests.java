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
		coupon.setCouponCode("ian15");
		coupon.setCoupons(10);
		coupon.setUses(10);
		coupon.setCurrency(Currency.getInstance("USD"));

		coupon.setPercentageDiscount("u1 ", 100);
		coupon.setPercentageDiscount("u2 ", 1);
		coupon.setPercentageDiscount("u3 ", 10);
		coupon.setPercentageDiscount("u4 ", 4);
		coupon.setPercentageDiscount(10);
		
//		coupon.setPercentageDiscount("Userss", 10);
//		coupon.setCashDiscount("USerss", 20);
//		coupon.setCashDiscount(3);
//		coupon.setPercentageDiscount(4);
		
		Coupon newCoupon = Coupon.create(coupon);
		
		System.out.println(newCoupon);
	}
	
	//@Test
	public void testCreationSimple() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon coupon = new Coupon().setCouponCode("SUMMERFUN").
				setCoupons(10).
				setUses(10).
				setCurrency(Currency.getInstance("USD")).
				setUnitsFree("Users", 10);
		
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
		Subscription subscription = Subscription.getByID("D40FC351-7FB4-4E45-A43A-750A7DB2B249");
		Coupon[] codes = subscription.getCouponCodes();
		Coupon coupon = null;
		for(Coupon code : codes) {
			coupon = subscription.removeCouponCode(code.getCouponCode());
			System.out.println(coupon);
			
		}
				
	}
	


	@Test
	public void addCoupon() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Subscription subscription = Subscription.getByID("D40FC351-7FB4-4E45-A43A-750A7DB2B249");
	//	String[] couponCodes = Coupon.createCouponCodes("ian8", 1);
	//	String code = couponCodes[0];
		
		Coupon coupon = subscription.addCouponCode("ian15");
				
		//subscription.removeCouponCode(code);
		
		System.out.println(coupon);
	}
	
}
