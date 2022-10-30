package com.lb.models;

import com.lb.console.Print;
import com.lb.enums.BookSubject;
import com.lb.enums.Role;
import com.lb.exceptions.UnAuthorizedException;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class SystemProxy implements System {

    private System system;
    private final Print log;

    public SystemProxy(Print print) {
        this.log = print;
        if (system == null)
            system = new SystemImpl(print);
    }

    public void addUser(User byUser, User user) {
        checkRole(byUser);
        log.info("Adding user " + user.getName());
        system.addUser(user);
    }

    public void addBook(User byUser, Book book) {
        checkRole(byUser);
        log.info("Adding book " + book.getBookTitle());
        system.addBook(book);
    }

    public void addBookItem(BookItem bookItem) {
        log.info("Adding BookItem " + bookItem.getBarCode());
        system.addBookItem(bookItem);
    }

    public void markBookItemToAvailable(BookItem bookItem) {
        log.info("Marking BookItem Available " + bookItem.getBarCode());
        system.markBookItemToAvailable(bookItem);
    }

    public void markUnAvailableBookItem(BookItem bookItem) {
        log.info("Marking BookItem UnAvailable " + bookItem.getBarCode());
        system.markUnAvailableBookItem(bookItem);
    }

    public Long getFine(Checkout checkout, Date checkInDate) {
        log.info("Fetching fine for " + checkout.getBookItem().getBarCode());
        return system.getFine(checkout, checkInDate);
    }

    private void checkRole(User user) {
        if (!Role.LIBRARIAN.equals(user.getRole()))
            throw new UnAuthorizedException("UnAuthorized !!! Please check role");
    }

    @Override
    public Book searchBookByTitle(String title) {
        log.info("Searching Book by title !!!" + title);
        return system.searchBookByTitle(title);
    }

    @Override
    public List<Book> searchBookByCategory(BookSubject category) {
        log.info("Searching Book by category !!!" + category);
        return system.searchBookByCategory(category);
    }

    @Override
    public List<Book> searchBookByPublishDate(Date publishDate) {
        log.info("Searching Book by publishDate !!!" + publishDate);
        return system.searchBookByPublishDate(publishDate);
    }

    @Override
    public List<Book> searchBookByAuthor(Integer authorId) {
        log.info("Searching Book by authorId !!!" + authorId);
        return system.searchBookByAuthor(authorId);
    }

    @Override
    public void addAuthor(Author author) {
        log.info("Adding author !!!" + author.getAuthorId());
        system.addAuthor(author);
    }

    @Override
    public void addBookToAuthor(Book book, Author author) {
        log.info("Adding book to author !!!" + author.getAuthorId());
        system.addBookToAuthor(book, author);
    }

    @Override
    public void issueLibraryCard(LibraryCard libraryCard) {
        log.info("assigning libraryCard to user " + libraryCard.getUser().getName());
        system.issueLibraryCard(libraryCard);
    }

    @Override
    public List<BookItem> getBookItemsByBookId(Integer bookId) {
        log.info("Searching available book item by book id");
        return system.getBookItemsByBookId(bookId);
    }

    @Override
    public Set<BookItem> checkOutBookItemByUserId(String libCardBarCode) {
        log.info("Get all book taken by user using libraryCard info");
        return system.checkOutBookItemByUserId(libCardBarCode);
    }

    @Override
    public LibraryCard getLibraryCardByBarCode(String libCardBarCode) {
        log.info("Fetching libraryCard info by libCardBarCode");
        return system.getLibraryCardByBarCode(libCardBarCode);
    }

    @Override
    public void getBookNotAvailableBookItem(LibraryCard libraryCard, Book book) {
        system.getBookNotAvailableBookItem(libraryCard, book);
    }

    @Override
    public void updateSubscribedOnBookArrival(Book book) {
        system.updateSubscribedOnBookArrival(book);
    }
}
