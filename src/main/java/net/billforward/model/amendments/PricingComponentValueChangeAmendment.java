package net.billforward.model.amendments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.APIResponse;
import net.billforward.model.InvoicingType;
import net.billforward.model.ResourcePath;
import net.billforward.model.ValueChangeMode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class PricingComponentValueChangeAmendment extends Amendment {
	@SerializedName("@type")
	@Expose protected String amendmentType = AmendmentType.PricingComponentValueChangeAmendment.toString();
	
	@Expose ValueChangeMode mode = ValueChangeMode.immediate;
	@Expose InvoicingType invoicingType = InvoicingType.Aggregated;

	@Expose List<ComponentChange> componentChanges = new ArrayList<ComponentChange>();
	
	
	public String getAmendmentTypeAsString() {
		return amendmentType;
	}
	
	public AmendmentType getAmendmentType() {
		return AmendmentType.valueOf(amendmentType);
	}

	public ValueChangeMode getMode() {
		return mode;
	}

	public void setMode(ValueChangeMode mode) {
		this.mode = mode;
	}

	public InvoicingType getInvoicingType() {
		return invoicingType;
	}

	public void setInvoicingType(InvoicingType invoicingType) {
		this.invoicingType = invoicingType;
	}

	public List<ComponentChange> getComponentChanges() {
		return Collections.unmodifiableList(componentChanges);
	}
	
	public ComponentChange addComponentChangeByName(String name, int newValue) {
		ComponentChange change = new ComponentChange();
		change.setName(name);
		change.setNewValue(newValue);
		
		componentChanges.add(change);
		
		return change;
	}
	
	public ComponentChange addComponentChangeByComponentID(String componentID, int newValue) {
		ComponentChange change = new ComponentChange();
		change.setLogicalComponentID(componentID);
		change.setNewValue(newValue);
		
		componentChanges.add(change);
		
		return change;
	}


	public void addComponentChange(ComponentChange change) {
		componentChanges.add(change);		
	}
	
	public static PricingComponentValueChangeAmendment create(PricingComponentValueChangeAmendment amendment) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(amendment, ResourcePath())[0];
	}
	
	public static PricingComponentValueChangeAmendment get(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		PricingComponentValueChangeAmendment[] amendments = getByID(ID, ResourcePath());;
		
		if(amendments == null || amendments.length == 0) return null;
		
		return amendments[0];
	}
	
	public PricingComponentValueChangeAmendment(BillForwardClient client_) {
		super(client_);
	}
	
	public PricingComponentValueChangeAmendment() {
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
		resourcePath = new ResourcePath("amendments", "amendments",  new TypeToken<APIResponse<PricingComponentValueChangeAmendment>>() {}.getType());
	}
	
	@SuppressWarnings("unchecked")
	public PricingComponentValueChangeAmendment sync() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		
		PricingComponentValueChangeAmendment amendment = this;
		int maxQuery = 60;
		while(amendment.getState() == AmendmentState.Pending) {
			amendment = PricingComponentValueChangeAmendment.get(amendment.getID());
			
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