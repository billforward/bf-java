package net.billforward.model.notifications;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class AuditFieldChanges {
	@Expose List<FieldChange> auditFieldChanges = new ArrayList<FieldChange>();

	public List<FieldChange> getAuditFieldChanges() {
		return auditFieldChanges;
	}

	public void setAuditFieldChanges(List<FieldChange> auditFieldChanges) {
		this.auditFieldChanges = auditFieldChanges;
	}
}
