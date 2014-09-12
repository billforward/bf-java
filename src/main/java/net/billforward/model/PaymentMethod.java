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

public class PaymentMethod extends MutableEntity<PaymentMethod> {
	@Expose protected String id;
	@Expose protected String accountID;
	@Expose protected String organizationID;
	@Expose protected String name;
	@Expose protected String description;
	@Expose protected String cardHolderName;
	@Expose protected String expiryDate;
	@Expose protected String cardType;
	@Expose protected String gateway;
	@Expose protected String linkID = "";
	@Expose protected int priority;
	@Expose protected Boolean reusable;
	@Expose protected Boolean deleted;
	@Expose protected Date updated;
	@Expose protected String changedBy;
	@Expose protected Date created;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardTypeAsString() {
		return cardType;
	}

	public CardType getCardType() {
		return CardType.valueOf(cardType);
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType.toString();
	}

	public String getPaymentGatewayAsString() {
		return gateway;
	}

	public PaymentGateway getGateway() {
		return PaymentGateway.valueOf(gateway);
	}
	
	public void setGateway(PaymentGateway cardType) {
		this.gateway = cardType.toString();
	}

	public String getLinkID() {
		return linkID;
	}
	
	public void setLinkID(String linkID) {
		this.linkID = linkID;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Boolean getReusable() {
		return reusable;
	}

	public void setReusable(Boolean reusable) {
		this.reusable = reusable;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
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

	public static PaymentMethod create(PaymentMethod paymentMethod) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return create(paymentMethod, ResourcePath())[0];
	}
	
	public static PaymentMethod getByID(String ID) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		PaymentMethod[] paymentMethods = getByID(ID, ResourcePath());
		return paymentMethods[0];
	}	
	
	protected static ResourcePath resourcePath;

	public PaymentMethod(BillForwardClient client_) {
		super(client_);		
	}
	
	public PaymentMethod() {
		super();
	}
	
	public ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	public static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("payment-methods", "paymentMethod",  new TypeToken<APIResponse<PaymentMethod>>() {}.getType());
	}
}
