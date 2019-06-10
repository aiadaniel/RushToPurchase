package com.platform.dto;

import java.io.Serializable;

//DTO:web层传递数据用
public class PurchaseResult<T> implements Serializable {

    private static final long serialVersionUID = -460838879500134613L;

    private boolean success;

    private T data;

    private String error;

    public PurchaseResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public PurchaseResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "PurchaseResult{" +
                "success=" + success +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
