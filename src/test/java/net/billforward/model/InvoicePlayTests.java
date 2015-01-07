package net.billforward.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import net.billforward.exception.BillforwardException;
import net.billforward.model.Invoice.InvoiceType;
import net.billforward.model.amendments.PricingComponentValueMigrationChargeAmendmentMapping;
import net.billforward.model.amendments.ProductRatePlanMigrationChargeAmendment;

import org.junit.Test;

public class InvoicePlayTests extends TestBase {

	@Test
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
	
}
