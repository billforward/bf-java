package net.billforward.model.notifications;

import net.billforward.BillForwardClient;

public class NotificationHelper {
	public static Notification parse(String postData) {
		Notification notification = BillForwardClient.GSON.fromJson(postData, Notification.class);
		notification.buildEntity();
		
		return notification;
	}
}
