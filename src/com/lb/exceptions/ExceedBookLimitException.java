package com.lb.exceptions;

public class ExceedBookLimitException extends RuntimeException {
    public ExceedBookLimitException(String s) {
        super(s);
    }
}
