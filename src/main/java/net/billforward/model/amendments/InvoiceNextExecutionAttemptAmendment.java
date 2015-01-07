package net.billforward.model.amendments;

import java.util.Date;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.APIResponse;
import net.billforward.model.ResourcePath;
import net.billforward.model.amendments.Amendment.AmendmentState;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class InvoiceNextExecutionAttemptAmendment extends Amendment {
	@SerializedName("@type")
	@Expose protected String amendmentType = AmendmentType.InvoiceNextExecutionAttemptAmendment.toString();
	@Expose String invoiceID;
	@Expose Date nextExecutionAttempt;	

	public String getAmendmentTypeAsString() {
		return amendmentType;
	}
	
	public AmendmentType getAmendmentType() {
		return AmendmentType.valueOf(amendmentType);
	}
	
	public String getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(String invoiceID) {
		this.invoiceID = invoiceID;
	}

	public Date getNextExecutionAttempt() {
		return nextExecutionAttempt;
	}

	public void setNextExecutionAttempt(Date nextExecutionAttempt) {
		this.nextExecutionAttempt = nextExecutionAttempt;
	}

	public InvoiceNextExecutionAttemptAmendment(BillForwardClient client_) {
		super(client_);
	}
	
	public InvoiceNextExecutionAttemptAmendment() {
		super();
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath resourcePath;
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
		
	static {
		resourcePath = new ResourcePath("amendments", "amendments",  new TypeToken<APIResponse<InvoiceNextExecutionAttemptAmendment>>() {}.getType());
	}

	public static InvoiceNextExecutionAttemptAmendment create(InvoiceNextExecutionAttemptAmendment amendment) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(amendment, ResourcePath())[0];
	}
	

	public static InvoiceNextExecutionAttemptAmendment get(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		InvoiceNextExecutionAttemptAmendment[] amendments = getByID(ID, ResourcePath());;
		
		if(amendments == null || amendments.length == 0) return null;
		
		return amendments[0];
	}
	
	@SuppressWarnings("unchecked")
	public InvoiceNextExecutionAttemptAmendment sync() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		
		InvoiceNextExecutionAttemptAmendment amendment = this;
		int maxQuery = 60;
		while(amendment.getState() == AmendmentState.Pending) {
			amendment = InvoiceNextExecutionAttemptAmendment.get(amendment.getID());
			
			if(--maxQuery <= 0) break;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
		
		return amendment;
	}
}
