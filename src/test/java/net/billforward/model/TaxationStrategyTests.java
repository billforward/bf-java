package net.billforward.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

import org.junit.Test;

import net.billforward.exception.BillforwardException;
import net.billforward.model.TaxationStrategy.TaxationCountry;

public class TaxationStrategyTests extends TestBase {


	//@Test
	public void getAll() throws BillforwardException {
		TaxationStrategy[] TaxationStrategies = TaxationStrategy.getAll();
		
		for(TaxationStrategy tax : TaxationStrategies) {
			System.out.println(tax);
		}
	}
	
	//@Test
	public void create() throws BillforwardException {		
		TaxationStrategy tax = new TaxationStrategy();
		tax.setName("GST");
		tax.setCurrency(Currency.getInstance("USD"));
		tax.setCountry(TaxationCountry.USA);
		tax.setProvince("CAlifornia");
		tax.setPercentage(new BigDecimal("7.5"));
		
		tax = TaxationStrategy.create(tax);
		System.out.println(tax);
	}
	

	
	//@Test
	public void scheduleChange() throws BillforwardException, ParseException {			
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date date = formatter.parse("09/28/15");
		
		TaxationStrategy tax = new TaxationStrategy();
		tax.setID("TAX-E74F9C24-EB19-45E1-806D-01F9117A");
		tax.setPercentage(new BigDecimal("10"));
		tax.setValidFrom(date);
		
		tax = TaxationStrategy.create(tax);
		System.out.println(tax);
	}

	//@Test
	public void getByID() throws BillforwardException {		
		TaxationStrategy tax = TaxationStrategy.getByID("TAX-E74F9C24-EB19-45E1-806D-01F9117A");

		System.out.println(tax);
	}


	
	

	//@Test
	public void updateTax() throws BillforwardException {
		
		TaxationStrategy tax = TaxationStrategy.getByID("TAX-E74F9C24-EB19-45E1-806D-01F9117A");
		tax.setPercentage(new BigDecimal("6.5"));
		tax = tax.save();
		
		System.out.println(tax);
	}
	
	//@Test
	public void retire() throws BillforwardException {	
		TaxationStrategy tax = TaxationStrategy.getByVersionID("TAX-2C539974-01A7-4CDA-9B38-902905BB");
		
		tax = tax.retire();
		
		System.out.println(tax);
	}
	
	
	
	//@Test
	public void addDefaultTaxationStrategy() throws BillforwardException {	

		
		//US
		//TaxationStrategy tax = TaxationStrategy.getByID("TAX-1E8DDFD7-6E7A-47DF-9409-D6E38972");
		//TaxationStrategy tax = TaxationStrategy.getByID("TAX-68DED146-5C78-4EFD-929C-65DD2FD6");
		
		//UK
		//TaxationStrategy tax = TaxationStrategy.getByID("TAX-171A5B1D-A1F3-400A-A721-EB877AD3");
		//TaxationStrategy tax = TaxationStrategy.getByVersionID("TAX-171A5B1D-A1F3-400A-A721-EB877AD3");
		TaxationStrategy tax = TaxationStrategy.getByID("TAX-89C939BF-CDA1-4E54-A30D-0976C204");
		
		tax.setName("vat3");
		tax.setDefaultTaxationStrategy(true);
		//tax.setPercentage(new BigDecimal("72"));
		tax = tax.save();
		
		System.out.println(tax);
	}
	
	//@Test
	public void chanegDefaultTaxationStrategy() throws BillforwardException {		
		TaxationStrategy tax = TaxationStrategy.getByID("TAX-1E8DDFD7-6E7A-47DF-9409-D6E38972");
		tax.setDefaultTaxationStrategy(true);
		tax = tax.save();
		
		System.out.println(tax);
	}

	//@Test
	public void addTax() throws BillforwardException {	
		RatePlan ratePlan = RatePlan.getByID("EED77FD0-BA6F-4386-8343-9F4080DF305F");
		TaxationStrategy tax = TaxationStrategy.getByID("TAX-68DED146-5C78-4EFD-929C-65DD2FD6");

		tax = ratePlan.addTax(tax.getID());
		
		System.out.println(tax);
	}

	@Test
	public void removeTax() throws BillforwardException {	
		RatePlan ratePlan = RatePlan.getByID("EED77FD0-BA6F-4386-8343-9F4080DF305F");
						
		for(TaxationStrategy tax : ratePlan.getTax()) {
			ratePlan.removeTax(tax.getID());	
			System.out.println(tax);		
		}
		
	}
	

	//@Test
	public void listTax() throws BillforwardException {	
		RatePlan ratePlan = RatePlan.getByID("EED77FD0-BA6F-4386-8343-9F4080DF305F");
		TaxationStrategy[] taxes = ratePlan.getTax();

		for(TaxationStrategy tax : taxes) {
			System.out.println(tax);			
		}		
	}
}
