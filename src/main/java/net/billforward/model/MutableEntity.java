package net.billforward.model;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

public abstract class MutableEntity<TEntityType extends BillingEntity> extends InsertableEntity<TEntityType> {
	
	public MutableEntity() {
		
	}
	
	public MutableEntity(BillForwardClient client_) {
		super(client_);
	}
	
	public TEntityType save() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		ResourcePath path = this.getResourcePath();
		String endPoint = path.getPath();
		
		if(m_client == null) {
			m_client = BillForwardClient.getClient();
		}
				
		@SuppressWarnings("unchecked")
		APIResponse<TEntityType> resp = m_client.request(BillForwardClient.RequestMethod.PUT, endPoint, (TEntityType)this, path.getResponseType());
		
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		return resp.results[0];
	}
}
