package net.billforward.model;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import net.billforward.exception.BillforwardException;
import net.billforward.model.Invoice.InvoiceState;
import net.billforward.model.amendments.Amendment;
import net.billforward.model.amendments.Amendment.AmendmentState;
import net.billforward.model.amendments.ServiceEndAmendment;
import net.billforward.model.notifications.AmendmentNotification;
import net.billforward.model.notifications.FieldChange;
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
		
		 HttpServer server = HttpServer.create(new InetSocketAddress(8099), 0);
	        server.createContext("/test", new EchoHandler());
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

	
	static class EchoHandler implements HttpHandler {
	        public void handle(HttpExchange t) throws IOException {
	        	InputStream inputStream = t.getRequestBody();
	        	StringWriter writer = new StringWriter();
	        	String inputStreamString = new Scanner(inputStream,"UTF-8").useDelimiter("\\A").next();
	        		        	
	        	Notification notification = NotificationHelper.parse(inputStreamString.toString());
	        	if(notification instanceof AmendmentNotification) { 
	        		AmendmentNotification amendmentNotification = (AmendmentNotification)notification;
	        		Amendment amendment = amendmentNotification.getAmendment();
	        		
	        		if(amendment instanceof ServiceEndAmendment) {
	        			ServiceEndAmendment serviceEndAmendment = (ServiceEndAmendment)amendment;	        			
	        			if(amendment.getState().equals(AmendmentState.Succeeded)) {
	        				//Cancel here
	        				Date cancelledTime = serviceEndAmendment.getActionedTime();
	        				System.out.println(cancelledTime);
	        			}
	        		}
	        	}
	        	
//	        	List<FieldChange> changes = notification.getAuditFieldChanges();
//	        	for(FieldChange change : changes) {
//	        		if(change.getAttributeName().equals("currentPeriodStart")) {
//	        			System.out.println("currentPeriodStart");
//	        			System.out.println(change.getNewValueAsDate());
//	        			System.out.println(change.getPreviousValueAsDate());
//	        		}
//	        	}
	        	
	            String response = "This is the response";
	            t.sendResponseHeaders(200, response.length());
	            OutputStream os = t.getResponseBody();
	            os.write(response.getBytes());
	            os.close();
	        }
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
