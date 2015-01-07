package net.billforward.model.amendments;

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

public class IssueInvoiceAmendment extends Amendment {
	@SerializedName("@type")
	@Expose protected String amendmentType = AmendmentType.IssueInvoiceAmendment.toString();
	@Expose String invoiceID;
	
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

	public static IssueInvoiceAmendment create(IssueInvoiceAmendment amendment) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(amendment, ResourcePath())[0];
	}
	
	public IssueInvoiceAmendment(BillForwardClient client_) {
		super(client_);
	}
	
	public IssueInvoiceAmendment() {
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
		resourcePath = new ResourcePath("amendments", "amendments",  new TypeToken<APIResponse<IssueInvoiceAmendment>>() {}.getType());
	}

	public static IssueInvoiceAmendment get(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		IssueInvoiceAmendment[] amendments = getByID(ID, ResourcePath());;
		
		if(amendments == null || amendments.length == 0) return null;
		
		return amendments[0];
	}
	
	@SuppressWarnings("unchecked")
	public IssueInvoiceAmendment sync() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		
		IssueInvoiceAmendment amendment = this;
		int maxQuery = 60;
		while(amendment.getState() == AmendmentState.Pending) {
			amendment = IssueInvoiceAmendment.get(amendment.getID());
			
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