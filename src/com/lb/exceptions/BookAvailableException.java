package com.lb.exceptions;

public class BookAvailableException extends RuntimeException {
    public BookAvailableException(String s) {
        super(s);
    }
}
