package com.spring.batch.multipledatasoures.demo.exception;

public class CommonBatchServiceException extends RuntimeException {
    public CommonBatchServiceException() {
        super();
    }

    public CommonBatchServiceException(String s) {
        super(s);
    }

    public CommonBatchServiceException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public CommonBatchServiceException(Throwable throwable) {
        super(throwable);
    }
}
