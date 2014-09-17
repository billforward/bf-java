package net.billforward.amendments;

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

public class CancellationAmendment extends Amendment {
	@SerializedName("@type")
	@Expose protected String amendmentType = AmendmentType.cancellationAmendment.toString();
	@Expose protected String source = "";
	@Expose protected String serviceEnd;
	@Expose protected SubscriptionCancellation subscriptionCancellation;
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public String getServiceEndAsString() {
		return serviceEnd;
	}
	
	public ServiceEndState getServiceEnd() {
		return ServiceEndState.valueOf(serviceEnd);
	}

	public void setServiceEnd(ServiceEndState serviceEnd) {
		this.serviceEnd = serviceEnd.toString();
	}

	public SubscriptionCancellation getSubscriptionCancellation() {
		return subscriptionCancellation;
	}

	public CancellationAmendment(BillForwardClient client_) {
		super(client_);
	}
	
	public CancellationAmendment() {
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
		resourcePath = new ResourcePath("amendments", "amendments",  new TypeToken<APIResponse<CancellationAmendment>>() {}.getType());
	}

	public static CancellationAmendment create(CancellationAmendment amendment) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(amendment, ResourcePath())[0];
	}
	
	public enum ServiceEndState {
		Immediate,
		AtPeriodEnd
	}
}
