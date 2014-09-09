package net.billforward.model;

import static org.junit.Assert.*;

import org.junit.Test;

import net.billforward.BillForwardClient;
import net.billforward.exception.BillforwardException;

public class OrganizationTests extends TestBase {

	@Test
	public void testMyOrganizationRetrieve() throws BillforwardException {
		Organization retrievedOrganization = Organization.getMine();
		
		assertNotNull(retrievedOrganization.getID());
		System.out.println(retrievedOrganization.toString());
	}
	
}
