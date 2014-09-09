package net.billforward.model;

import static org.junit.Assert.assertEquals;
import net.billforward.BillForwardClient;
import net.billforward.exception.BillforwardException;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestBase {
	static BillForwardClient m_BfClient = null;
	@BeforeClass
	public static void setUp() {
		m_BfClient = BillForwardClient.makeDefaultClient("INSERT_ACCESS_TOKEN_HERE", "https://api-sandbox.billforward.net/2014.223.0");
	
	}
	
	@Test
	public void testAPIBase() throws BillforwardException {
		assertEquals("https://api-sandbox.billforward.net/2014.223.0", m_BfClient.getApiUrl());
	}
}
