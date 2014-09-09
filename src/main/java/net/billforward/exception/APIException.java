package net.billforward.exception;

public class APIException extends BillforwardException {

	private static final long serialVersionUID = 1L;

	public APIException(String message, Throwable e) {
		super(message, e);
	}
}
