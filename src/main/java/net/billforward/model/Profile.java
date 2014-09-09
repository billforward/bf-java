package net.billforward.model;

import java.util.ArrayList;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

/**
 * A profile contains the details such as contact data associated with an account.
 * 
 * @see <a href="https://app.billforward.net/api/#/method/profiles">https://app.billforward.net/api/#/method/profiless</a>
 * @author Ian
 *
 */
public class Profile extends MutableEntity<Profile> {
	@Expose protected String id;
	@Expose protected String accountID;
	@Expose protected String organizationID;
	@Expose protected String email;
	@Expose protected String firstName;
	@Expose protected String lastName;
	@Expose protected String mobile;
	@Expose protected String landline;
	@Expose protected String dob;
	@Expose protected String additionalInformation;
	@Expose protected String updated;
	@Expose protected String changedBy;
	@Expose protected String created;
	@Expose protected List<Address> addresses = new ArrayList<Address>();
	
	
	public String getID() {
		return id;
	}
	
	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
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

	public List<Address> getAddresses() {
		return addresses;
	}
	
	public static Profile getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Profile[] profiles = getByID(ID, ResourcePath());
		return profiles[0];
	}
	
	public static Profile[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}
	
	protected static ResourcePath resourcePath;

	public Profile(BillForwardClient client_) {
		super(client_);		
	}
	
	protected Profile() {
		
	}

	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("profiles", "profile",  new TypeToken<APIResponse<Profile>>() {}.getType());
	}
}
