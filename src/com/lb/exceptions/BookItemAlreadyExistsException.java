package com.lb.exceptions;

public class BookItemAlreadyExistsException extends RuntimeException {
    public BookItemAlreadyExistsException(String s) {
        super(s);
    }
}
