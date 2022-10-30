package com.lb.observers;

import com.lb.models.Book;

public class Subscriber {
    private String emailId;

    public Subscriber(String emailId) {
        this.emailId = emailId;
    }

    public void update(Book book){
        System.out.println("Book is available now ");
    }

}
