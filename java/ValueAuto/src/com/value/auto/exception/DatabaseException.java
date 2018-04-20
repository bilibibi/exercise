package com.value.auto.exception;

public class DatabaseException extends BaseException {
    /**
     * @param message
     */
    public DatabaseException(String message) {
        super(ErrorCode.DATABASE_FAILED,message);
    }

    /**
     * @param cause
     */
    public DatabaseException(Throwable cause) {
        super(ErrorCode.DATABASE_FAILED,cause);
    }

    /**
     * @param message
     * @param cause
     */
    public DatabaseException( String message, Throwable cause) {
        super(ErrorCode.DATABASE_FAILED,message, cause);
    }
}
