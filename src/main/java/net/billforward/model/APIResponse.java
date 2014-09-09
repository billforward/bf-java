package net.billforward.model;

import com.google.gson.annotations.Expose;


public class APIResponse<T> {
	@Expose protected int executionTime;
	@Expose protected T[] results;
	
	public int getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(int executionTime) {
		this.executionTime = executionTime;
	}
	public T[] getResults() {
		return results;
	}
	public void setResults(T[] results) {
		this.results = results;
	}
}
