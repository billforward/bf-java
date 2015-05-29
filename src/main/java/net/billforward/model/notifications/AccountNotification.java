package net.billforward.model.notifications;

import net.billforward.BillForwardClient;
import net.billforward.model.Account;

import com.google.gson.annotations.Expose;

public class AccountNotification extends Notification {
	@Expose protected Account account;
	
	public AccountNotification() {
		super();
	}
	
	public Account getAccount() {
		return account;
	}

	@Override
	protected void buildEntity() {
		account = BillForwardClient.GSON_NOTIFICATION_ENTITY.fromJson(this.entity, Account.class);
	}
}
