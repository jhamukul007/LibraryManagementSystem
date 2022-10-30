package com.lb.models;

import com.lb.services.SearchBook;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface System extends SearchBook {

    default void addUser(User user){}
    default void addBook(Book book){}
    void addBookItem(BookItem bookItem);
    void markBookItemToAvailable(BookItem bookItem);
    void markUnAvailableBookItem(BookItem bookItem);
    Long getFine(Checkout checkout, Date checkInDate);
    default void addAuthor(Author author){}
    default void addBookToAuthor(Book book, Author author){}
    void issueLibraryCard(LibraryCard libraryCard);
    default List<BookItem> getBookItemsByBookId(Integer bookId){return Collections.emptyList();}
    default Set<BookItem> checkOutBookItemByUserId(String libCardBarCode){return Set.of();}
    LibraryCard getLibraryCardByBarCode(String libCardBarCode);
    void getBookNotAvailableBookItem(LibraryCard libraryCard, Book book);
    void updateSubscribedOnBookArrival(Book book);
}
