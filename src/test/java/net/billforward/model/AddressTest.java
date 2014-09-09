package net.billforward.model;

import static org.junit.Assert.assertNotNull;
import net.billforward.exception.BillforwardException;

import org.junit.Test;

public class AddressTest extends TestBase {
	
	@Test
	public void UpdateAddress() throws BillforwardException {		
		//--Get profile by ID
		Profile profile = Profile.getByID("57456CDB-A868-4F8C-AD6F-957B44DB60EC");

		// Get address from profile
		Address address = profile.getAddresses().get(0);
		System.out.println(address.toString());
		
		assertNotNull(address);
		
		address.addressLine1 = "Changed address Line 1";
		
		// Update the profile containing the address
		profile = profile.save();
		
		System.out.println(profile.getAddresses().get(0).toString());
		

		assertNotNull(profile);
	}
	
	@Test
	public void CreateAddress() throws BillforwardException {		
		//--Create address on specified profile..
		Profile profile = Profile.getByID("57456CDB-A868-4F8C-AD6F-957B44DB60EC");

		// make model of address
		Address address = new Address();
		address.setProfileID(profile.getID());
		address.setAddressLine1("address line 1");
		address.setAddressLine2("address line 2");
		address.setAddressLine3("address line 3");
		address.setCity("London");
		address.setProvince("London");
		address.setCountry("United Kingdom");
		address.setPostcode("SW1A 2HQ");
		address.setLandline("02076014444");
		
		// create address from model, using API
		address = Address.create(address);
		
		System.out.println(address);
		

		assertNotNull(profile);
	}
}
