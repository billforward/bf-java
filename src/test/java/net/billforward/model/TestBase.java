package net.billforward.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import net.billforward.BillForwardClient;
import net.billforward.exception.BillforwardException;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestBase {
	static String resourcePath = "";
	static BillForwardClient m_BfClient = null;
	@BeforeClass
	public static void setUp() {
		m_BfClient = BillForwardClient.makeDefaultClient("CLIENT_ID", "https://api-sandbox.billforward.net/2014.251.0");
		
//		System.setProperty("http.proxyHost", "127.0.0.1");
//		System.setProperty("https.proxyHost", "127.0.0.1");
//		System.setProperty("http.proxyPort", "8855");
//		System.setProperty("https.proxyPort", "8855");
	}
	
	@Test
	public void testAPIBase() throws BillforwardException {
		assertEquals("https://api-sandbox.billforward.net/2014.251.0", m_BfClient.getApiUrl());
	}
	
	public static String getResourceData(String path) {

	    String content = "";
		try {
			content = new Scanner(new File(resourcePath + path)).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    return content;
	}
}
