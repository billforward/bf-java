package net.billforward.model;

import static org.junit.Assert.*;
import net.billforward.exception.BillforwardException;

import org.junit.Test;

public class ProfileTests  extends TestBase {
	@Test
	public void GetAllProfiles() throws BillforwardException {
		//--Get all profiles
		Profile[] profiles = Profile.getAll();
		
		assertNotNull(profiles);
		System.out.println(profiles[0].toString());
	}
	
	@Test
	public void GetProfilesByID() throws BillforwardException {
		//--Get profile by ID
		Profile profile = Profile.getByID("187BE3D7-B89E-4C0F-A743-15191D5E8DC1");
		
		assertNotNull(profile);
		System.out.println(profile.toString());
	}
	
	@Test
	public void UpdateProfile() throws BillforwardException {
		//--Get profile by ID
		Profile profile = Profile.getByID("187BE3D7-B89E-4C0F-A743-15191D5E8DC1");
		// Change the first name
		profile.setFirstName("Test");
		// Update the profile
		profile = profile.save();
		
		System.out.println(profile.toString());
		

		assertNotNull(profile);
	}
}