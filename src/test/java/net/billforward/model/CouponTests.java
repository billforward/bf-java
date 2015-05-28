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
		Coupon coupon = new Coupon();//"Premium", "Monthly");
		coupon.setName("6 Months free");
		coupon.setCouponCode("WINTERFUN1");
		coupon.setCoupons(10);
		coupon.setUses(6);
		coupon.setCurrency(Currency.getInstance("USD"));
		
		//coupon.addPercentageDiscount("Users", 50);
		
		Coupon newCoupon = Coupon.create(coupon);
		
		System.out.println(newCoupon);
	}
	
	//@Test
	public void testCreationComplex() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon coupon = new Coupon("Corporate", "EXAMPLE-CUSTOM");
		coupon.setName("1 year discount");
		coupon.setCouponCode("EXAMPLE-COMPLEX");
		coupon.setCoupons(1);
		coupon.setUses(12);
		
		coupon.addPercentageDiscount("Users", 50);
		coupon.addUnitsFree("Licenses", 5);
		coupon.addUnitsFree("Monthly Total API Requests", 1000);
		coupon.addCashDiscount(10);
		coupon.addPercentageDiscount(8);
		
		Coupon newCoupon = Coupon.create(coupon);
		
		System.out.println(newCoupon);
	}

	//@Test
	public void getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon[] coupons = Coupon.getAll();
		
		for(Coupon code : coupons) {
			System.out.println(code);			
		}
	}
	

	//@Test
	public void testUniqueFetch() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon[] codes = Coupon.getCouponCodes("WINTERFUN1");
		
		for(Coupon code : codes) {
			System.out.println(code);			
		}
	}
	
	//@Test
	public void createCouponCodes() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon[] codes = Coupon.createCouponCodes("WINTERFUN1", 1);
		
		for(Coupon code : codes) {
			System.out.println(code);			
		}
	}
	
	//@Test
	public void getCoupon() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon coupon = Coupon.getByCode("WINTERFUN");
		
		System.out.println(coupon);
	}

	//@Test
	public void getCouponCode() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Coupon coupon = Coupon.getByCode("WINTERFUN13100291C");
		
		System.out.println(coupon);
	}
	
	//@Test
	public void addCouponCodeToSubscription() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		String couponCode = "ian6";
		Subscription subscription = Subscription.getByID("B51BF769-24BE-432B-B592-66E257ABEBF2");
		
		Coupon coupon = subscription.addCouponCode(couponCode);
		
		System.out.println(coupon);
	}

	@Test
	public void getCouponCodes() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Subscription subscription = Subscription.getByID("SUB-684FA942-35C2-4865-A365-48672842");
		Coupon[] coupons = subscription.getCouponCodes();
		
		for(Coupon coupon : coupons) {
			System.out.println(coupon);			
		}
	}

	//@Test
	public void removeCouponCode() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Subscription subscription = Subscription.getByID("SUB-CE7A2F73-4ADA-4047-BFA6-C30FBFB6");
		Coupon[] codes = subscription.getCouponCodes();
		Coupon coupon = null;
		for(Coupon code : codes) {
			coupon = subscription.removeCouponCode(code.getCouponCode());
			System.out.println(coupon);
			
		}				
	}

	//@Test
	public void addCoupon() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Subscription subscription = Subscription.getByID("SUB-CE7A2F73-4ADA-4047-BFA6-C30FBFB6");
		Coupon coupon = subscription.addCouponCode("WINTERFUN1");
		
		System.out.println(coupon);
	}
	
}
