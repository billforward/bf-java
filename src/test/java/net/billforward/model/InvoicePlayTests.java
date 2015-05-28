package net.billforward.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.billforward.exception.BillforwardException;
import net.billforward.model.Invoice.InvoiceType;
import net.billforward.model.amendments.PricingComponentValueMigrationChargeAmendmentMapping;
import net.billforward.model.amendments.ProductRatePlanMigrationChargeAmendment;
import net.billforward.model.charges.Charge;
import net.billforward.model.charges.ChargeType;
import net.billforward.model.charges.SubscriptionChargeType;

import org.junit.Test;

public class InvoicePlayTests extends TestBase {

	//@Test
	public void getByID() throws BillforwardException {
//		Invoice invoice = Invoice.getByID("5B839520-957F-459B-BB25-5194E395A3E2");
//		invoice.retryTakingPayment();
//		if(1 == 1) return;
		
//		RatePlan ratePlan = RatePlan.getByID("A570A087-771A-4EF1-A769-E97321114290");
//		System.out.println(ratePlan);
//		
//		if(1 == 1) return;
////		
//		Date dateToExecute = new Date(System.currentTimeMillis()+2*60*1000);
		
		
		//30 minute sub
//		ProductRatePlanMigrationChargeAmendment migration = new ProductRatePlanMigrationChargeAmendment();
//		migration.setProductRatePlanID("8BB3CE3C-7780-4095-A8B4-B2DD76BD065D");
//		migration.setSubscriptionID("AC7510EC-265B-405E-9CFB-BF1FBEDCDECD");
//		migration.addMapping("874FC13E-DC04-46B4-83E3-C57F1C710BBD", 69);
//		migration = ProductRatePlanMigrationChargeAmendment.create(migration);
//		
		

		ProductRatePlanMigrationChargeAmendment migration = new ProductRatePlanMigrationChargeAmendment();
		migration.setProductRatePlanID("A570A087-771A-4EF1-A769-E97321114290");
		migration.setSubscriptionID("AC7510EC-265B-405E-9CFB-BF1FBEDCDECD");
		migration.addMapping("E3279171-E188-4537-B586-21B687DDDFFF", 5);
		migration = ProductRatePlanMigrationChargeAmendment.create(migration);
		
		System.out.println(migration);
		
		
		
	}
	

	@Test
	public void getCharges() throws BillforwardException {
		Subscription sub = Subscription.getByID("SUB-64600AE9-89BB-4669-9D6B-ACDB830A");
		
		Invoice inv = Invoice.getByID("INV-ADAE5F4C-E135-456B-A8B4-94F1111C");
		//inv.recalculate();

//		inv.addCharge(new BigDecimal("72"), "Petty cash ");
////
//		inv.addCharge(15, "Speed");
//		inv.addCharge(25, "Speed");
//		inv.addCharge(10, "Bandwidth");
		
		Charge[] charges = sub.getPendingCharges();
		
		System.out.println("Charges on sub");
		if(charges != null) {
			for(Charge charge : charges) {
				System.out.println(String.format("%s %s [%s] - %s", charge.getType(), charge.getDescription(), charge.getAmount(), charge.getID()));
				if(charge.getType().equals(SubscriptionChargeType.Manual)) {
					
					//break;
				}
				
				//inv.addCharge(charge.getID());
				if(charge.getID().equals("CHG-E54B2E4B-B6A6-4435-8004-B57EFCD5")) {
				//	charge.delete();
				}
				//break;
				
					//charge.delete();
			}
		}	

		System.out.println("\n\nCharges on inv");
		charges = inv.getCharges();
		
		if(charges != null) {
			for(Charge charge : charges) {
				System.out.println(String.format("%s %s [%s] - %s", charge.getType(), charge.getDescription(), charge.getAmount(), charge.getID()));
				if(charge.getType().equals(SubscriptionChargeType.Manual)) {
//					inv.removeCharge(charge);
				}
				//inv.addCharge(charge.getID());
			//	inv.removeCharge(charge);
				//charge.delete();
		//		break;
			}
		}	
		
	inv.recalculate();
	}
	
}
