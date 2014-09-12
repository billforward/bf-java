package net.billforward.model;

import java.util.Date;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class UnitOfMeasure extends MutableEntity<UnitOfMeasure> {
	@Expose protected String id;
	@Expose protected String name;
	@Expose protected String organizationID;
	@Expose protected String displayedAs;
	@Expose protected boolean deleted;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;
	@Expose protected String roundingScheme;

	public String getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayedAs() {
		return displayedAs;
	}

	public void setDisplayedAs(String displayedAs) {
		this.displayedAs = displayedAs;
	}

	public void setRoundingScheme(RoundingScheme roundingScheme) {
		this.roundingScheme = roundingScheme.toString();		
	}

	public String getRoundingSchemeAsString( ) {
		return this.roundingScheme;		
	}

	public RoundingScheme getRoundingScheme( ) {
		return RoundingScheme.valueOf(this.roundingScheme);		
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public Date getUpdated() {
		return updated;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public Date getCreated() {
		return created;
	}
	
	public static UnitOfMeasure create(UnitOfMeasure unitOfMeasure) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return unitOfMeasure;
	}	
	
	public static UnitOfMeasure getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		UnitOfMeasure[] uoms = getByID(ID, ResourcePath());
		return uoms[0];
	}
	
	public static UnitOfMeasure[] getAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return getAll(ResourcePath());
	}
	
	protected static ResourcePath resourcePath;
	
	public UnitOfMeasure(BillForwardClient client_) {
		super(client_);		
	}
	
	public UnitOfMeasure() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("units-of-measure", "units-of-measure",  new TypeToken<APIResponse<UnitOfMeasure>>() {}.getType());
	}
	
	public enum RoundingScheme {
		UP,
		DOWN,
		HALF_EVEN,
		HALF_ODD
	}
}
