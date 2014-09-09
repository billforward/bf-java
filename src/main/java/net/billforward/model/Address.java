package net.billforward.model;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class Address  extends MutableEntity<Address> {
	@Expose protected String id;
	@Expose protected String profileID;
	@Expose protected String organizationID;
	@Expose protected String addressLine1 = "";
	@Expose protected String addressLine2 = "";
	@Expose protected String addressLine3 = "";
	@Expose protected String city = "";
	@Expose protected String province = "";
	@Expose protected String country = "";
	@Expose protected String postcode = "";
	@Expose protected String landline = "";
	@Expose protected Boolean primaryAddress;
	@Expose protected Boolean deleted;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;
		
	public String getID() {
		return id;
	}
	
	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public Boolean getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(Boolean primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getUpdated() {
		return updated;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public String getCreated() {
		return created;
	}


	public static Address create(Address address) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(address, ResourcePath())[0];
	}
	
	public static Address getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Address[] adds = getByID(ID, ResourcePath());
		return adds[0];
	}
	
	public static Address[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}

	protected static ResourcePath resourcePath;

	public Address(BillForwardClient client_) {
		super(client_);		
	}
	
	protected Address() {
		
	}
	
	public ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	public static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("addresses", "address",  new TypeToken<APIResponse<Address>>() {}.getType());
	}
}
