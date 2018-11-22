package com.cqut.exception;

public class IllegalOperationException extends Exception {
    public IllegalOperationException(String illegalOperation) {
        super(illegalOperation);
    }
}
