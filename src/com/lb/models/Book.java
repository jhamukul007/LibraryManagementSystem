package com.lb.models;

import com.lb.enums.BookSubject;
import com.lb.observers.Subscriber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Book {
    private Integer bookId;
    private String bookTitle;
    private BookSubject bookSubject;
    private Date publishedDate;
    private List<User> subscribers;

    public Book(Integer bookId, String bookTitle, BookSubject bookSubject, Date publishedDate) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookSubject = bookSubject;
        this.publishedDate = publishedDate;
        this.subscribers = new ArrayList<>();
    }

    public Integer getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public BookSubject getBookSubject() {
        return bookSubject;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookSubject=" + bookSubject +
                ", publishedDate=" + publishedDate +
                '}';
    }

    public void addSubscriber(User user){
        subscribers.add(user);
    }

    public void removeSubscriber(User user){
        subscribers.remove(user);
    }

    public void notifySubscriber(){
        for(User subscriber: subscribers){
            java.lang.System.out.println("Notifying subscriber " + subscriber.getEmail() + " on book arrival");
            subscriber.notifyOnBookArrival(this);
        }
    }

}
