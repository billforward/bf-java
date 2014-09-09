package net.billforward.model;

import static org.junit.Assert.*;
import net.billforward.exception.BillforwardException;

import org.junit.Test;

public class AccountTests extends TestBase {
	
	@Test
	public void testAccountRetrieve() throws BillforwardException {
		Account retrievedAccount = Account.getByID("F15490C4-FC6B-4E40-90B2-1A16A66B619F");
		
		assertEquals("F15490C4-FC6B-4E40-90B2-1A16A66B619F", retrievedAccount.getID());
		System.out.println(retrievedAccount.toString());
	}
	
	@Test
	public void testIncorrectAccountRetrieve() throws BillforwardException {
		Account retrievedAccount = Account.getByID("FAIL");		
		assertEquals(null, retrievedAccount);
	}

	
	//@Test
	public void testCreateAccountCreate() throws BillforwardException {
		Address address = new Address();
		address.setAddressLine1("address line 1");
		address.setAddressLine2("address line 2");
		address.setAddressLine3("address line 3");
		address.setCity("London");
		address.setProvince("London");
		address.setCountry("United Kingdom");
		address.setPostcode("SW1A 2HQ");
		address.setLandline("02076014444");

		Profile profile = new Profile();
		profile.setFirstName("Test");
		profile.setEmail("always@testing.is.moe");
		profile.getAddresses().add(address);
		
		Account account = new Account();
		account.setProfile(profile);
		
		account = Account.create(account);

		System.out.println(account.toString());
		
		assertNotNull(account);
	}

	@Test
	public void testGetAccountByID() throws BillforwardException {

		Account retrievedAccount = Account.getByID("F15490C4-FC6B-4E40-90B2-1A16A66B619F");
		assertEquals("F15490C4-FC6B-4E40-90B2-1A16A66B619F", retrievedAccount.getID());
				
		//retrievedAccount = retrievedAccount.save();
		
		//assertEquals("2014-06-11T23:38:23Z", retrievedAccount.getUpdated());
	}
	
	@Test
	public void testUpdateAccountRetrieve() throws BillforwardException {

		Account[] retrievedAccounts = Account.getAll();
		assertNotNull("F15490C4-FC6B-4E40-90B2-1A16A66B619F", retrievedAccounts);
				
		//retrievedAccount = retrievedAccount.save();
		
		//assertEquals("2014-06-11T23:38:23Z", retrievedAccount.getUpdated());
	}

	//@Test
	public void testUpdateAccountUpdate() throws BillforwardException {
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
		
		assertNotNull(account);
	}
}
