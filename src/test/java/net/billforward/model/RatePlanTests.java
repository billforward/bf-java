package net.billforward.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.BillforwardException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.PricingComponent.PricingComponentChargeModel;
import net.billforward.model.PricingComponent.PricingComponentChargeType;
import net.billforward.model.PricingComponent.PricingComponentType;
import net.billforward.model.PricingComponentTier.PricingComponentTierType;
import net.billforward.model.RatePlan.TaxStatus;

import org.junit.Test;

public class RatePlanTests extends TestBase {

	@Test
	public void addPricingComponent() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {		
		//Create the princg Plan
		RatePlan ratePlan = RatePlan.getByID("PRP-FEED06FF-5F73-47C3-920A-247EA785");

		//--Get Unit-of-Measure by ID
		UnitOfMeasure unitOfMeasure = UnitOfMeasure.getByID("UOM-2D7284B9-A179-4654-AC78-6F3F7AAD");
		
		// Create a pricing tier of 2->4 with each unit costing $0.10.
		PricingComponentTier tier = new PricingComponentTier();
		tier.setLowerThreshold(10);
		tier.setUpperThreshold(12);
		tier.setPrice(new BigDecimal(".10"));
		tier.setPricingType(PricingComponentTierType.unit);
		
		PricingComponentTier tier2 = new PricingComponentTier();
		tier2.setLowerThreshold(13);
		tier2.setUpperThreshold(13);
		tier2.setPrice(new BigDecimal(".10"));
		tier2.setPricingType(PricingComponentTierType.unit);
		
		//Create a Pricing Component for this tier and Unit-of-Measure
		PricingComponent pricingComponent = new PricingComponent();
		pricingComponent.setChargeModel(PricingComponentChargeModel.tiered);
		pricingComponent.setChargeType(PricingComponentChargeType.subscription);
		pricingComponent.setName("Bars Of Chocolate4");
		pricingComponent.setUnitOfMeasureID(unitOfMeasure.getID());
		pricingComponent.setType(PricingComponentType.tieredPricingComponent);
		pricingComponent.setProductRatePlanID(ratePlan.getID());
		pricingComponent.getTiers().add(tier);
		pricingComponent.getTiers().add(tier2);
		
		pricingComponent = PricingComponent.create(pricingComponent);
	}
	
	//@Test
	public void UpdateRatePlan2() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {		
		//Create the princg Plan
		RatePlan ratePlan = RatePlan.getByID("PRP-DBE50896-C3A0-4315-959D-39F76CD0");
//		ratePlan.setCurrency(Currency.getInstance("GBP"));
//		ratePlan.setTaxStatus(TaxStatus.exclusive);
//		ratePlan.setName("Pricing Plan Name " + new Date().getSeconds());
//		
//		ratePlan = ratePlan.save();
//		
//		ratePlan = RatePlan.getByID("PRP-DBE50896-C3A0-4315-959D-39F76CD0");
//		
		System.out.println(ratePlan.toString());
		
		assertNotNull(ratePlan);
	}
	
	//@Test
	public void CreateRatePlan() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		//PRO-EFFA35E9-8113-4086-AF71-020F330D
		
		//--Get product by ID
		Product product = Product.getByID("PRO-EFFA35E9-8113-4086-AF71-020F330D");

		
		//Create the princg Plan
		RatePlan ratePlan = new RatePlan();
		ratePlan.setCurrency(Currency.getInstance("USD"));
		ratePlan.setProductID(product.getID());
		ratePlan.setName("new plan baby" + new Date().getSeconds());
		//ratePlan.setTaxStatus(TaxStatus.exclusive);
		
		ratePlan = RatePlan.create(ratePlan);

		System.out.println(ratePlan.toString());
		
		assertNotNull(ratePlan);
	}
	
	//@Test
	public void GetRatePlan() throws BillforwardException {
		//--Get rate-plan by ID
		RatePlan ratePlan = RatePlan.getByID("1234CC32-B6E6-4972-BB28-965323148D9F");
		
		System.out.println(ratePlan.toString());
	}

	//@Test
	public void UpdateRatePlan() throws BillforwardException {
		//--Get rate-plan by ID
		RatePlan ratePlan = RatePlan.getByID("2B9621AC-606A-49F3-970C-DE0B421470C4");
		
		System.out.println(ratePlan.toString());
		
		//ratePlan.setTaxStatus(TaxStatus.inclusive);
		
		// Update the rate plan
		ratePlan = ratePlan.save();
		
		System.out.println(ratePlan.toString());
	}
	
	//@Test
	public void RetireRatePlan() throws BillforwardException {
		//--Get rate-plan by ID
		RatePlan ratePlan = RatePlan.getByID("5C7AD40C-9960-4257-8255-59416B7F45CC");
		
		System.out.println(ratePlan.toString());
		
		ratePlan.setTaxStatus(TaxStatus.exclusive);
		
		// Retire the rate plan
		ratePlan = ratePlan.retire();
		
		System.out.println(ratePlan.toString());
	}

	//@Test
	public void GetAllRatePlans() throws BillforwardException {
		//--Get all rate-plans
		RatePlan[] ratePlans = RatePlan.getAll();
		
		assertNotNull(ratePlans);
	}
	
	//@Test
	public void GetByProductID() throws BillforwardException {
		//--Get Product
		Product product = Product.getByID("69933463-4E56-4221-931A-5F009788B333");
		
		// Get rate-plans
		RatePlan[] ratePlans = RatePlan.getByProductID(product.getID());
		

		assertNotNull(ratePlans);
		
		for(RatePlan ratePlan : ratePlans) {
			assertEquals(product.getID(), ratePlan.getProductID());
		}
		
	}
	
	//@Test
	public void CreateRatePlans() throws BillforwardException {
		//--Get product by ID
		Product product = Product.getByID("D3E0F064-9E67-492E-8CFC-73E97B0B006A");
		
		//--Get Unit-of-Measure by ID
		UnitOfMeasure unitOfMeasure = UnitOfMeasure.getByID("12024AB2-1CB4-447A-968E-C1AE9DAE1CBF");
		
		// Create a pricing tier of 2->4 with each unit costing $0.10.
		PricingComponentTier tier = new PricingComponentTier();
		tier.setLowerThreshold(2);
		tier.setUpperThreshold(4);
		tier.setPrice(new BigDecimal(".10"));
		tier.setPricingType(PricingComponentTierType.unit);
		
		//Create a Pricing Component for this tier and Unit-of-Measure
		PricingComponent pricingComponent = new PricingComponent();
		pricingComponent.setChargeModel(PricingComponentChargeModel.tiered);
		pricingComponent.setChargeType(PricingComponentChargeType.subscription);
		pricingComponent.setName("Bars Of Chocolate!");
		pricingComponent.setUnitOfMeasureID(unitOfMeasure.getID());
		pricingComponent.setUpgradeMode(ValueChangeMode.immediate);
		pricingComponent.setDowngradeMode(ValueChangeMode.delayed);
		//pricingComponent.setDefaultQuantity(2);
		pricingComponent.setType(PricingComponentType.tieredPricingComponent);
		pricingComponent.getTiers().add(tier);
		
		//Create the princg Plan
		RatePlan ratePlan = new RatePlan();
		ratePlan.setCurrency(Currency.getInstance("USD"));
		ratePlan.setProductID(product.getID());
		ratePlan.getPricingComponents().add(pricingComponent);
		ratePlan.setName("Pricing Plan Name");
		ratePlan.setTaxStatus(TaxStatus.exclusive);
		
		ratePlan = RatePlan.create(ratePlan);

		System.out.println(ratePlan.toString());
		
		assertNotNull(ratePlan);
	}
	
	//@Test
	public void GetUnitOfMeasure() throws BillforwardException {
		UnitOfMeasure unit = UnitOfMeasure.getByID("D535DA63-7278-448C-9109-D11AA6E94DF0");
		
		assertEquals("D535DA63-7278-448C-9109-D11AA6E94DF0", unit.getID());
	}
	
	//@Test
	public void GetUnitsOfMeasure() throws BillforwardException {
		UnitOfMeasure[] units = UnitOfMeasure.getAll();
		for(UnitOfMeasure unit : units) {
			System.out.println(unit.toString());
		}
	}
}
