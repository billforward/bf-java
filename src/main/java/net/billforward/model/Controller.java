package net.billforward.model;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

public abstract class Controller<TEntityType extends BillingEntity> {
	protected BillForwardClient m_client;
	
	public Controller(BillForwardClient client_) {
		m_client = client_;
	}

	public TEntityType getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		ResourcePath path = this.getEntityResourcePath();
		
		String apiRoute = path.getPath();
		String endPoint = String.format("/%s", ID);
		String fullRoute = String.format("%s%s", apiRoute, endPoint);
		

		APIResponse<TEntityType> resp =  m_client.request(BillForwardClient.RequestMethod.GET, fullRoute, null, path.getResponseType());
				
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		TEntityType res = resp.results[0];
		res.setClient(m_client);
		
		return res;
	}
	
	public TEntityType[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		ResourcePath path = this.getEntityResourcePath();
		
		String apiRoute = path.getPath();
		String fullRoute = String.format("%s", apiRoute);
		

		APIResponse<TEntityType> resp =  m_client.request(BillForwardClient.RequestMethod.GET, fullRoute, null, path.getResponseType());
				
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		TEntityType[] res = resp.results;
		for(TEntityType ntt : res) {
			ntt.setClient(m_client);
		}
		
		return res;
	}
	
	public abstract ResourcePath getEntityResourcePath();
}
