package com.platform.execption;

/**
 * 抢购相关业务异常
 */
public class RushPurchaseException extends RuntimeException {

    private static final long serialVersionUID = 2847595230286582067L;

	public RushPurchaseException(String message) {
        super(message);
    }

    public RushPurchaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
