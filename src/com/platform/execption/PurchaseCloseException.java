package com.platform.execption;

public class PurchaseCloseException extends RushPurchaseException {

    private static final long serialVersionUID = -3405467897058989439L;

	public PurchaseCloseException(String message) {
        super(message);
    }

    public PurchaseCloseException(String message, Throwable cause) {
        super(message, cause);
    }

}
