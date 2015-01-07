package net.billforward.model;

import com.google.gson.annotations.Expose;


public class APIResponse<T> {
	@Expose protected int executionTime;
	@Expose protected T[] results;
	@Expose protected String errorType;
	@Expose protected String errorMessage;
	@Expose protected int errorCode;
	
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
	
	public String getErrorType() {
		return errorType;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
}
