package net.billforward.model.amendments;


import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.APIResponse;
import net.billforward.model.Invoice.InvoiceState;
import net.billforward.model.amendments.Amendment.AmendmentState;
import net.billforward.model.ResourcePath;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class InvoiceRecalculationAmendment extends Amendment {
	@SerializedName("@type")
	@Expose protected String amendmentType = AmendmentType.InvoiceRecalculationAmendment.toString();
	@Expose String invoiceID;
	@Expose String newInvoiceState;
	@Expose Boolean includeInvoicedChargesOnly = true;
	@Expose ChargeAssociationStrategy associateCharges = ChargeAssociationStrategy.Associated;
	
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

	public String getNewInvoiceStateAsString() {
		return newInvoiceState;
	}
	
	public InvoiceState getNewInvoiceState() {
		return InvoiceState.valueOf(newInvoiceState);
	}

	public void setNewInvoiceState(InvoiceState newInvoiceState) {
		this.newInvoiceState = newInvoiceState.toString();
	}	
	
	public ChargeAssociationStrategy getAssociateCharges() {
		return associateCharges;
	}

	public void setAssociateCharges(ChargeAssociationStrategy associateCharges) {
		this.associateCharges = associateCharges;
	}

	public static InvoiceRecalculationAmendment create(InvoiceRecalculationAmendment amendment) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(amendment, ResourcePath())[0];
	}
	
	public InvoiceRecalculationAmendment(BillForwardClient client_) {
		super(client_);
	}
	
	public InvoiceRecalculationAmendment() {
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
		resourcePath = new ResourcePath("amendments", "amendments",  new TypeToken<APIResponse<InvoiceRecalculationAmendment>>() {}.getType());
	}
	
	public static InvoiceRecalculationAmendment get(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		InvoiceRecalculationAmendment[] amendments = getByID(ID, ResourcePath());;
		
		if(amendments == null || amendments.length == 0) return null;
		
		return amendments[0];
	}
	
	@SuppressWarnings("unchecked")
	public InvoiceRecalculationAmendment sync() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		
		InvoiceRecalculationAmendment amendment = this;
		int maxQuery = 60;
		while(amendment.getState() == AmendmentState.Pending) {
			amendment = InvoiceRecalculationAmendment.get(amendment.getID());
			
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
