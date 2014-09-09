package net.billforward.model;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;


public abstract class InsertableEntity<TEntityType extends BillingEntity> extends BillingEntity {
	
	public InsertableEntity() {
		
	}
	
	public InsertableEntity(BillForwardClient client_) {
		super(client_);
	}

	protected static <TStaticEntityType  extends BillingEntity> TStaticEntityType[] create(TStaticEntityType entity, ResourcePath path) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(entity, path, null);
	}
	

	protected static <TStaticEntityType  extends BillingEntity> TStaticEntityType[] create(TStaticEntityType entity, ResourcePath path, String extraPath) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		String fullRoute =  path.getPath();
	
		if(extraPath != null) {
			String apiRoute = path.getPath();
			String endPoint = String.format("/%s", extraPath);
			fullRoute = String.format("%s%s", apiRoute, endPoint);
		} 
				
		APIResponse<TStaticEntityType> resp = client.request(BillForwardClient.RequestMethod.POST,fullRoute, entity, path.getResponseType());
		
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		return resp.results;
	}
	
	protected static <TStaticEntityType  extends BillingEntity> TStaticEntityType retire(String ID, ResourcePath path) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		
		String apiRoute = path.getPath();
		String endPoint = String.format("/%s", ID);
		String fullRoute = String.format("%s%s", apiRoute, endPoint);
		

		APIResponse<TStaticEntityType> resp =  client.request(BillForwardClient.RequestMethod.DELETE, fullRoute, null, path.getResponseType());
				
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		TStaticEntityType res = resp.results[0];
		res.setClient(client);
		
		return res;
	}
}
