package net.billforward.model.amendments;

import com.google.gson.annotations.Expose;

public class ComponentChange {
	@Expose String id;
	@Expose String name;
	@Expose int oldValue;
	@Expose int newValue;
	@Expose String logicalComponentID = "D8524DBE-3C93-48C4-B83D-72A5C0E5CCA0";
	
	public ComponentChange() {
		
	}
	
	public ComponentChange(String name, int newValue) {
		this.name = name;
		this.newValue = newValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNewValue() {
		return newValue;
	}

	public void setNewValue(int newValue) {
		this.newValue = newValue;
	}

	public String getLogicalComponentID() {
		return logicalComponentID;
	}

	public void setLogicalComponentID(String logicalComponentID) {
		this.logicalComponentID = logicalComponentID;
	}

	public String getId() {
		return id;
	}

	public int getOldValue() {
		return oldValue;
	}
}
