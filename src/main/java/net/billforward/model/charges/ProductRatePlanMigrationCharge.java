package net.billforward.model.charges;

import net.billforward.model.ResourcePath;

public class ProductRatePlanMigrationCharge extends Charge {

	@Override
	protected ResourcePath getResourcePath() {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getChargeTypeName() {
		return "ProductRatePlanMigration";
	}

}
