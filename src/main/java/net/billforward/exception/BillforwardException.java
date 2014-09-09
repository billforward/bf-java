package net.billforward.exception;

public abstract class BillforwardException extends Exception {

	public BillforwardException(String message) {
		super(message, null);
	}

	public BillforwardException(String message, Throwable e) {
		super(message, e);
	}

	private static final long serialVersionUID = 1L;

}
