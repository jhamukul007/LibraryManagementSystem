package com.lb.exceptions;

public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException(String s) {
        super(s);
    }
}
