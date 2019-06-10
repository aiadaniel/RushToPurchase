package com.platform.execption;

/**
 * 重复抢购异常(运行期异常)
 * RuntimeException 不需要try/catch 而且Spring 的声明式事务只接收RuntimeException回滚策略.
 */
public class RepeatPurchaseException extends RushPurchaseException {

    private static final long serialVersionUID = 4391056856610279032L;

    public RepeatPurchaseException(String message) {
        super(message);
    }

    public RepeatPurchaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
