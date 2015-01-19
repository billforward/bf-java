package net.billforward.model;

import java.lang.reflect.Field;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class BillingEntity {
	
	protected BillForwardClient m_client;
	
	public BillForwardClient getClient() {
		return m_client;
	}
	
	public void setClient(BillForwardClient client_) {
		m_client = client_;
	}

	public BillingEntity() {
	}
	
	public BillingEntity(BillForwardClient client_) {
		m_client = client_;
	}



	protected static <TStaticEntityType  extends BillingEntity> TStaticEntityType[] getByIDPostPath(String ID, String postPath, ResourcePath path) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		
		String apiRoute = path.getPath();
		String endPoint = String.format("/%s/%s", ID, postPath);
		String fullRoute = String.format("%s%s", apiRoute, endPoint);
		

		APIResponse<TStaticEntityType> resp =  client.request(BillForwardClient.RequestMethod.GET, fullRoute, null, path.getResponseType());
				
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		
		for(TStaticEntityType res : resp.results) {
			res.setClient(client);
		}
		
		return resp.results;
	}
	
	protected static <TStaticEntityType  extends BillingEntity> TStaticEntityType[] getByID(String ID, String prePath, ResourcePath path) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return BillingEntity.getByID(ID, prePath, path, null);
	}
	
	protected static <TStaticEntityType  extends BillingEntity> TStaticEntityType[] getByID(String ID, String prePath, ResourcePath path, String postfixPath) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		
		String apiRoute = path.getPath();
		String endPoint = String.format("/%s/%s", prePath, ID);
		String fullRoute = String.format("%s%s", apiRoute, endPoint);
		
		if(postfixPath != null) {
			 fullRoute = String.format("%s/%s", fullRoute, postfixPath);
		}

		APIResponse<TStaticEntityType> resp =  client.request(BillForwardClient.RequestMethod.GET, fullRoute, null, path.getResponseType());
				
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		
		for(TStaticEntityType res : resp.results) {
			res.setClient(client);
		}
		
		return resp.results;
	}
	
	protected static <TStaticEntityType  extends BillingEntity> TStaticEntityType[] getByID(String ID, ResourcePath path) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return BillingEntity.getByID(ID, path, null);
	}
	
	protected static <TStaticEntityType  extends BillingEntity> TStaticEntityType[] getByID(String ID, ResourcePath path, String postfixPath) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		
		String apiRoute = path.getPath();
		String endPoint = String.format("/%s", ID);
		String fullRoute = String.format("%s%s", apiRoute, endPoint);
		
		if(postfixPath != null) {
			 fullRoute = String.format("%s/%s", fullRoute, postfixPath);
		}

		
		APIResponse<TStaticEntityType> resp =  client.request(BillForwardClient.RequestMethod.GET, fullRoute, null, path.getResponseType());
				
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}

		for(TStaticEntityType res : resp.results) {
			res.setClient(client);
		}
		
		return resp.results;
	}

	protected static <TStaticEntityType  extends BillingEntity> TStaticEntityType[] getAll(ResourcePath path) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
		
		String apiRoute = path.getPath();
		String fullRoute = String.format("%s", apiRoute);
		

		APIResponse<TStaticEntityType> resp =  client.request(BillForwardClient.RequestMethod.GET, fullRoute, null, path.getResponseType());
				
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		TStaticEntityType[] res = resp.results;
		for(TStaticEntityType ntt : res) {
			ntt.setClient(client);
		}
		
		return res;
	}
	

	protected static <TStaticEntityType  extends BillingEntity> TStaticEntityType[] getAll(ResourcePath path, String explicitPath) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		BillForwardClient client = BillForwardClient.getClient();
				

		APIResponse<TStaticEntityType> resp =  client.request(BillForwardClient.RequestMethod.GET, explicitPath, null, path.getResponseType());
				
		if(resp == null || resp.results == null || resp.results.length < 1) {
			return null;
		}
		TStaticEntityType[] res = resp.results;
		for(TStaticEntityType ntt : res) {
			ntt.setClient(client);
		}
		
		return res;
	}
	
	public static final Gson PRETTY_PRINT_GSON = new GsonBuilder()
			.setPrettyPrinting()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
			.excludeFieldsWithoutExposeAnnotation()
			.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
			.create();
		
	@Override public String toString() {
		return String.format("#%s@%s id=%s# JSON: %s",
							this.getClass().getName(),
							System.identityHashCode(this),
							this.getIDString(),
							PRETTY_PRINT_GSON.toJson(this));
	}

	private Object getIDString() {
		try {
			Field idField = this.getClass().getDeclaredField("id");
			return idField.get(this);
		} catch (SecurityException e) {
			return "";
		} catch (NoSuchFieldException e) {
			return "";
		} catch (IllegalArgumentException e) {
			return "";
		} catch (IllegalAccessException e) {
			return "";
		}
	}

	protected abstract ResourcePath getResourcePath();
}
