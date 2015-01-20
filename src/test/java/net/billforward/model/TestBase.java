package net.billforward.model;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.billforward.BillForwardClient;
import net.billforward.exception.BillforwardException;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestBase {
	static BillForwardClient m_BfClient = null;
	@BeforeClass
	public static void setUp() {
		//m_BfClient = BillForwardClient.makeDefaultClient("API_TOKEN", "https://api-sandbox.billforward.net/2014.251.0");
		m_BfClient = BillForwardClient.makeDefaultClient("6a5e3c60-6fa1-413f-aa5b-c241d90cd226", "http://local.billforward.net:8080/RestAPI");
		
		System.setProperty("http.proxyHost", "127.0.0.1");
		System.setProperty("https.proxyHost", "127.0.0.1");
		System.setProperty("http.proxyPort", "8855");
		System.setProperty("https.proxyPort", "8855");
	}
	
	@Test
	public void testAPIBase() throws BillforwardException {
		assertEquals("http://local.billforward.net:8080/RestAPI", m_BfClient.getApiUrl());
	}
	
	public String getResourceData(String path) {
		BufferedReader jsonSource = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/net/billforward/" + path)));

		StringBuilder builder = new StringBuilder();
		String aux = "";

		try {
			while ((aux = jsonSource.readLine()) != null) {
			    builder.append(aux);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return builder.toString();
	}
}
