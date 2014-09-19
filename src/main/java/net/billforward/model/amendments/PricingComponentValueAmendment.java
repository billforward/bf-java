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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/***
 * Coming in new version of API 
 *
 */
abstract class PricingComponentValueAmendment extends Amendment {
	@SerializedName("@type")
	@Expose protected String amendmentType = AmendmentType.pricingComponentValueAmendment.toString();
	@Expose int oldValue;
	@Expose int newValue;
	@Expose String mode;
	@Expose String invoicingType;
	@Expose String invoiceID;
	@Expose String logicalComponentID;
	@Expose Date nextExecutionAttempt;	

	public enum ValueChangeMode {
		immediate,
		delayed
	}
	
	public enum InvoicingType {
		Immediate,
		Aggregated
	}
	
	public PricingComponentValueAmendment(BillForwardClient client_) {
		super(client_);
	}
	
	public PricingComponentValueAmendment() {
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
		resourcePath = new ResourcePath("amendments", "amendments",  new TypeToken<APIResponse<PricingComponentValueAmendment>>() {}.getType());
	}

	public static PricingComponentValueAmendment create(PricingComponentValueAmendment amendment) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(amendment, ResourcePath())[0];
	}
}
