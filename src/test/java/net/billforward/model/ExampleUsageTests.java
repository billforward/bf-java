package net.billforward.model;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Currency;

import net.billforward.exception.BillforwardException;
import net.billforward.model.PricingComponent.PricingComponentChargeModel;
import net.billforward.model.PricingComponent.PricingComponentChargeType;
import net.billforward.model.PricingComponent.PricingComponentType;
import net.billforward.model.PricingComponent.ValueChangeMode;
import net.billforward.model.PricingComponentTier.PricingComponentTierType;
import net.billforward.model.Product.ProductPeriod;
import net.billforward.model.Product.ProductType;
import net.billforward.model.RatePlan.TaxStatus;
import net.billforward.model.Subscription.SubscriptionState;
import net.billforward.model.Subscription.SubscriptionType;
import net.billforward.model.UnitOfMeasure.RoundingScheme;
import net.billforward.model.gateways.APIConfiguration;
import net.billforward.model.gateways.AuthorizeNetConfiguration;
import net.billforward.model.gateways.GatewayEnvironment;

import org.junit.Test;

public class ExampleUsageTests extends TestBase {

	@Test
	public void testMyOrganizationRetrieve() throws BillforwardException {
		Account[] accounts = Account.getAll();
		
		Account loginAccount = null;
		for(Account account : accounts) {
			if(account.getUserID() != null) {
				loginAccount = account;
				break;
			}
		}
		
		//assertNotNull(loginAccount);
		
		
		Organization org = Organization.getMine();

		assertNotNull(org);
		
//		String authorizeNetLoginID = "KEY";
//		String authorizeNetTransactionKey = "KEY";
//		
//		AuthorizeNetConfiguration authConfig = null;
//		for(APIConfiguration config : org.apiConfigurations) {
//			if(config.getClass().equals(AuthorizeNetConfiguration.class)) {
//				authConfig = (AuthorizeNetConfiguration)config;;				
//			}
//		}
//		
//		if(authConfig != null) {
//			authConfig.setAPILoginID(authorizeNetLoginID);
//			authConfig.setTransactionKey(authorizeNetTransactionKey);
//			org.apiConfigurations.add(authConfig);
//		} else {
//			authConfig = new AuthorizeNetConfiguration(m_BfClient, 
//													   authorizeNetLoginID,
//													   authorizeNetTransactionKey, 
//													   GatewayEnvironment.Sandbox);
//		}
//		
//		org.update();
//		
//		Address address = new Address();
//		address.setAddressLine1("address line 1");
//		address.setAddressLine2("address line 2");
//		address.setAddressLine3("address line 3");
//		address.setCity("London");
//		address.setProvince("London");
//		address.setCountry("United Kingdom");
//		address.setPostcode("SW1A 2HQ");
//		address.setLandline("02076014444");
//
//		Profile profile = new Profile();
//		profile.setFirstName("Test");
//		profile.setEmail("always@testing.is.moe");
//		profile.getAddresses().add(address);
//		
//		Account acc = new Account();
//		acc.setProfile(profile);
//		
//		acc = Account.create(acc);
//
//		System.out.println(acc.toString());

		
		//Account accs[] = Account.getAll();
		//System.out.println(accs.toString());

		//Account account = Account.getByID("44C0E6B7-2F40-4009-8C82-1EF7E84FDE59");
		//System.out.println(account.toString());
		
		//--Add Profile to an existing Account..
		// construct default model of new account
		Account account = new Account();
		// create modeled account via API
		account = Account.create(account);
		
		// construct model of profile
		Profile profile = new Profile();
		profile.setFirstName("Test");
		profile.setEmail("always@testing.is.moe");
		
		// associate profile with account
		account.setProfile(profile);
		
		// save changes to account
		account = account.save();
		
		System.out.println(account.toString());
		
		System.exit(1);
		
		
//		Account acc = Account.getByID("F15490C4-FC6B-4E40-90B2-1A16A66B619F"); //  m_BfClient.accounts.getByID("F15490C4-FC6B-4E40-90B2-1A16A66B619F");
//		//acc.getProfile().setFirstName("Tanya");
//		acc.getProfile().setLastName("Eset");
//		acc.getProfile().setMobile("07725216883");
//
//		acc.getProfile().save();
//		
		
//		AuthorizeNetToken token = new AuthorizeNetToken(m_BfClient);
//		token.setCustomerProfileID("28476855");
//		token.setCustomerPaymentProfileID("25879733");
//		token.setCardDetailsID("card details");
//		token.setLastFourDigits("4444");
//		token.setAccountID(acc.getID());
//		token.create();
		
		//AuthorizeNetToken token = AuthorizeNetToken.getByID("3BBEA61-5377-4B5D-8ABB-D9F818FFE0AE");
		//m_BfClient. "E3BBEA61-5377-4B5D-8ABB-D9F818FFE0AE";
		
		//System.out.println(token.toString());
		
//		PaymentMethod paymentMethod = new PaymentMethod();
//		paymentMethod.setAccountID(acc.getID());
//		paymentMethod.setCardHolderName("Ian Saunders");
//		paymentMethod.setCardType(CardType.Visa);
//		paymentMethod.setReusable(true);
//		paymentMethod.setLinkID("3BBEA61-5377-4B5D-8ABB-D9F818FFE0AE");
//		paymentMethod.create();
		
		PaymentMethod paymentMethod = PaymentMethod.getByID("9A176B4F-7D36-46E4-A83F-8D31D74EF746");
		
		System.out.println(paymentMethod.toString());
		
		//UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		//unitOfMeasure.setDisplayedAs("bars");
		//unitOfMeasure.setName("Chocolate");
		//unitOfMeasure.setRoundingScheme(RoundingScheme.UP);
		UnitOfMeasure unitOfMeasure = UnitOfMeasure.getByID("12024AB2-1CB4-447A-968E-C1AE9DAE1CBF");
//		unitOfMeasure.setRoundingScheme(RoundingScheme.HALF_EVEN);
//		unitOfMeasure = unitOfMeasure.update();
		
		//UnitOfMeasure[] unitsOM = UnitOfMeasure.getAll();
		System.out.println(unitOfMeasure.toString());
		
//		Product product = new Product();
//		product.setProductType(ProductType.recurring);
//		product.setName("API Product");
//		product.setDescription("API Desc");
//		product.setDurationPeriod(ProductPeriod.minutes);
//		product.setDuration(3);
//		product = Product.create(product);
		
		Product product = Product.getByID("D3E0F064-9E67-492E-8CFC-73E97B0B006A");
		
		System.out.println(product.toString());
		
		PricingComponentTier tier = new PricingComponentTier();
		tier.setLowerThreshold(2);
		tier.setUpperThreshold(4);
		tier.setPrice(new BigDecimal(".10"));
		tier.setPricingType(PricingComponentTierType.unit);
		
//		PricingComponent pricingComponent = new PricingComponent();
//		pricingComponent.setChargeModel(PricingComponentChargeModel.tiered);
//		pricingComponent.setChargeType(PricingComponentChargeType.subscription);
//		pricingComponent.setName("Bars Of Chocolate!");
//		pricingComponent.setUnitOfMeasureID(unitOfMeasure.getID());
//		pricingComponent.setUpgradeMode(ValueChangeMode.immediate);
//		pricingComponent.setDowngradeMode(ValueChangeMode.delayed);
//		pricingComponent.setDefaultQuantity(2);
//		pricingComponent.setType(PricingComponentType.tieredPricingComponent);
//		pricingComponent.getTiers().add(tier);
//		
//		RatePlan ratePlan = new RatePlan();
//		ratePlan.setCurrency(Currency.getInstance("USD"));
//		ratePlan.setProductID(product.getID());
//		ratePlan.getPricingComponents().add(pricingComponent);
//		ratePlan.setName("Another Name");
//		ratePlan.setTaxStatus(TaxStatus.exclusive);
//		
//		ratePlan = RatePlan.create(ratePlan);
		
		RatePlan ratePlan = RatePlan.getByID("A97DD9A3-A704-4837-990F-F63671A866EA");

		//ratePlan.getPricingComponents().add(pricingComponent);
		//ratePlan.update();
		System.out.println(ratePlan.toString());
		
		PricingComponentValue value = new PricingComponentValue();
		value.setPricingComponentID(ratePlan.getPricingComponents().get(0).getID());
		value.setValue(3);
		
//		Subscription sub = new Subscription();
//		sub.setType(SubscriptionType.Subscription);
//		sub.setProductID(ratePlan.getProductID());
//		sub.setProductRatePlanID(ratePlan.getID());
//		sub.setAccountID(acc.getID());
//		sub.setName("API Sub");
//		sub.setDescription("My New Sub 2!");
//		sub.setState(SubscriptionState.Provisioned);
//		sub.getPricingComponentValues().add(value);
//		sub.setDescription("Some Desc");
		
//		sub = Subscription.create(sub);

		//Subscription sub = Subscription.getByID("5F66E6F1-22DB-4F46-8EF8-0DD6CB34F05A");
		//sub.setState(SubscriptionState.AwaitingPayment);
		//sub.update();
		
		Subscription sub = Subscription.getByID("79AA1229-4E97-4368-A513-8CFE8C0BDD95");
//		for(PricingComponentValue val : sub.getPricingComponentValues()) {
//			val.setValue(2);
//		}
//		sub.update();
		//System.out.println(sub.toString());
		
//		PaymentMethod creditNote = new PaymentMethod();
//		creditNote.setAccountID(acc.getID());
//		creditNote.setDescription("Credit Note");
//		creditNote.setGateway(PaymentGateway.credit_note);
//		creditNote.setName("Credit Note");
		
		//creditNote.setReusable(true);


		//creditNote = PaymentMethod.create(creditNote);
		PaymentMethod creditNote = PaymentMethod.getByID("60FCC502-772A-49D4-AA90-2CDCBB71C589");
		
		sub.addPaymentMethod(creditNote);
		sub.save();
	}
}
