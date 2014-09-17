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

/**
 * Currently doesn't work on public API. JIRA Raised.
 *
 */
public class AmendmentDiscardAmendment  extends Amendment {
	@SerializedName("@type")
	@Expose protected String amendmentType = AmendmentType.amendmentDiscardAmendment.toString();
	@Expose protected String amendmentToDiscardID;
	
	public String getAmendmentToDiscardID() {
		return amendmentToDiscardID;
	}

	public void setAmendmentToDiscardID(String amendmentToDiscardID) {
		this.amendmentToDiscardID = amendmentToDiscardID;
	}

	public AmendmentDiscardAmendment(BillForwardClient client_) {
		super(client_);
	}
	
	public AmendmentDiscardAmendment() {
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
		resourcePath = new ResourcePath("amendments", "amendments",  new TypeToken<APIResponse<AmendmentDiscardAmendment>>() {}.getType());
	}

	public static AmendmentDiscardAmendment create(AmendmentDiscardAmendment amendment) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(amendment, ResourcePath())[0];
	}
}
