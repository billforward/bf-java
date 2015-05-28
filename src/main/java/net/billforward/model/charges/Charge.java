package net.billforward.model.charges;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.billforward.BillForwardClient;
import net.billforward.exception.APIConnectionException;
import net.billforward.exception.APIException;
import net.billforward.exception.AuthenticationException;
import net.billforward.exception.CardException;
import net.billforward.exception.InvalidRequestException;
import net.billforward.model.APIResponse;
import net.billforward.model.BillingEntity;
import net.billforward.model.InvoicingType;
import net.billforward.model.MutableEntity;
import net.billforward.model.ResourcePath;
import net.billforward.model.Subscription;
import net.billforward.model.gateways.GatewayTypeMapping;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public abstract class Charge extends MutableEntity<Charge> {
	@Expose private String id;
	
	@Expose private String crmID;
	
	@Expose private String accountID;
	
	@Expose private String subscriptionID;
	
	@Expose private String subscriptionVersionID;
	
	@Expose private String invoiceID;
	
	@Expose private BigDecimal amount;
	
	@Expose private BigDecimal amountExcludingTax;
	
	@Expose private Date periodStart;
    
	@Expose private Date periodEnd;
    
	@Expose private SubscriptionChargeType type;
	
	@Expose private InvoicingType invoicingType;
	
	@Expose private SubscriptionChargeState state;
	
	@Expose private ChargeType chargeType;
	
	@Expose private String description;
	
	@Expose private boolean trial;
	
	@Expose private int versionNumber;
    
	@Expose private String versionID;
	
	public String getID() {
		return id;
	}



	public String getCrmID() {
		return crmID;
	}



	public String getAccountID() {
		return accountID;
	}



	public String getSubscriptionID() {
		return subscriptionID;
	}



	public String getSubscriptionVersionID() {
		return subscriptionVersionID;
	}



	public String getInvoiceID() {
		return invoiceID;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public BigDecimal getAmountExcludingTax() {
		return amountExcludingTax;
	}



	public Date getPeriodStart() {
		return periodStart;
	}



	public Date getPeriodEnd() {
		return periodEnd;
	}



	public SubscriptionChargeType getType() {
		return type;
	}



	public InvoicingType getInvoicingType() {
		return invoicingType;
	}



	public SubscriptionChargeState getState() {
		return state;
	}



	public ChargeType getChargeType() {
		return chargeType;
	}



	public String getDescription() {
		return description;
	}



	public boolean isTrial() {
		return trial;
	}



	public int getVersionNumber() {
		return versionNumber;
	}



	public String getVersionID() {
		return versionID;
	}

	protected static ResourcePath resourcePath;
	
	public Charge(BillForwardClient client_) {
		super(client_);		
	}
	
	public Charge() {
		
	}
	
	protected ResourcePath getResourcePath() {
		return resourcePath;
	}
	
	protected static ResourcePath ResourcePath() {
		return resourcePath;
	}
	
	static {
		resourcePath = new ResourcePath("charges", "charge",  new TypeToken<APIResponse<Charge>>() {}.getType());
	}

	public static GatewayTypeMapping[] getTypeMappings() {
		List<GatewayTypeMapping> typeMappings = new ArrayList<GatewayTypeMapping>();
		typeMappings.add(new GatewayTypeMapping(ManualCharge.class, ManualCharge.class.getSimpleName()));
		typeMappings.add(new GatewayTypeMapping(PricingComponentCharge.class, PricingComponentCharge.getChargeTypeName()));
		typeMappings.add(new GatewayTypeMapping(ProductRatePlanMigrationCharge.class, ProductRatePlanMigrationCharge.getChargeTypeName()));
		typeMappings.add(new GatewayTypeMapping(CouponCharge.class , CouponCharge.getChargeTypeName()));
		
		return typeMappings.toArray(new GatewayTypeMapping[]{});
	}



	public Charge delete() throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		return retire(id, ResourcePath());
	}
}
