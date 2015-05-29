package net.billforward.model.amendments;

import com.google.gson.reflect.TypeToken;

import net.billforward.model.APIResponse;
import net.billforward.model.ResourcePath;

public class ServiceEndAmendment extends Amendment {

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath resourcePath;
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
		
	static {
		resourcePath = new ResourcePath("amendments", "amendments",  new TypeToken<APIResponse<ServiceEndAmendment>>() {}.getType());
	}

}
