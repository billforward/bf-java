package net.billforward.model;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.util.Scanner;
import net.billforward.exception.BillforwardException;
import net.billforward.model.Invoice.InvoiceState;
import net.billforward.model.notifications.InvoiceNotification;
import net.billforward.model.notifications.Notification;
import net.billforward.model.notifications.Notification.NotificationAction;
import net.billforward.model.notifications.NotificationHelper;
import net.billforward.model.notifications.Notification.NotificationDomain;

import org.junit.Test;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpHandlerTest extends TestBase {
	@Test
	public void getByState() throws BillforwardException, IOException, InterruptedException {
		
		 HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
	        server.createContext("/test", new MyHandler());
	        server.setExecutor(null); // creates a default executor
	        server.start();
	        
	        Thread.sleep(10000000);
//	        
//		//--Get Invoice by State
//		InvoiceState invoiceState = InvoiceState.Unpaid;
//		
//		Invoice[] invoices = Invoice.getByState(invoiceState);
//		
//		for(Invoice invoice : invoices) {
//			assertEquals(invoiceState, invoice.getState());		
//			System.out.println(invoice.toString());	
//		}
	}
	
	static class MyHandler implements HttpHandler {
	        public void handle(HttpExchange t) throws IOException {
	        	InputStream inputStream = t.getRequestBody();
	        	StringWriter writer = new StringWriter();
	        	String inputStreamString = new Scanner(inputStream,"UTF-8").useDelimiter("\\A").next();
	        	
	        	System.out.println(inputStreamString.toString());
	        	
	        	Notification notification = null;
	        	try {
	        		notification = NotificationHelper.parse(inputStreamString.toString());
	        	} catch(Exception ex) {
	        		return;
	        	}
	        			
	        	if(notification.getDomain() == NotificationDomain.Invoice && notification.getAction() == NotificationAction.Pending) {
	        		InvoiceNotification invoiceNotification = (InvoiceNotification)notification;
	        		
	        		Invoice invoice = invoiceNotification.getInvoice();
	        		
	        		try {
	        			int usage = 10;
	        			invoice.setUsage(usage);
	        			
						invoice.recalculate(InvoiceState.Unpaid);
					} catch (Exception e) {
						e.printStackTrace();
					}
	        	}
	        	
	        	
	            String response = "This is the response";
	            t.sendResponseHeaders(200, response.length());
	            OutputStream os = t.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	        }
	 }
}
