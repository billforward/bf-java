package net.billforward.model.gateways;

public class GatewayTypeMapping {
	public Class<?> type;
	public String name;
	
	public GatewayTypeMapping(Class<?> type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	public Class<?> getApiType() {
		return type;
	}
	
	public String getName() {
		return name;
	}	
}
