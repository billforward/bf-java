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
		m_BfClient = BillForwardClient.makeDefaultClient("6c0f497a-2af0-4e72-8181-f537b729129e", "https://api-sandbox.billforward.net/2014.251.0");
		
//		System.setProperty("http.proxyHost", "127.0.0.1");
//		System.setProperty("https.proxyHost", "127.0.0.1");
//		System.setProperty("http.proxyPort", "8855");
//		System.setProperty("https.proxyPort", "8855");
	}
	
	@Test
	public void testAPIBase() throws BillforwardException {
		assertEquals("https://api-sandbox.billforward.net/2014.251.0", m_BfClient.getApiUrl());
	}
}
