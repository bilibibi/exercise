package com.value.auto.exception;


public abstract class BaseException extends RuntimeException {
    private ErrorCode errorCode;

    /**
     * @param errorCode
     * @param message
     */
    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * @param errorCode
     * @param cause
     */
    public BaseException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * @param errorCode
     * @param message
     * @param cause
     */
    public BaseException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * @return
     */
    public String getErrorCode() {
        return errorCode.toString();
    }

    /**
     * @param errorCode
     */
    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    /* 
     * @Override
     * (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    public String toString() {
        return "ErrorCode=" + errorCode + ", class=" + super.toString();
    }
}
