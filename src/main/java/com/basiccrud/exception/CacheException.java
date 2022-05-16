package com.basiccrud.exception;

public class CacheException extends RuntimeException {

    /**
     * OtpCache Exception with error message
     *
     * @param errorMessage error message
     */
    public CacheException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * OtpCache Exception with error message and throwable
     *
     * @param errorMessage error message
     * @param throwable    error
     */
    public CacheException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
