package com.lb.models;

import com.lb.console.Print;
import com.lb.exceptions.ExceedBookLimitException;

import java.util.Date;

public class Librarian {

    private final SystemProxy systemProxy;
    private final Print log;

    public Librarian(SystemProxy systemProxy, Print log) {
        this.systemProxy = systemProxy;
        this.log = log;
    }

    public void createUser(User addingBy, User user){
        systemProxy.addUser(addingBy, user);
    }

    public void addBook(User user, Book book){
        systemProxy.addBook(user, book);
    }

    public void checkOutBook(BookItem bookItem, LibraryCard libraryCard){
        if(libraryCard.getAvailableBookCount() <= 0)
            throw new ExceedBookLimitException("Available book is zero");
        Checkout checkout = new Checkout(bookItem, libraryCard.getUser(), new Date());
        libraryCard.addCheckoutBook(checkout);
        libraryCard.updateAvailableBookCount(libraryCard.getAvailableBookCount()-1);
        systemProxy.issueLibraryCard(libraryCard);
        systemProxy.markUnAvailableBookItem(bookItem);
    }

    public void checkInBook(BookItem bookItem, LibraryCard libraryCard, Long fine, Date checkInDate){
        if(fine > 0)
            log.info("Book is damaged : " + bookItem.getBarCode());
        Checkout checkout = libraryCard.getCheckout(bookItem.getBarCode());
        libraryCard.checkInBook(bookItem.getBarCode());
        fine += systemProxy.getFine(checkout, checkInDate);
        libraryCard.updateFine(fine.intValue());
        systemProxy.issueLibraryCard(libraryCard);
        systemProxy.markBookItemToAvailable(bookItem);
        log.info("Informing subscribers on book arrival ");
        systemProxy.updateSubscribedOnBookArrival(bookItem.getBook());
        log.info("Successfully checked in book " );
    }

    public void createBookItem(BookItem bookItem){
        systemProxy.addBookItem(bookItem);
    }

    public void addAuthor(Author author){
        systemProxy.addAuthor(author);
    }

    public void addBookToAuthor(Book book, Author author){
        systemProxy.addBookToAuthor(book, author);
    }

    public void assignLibraryCard(LibraryCard libraryCard){
        systemProxy.issueLibraryCard(libraryCard);
    }

}
