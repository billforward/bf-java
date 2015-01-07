package net.billforward.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import net.billforward.exception.BillforwardException;
import net.billforward.model.PricingComponent.PricingComponentChargeModel;
import net.billforward.model.PricingComponent.PricingComponentChargeType;
import net.billforward.model.PricingComponent.PricingComponentType;
import net.billforward.model.PricingComponentTier.PricingComponentTierType;
import net.billforward.model.RatePlan.TaxStatus;

import org.junit.Test;

public class RatePlanTests extends TestBase {
	@Test
	public void GetRatePlan() throws BillforwardException {
		//--Get rate-plan by ID
		RatePlan ratePlan = RatePlan.getByID("1234CC32-B6E6-4972-BB28-965323148D9F");
		
		System.out.println(ratePlan.toString());
	}

	@Test
	public void UpdateRatePlan() throws BillforwardException {
		//--Get rate-plan by ID
		RatePlan ratePlan = RatePlan.getByID("2B9621AC-606A-49F3-970C-DE0B421470C4");
		
		System.out.println(ratePlan.toString());
		
		//ratePlan.setTaxStatus(TaxStatus.inclusive);
		
		// Update the rate plan
		ratePlan = ratePlan.save();
		
		System.out.println(ratePlan.toString());
	}
	
	@Test
	public void RetireRatePlan() throws BillforwardException {
		//--Get rate-plan by ID
		RatePlan ratePlan = RatePlan.getByID("5C7AD40C-9960-4257-8255-59416B7F45CC");
		
		System.out.println(ratePlan.toString());
		
		ratePlan.setTaxStatus(TaxStatus.exclusive);
		
		// Retire the rate plan
		ratePlan = ratePlan.retire();
		
		System.out.println(ratePlan.toString());
	}

	@Test
	public void GetAllRatePlans() throws BillforwardException {
		//--Get all rate-plans
		RatePlan[] ratePlans = RatePlan.getAll();
		
		assertNotNull(ratePlans);
	}
	
	@Test
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
	
	@Test
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
	
	@Test
	public void GetUnitOfMeasure() throws BillforwardException {
		UnitOfMeasure unit = UnitOfMeasure.getByID("D535DA63-7278-448C-9109-D11AA6E94DF0");
		
		assertEquals("D535DA63-7278-448C-9109-D11AA6E94DF0", unit.getID());
	}
	
	@Test
	public void GetUnitsOfMeasure() throws BillforwardException {
		UnitOfMeasure[] units = UnitOfMeasure.getAll();
		for(UnitOfMeasure unit : units) {
			System.out.println(unit.toString());
		}
	}
}
