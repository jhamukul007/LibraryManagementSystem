package com.lb.exceptions;

public class AuthorAleadyExistsException extends RuntimeException {
    public AuthorAleadyExistsException(String s) {
        super(s);
    }
}
