package com.lb.models;

import com.lb.console.Print;
import com.lb.enums.BookSubject;
import com.lb.exceptions.AuthorAleadyExistsException;
import com.lb.exceptions.BookAlreadyExistsException;
import com.lb.exceptions.BookAvailableException;
import com.lb.exceptions.BookItemAlreadyExistsException;
import com.lb.exceptions.UserAlreadyExistsException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SystemImpl implements System {

    private final Print print;
    private final Set<User> users;
    private final Set<Book> books;
    private final Map<Integer, List<BookItem>> bookItems;
    private final Map<Integer, List<BookItem>> availableBookItems;
    private final Set<Author> authors;
    private final Set<LibraryCard> libraryCards;

    private static final int BOOK_TAKE_HOME_MAX_LIMIT = 10;
    private static final int FINE_PER_DAY = 2;

    public SystemImpl(Print print) {
        this.print = print;
        this.users = new HashSet<>();
        this.books = new HashSet<>();
        this.bookItems = new HashMap<>();
        this.availableBookItems = new HashMap<>();
        this.authors = new HashSet<>();
        this.libraryCards = new HashSet<>();
    }

    @Override
    public void addUser(User user) {
        if (users.contains(user))
            throw new UserAlreadyExistsException("User already exists on email " + user.getEmail());
        users.add(user);
    }

    @Override
    public void addBook(Book book) {
        if (books.contains(book))
            throw new BookAlreadyExistsException("Book already exists on id " + book.getBookId());
        books.add(book);
    }

    @Override
    public void addBookItem(BookItem bookItem) {
        List<BookItem> bookItemsOfBook = bookItems.getOrDefault(bookItem.getBook().getBookId(), new ArrayList<>());

        if (bookItemsOfBook.contains(bookItem))
            throw new BookItemAlreadyExistsException("Book item already exists on id " + bookItem.getBarCode());
        bookItemsOfBook.add(bookItem);
        bookItems.put(bookItem.getBook().getBookId(), bookItemsOfBook);
        markBookItemToAvailable(bookItem);
    }

    public void markBookItemToAvailable(BookItem bookItem) {
        bookItem.markAvailable(true);
        Integer bookId = bookItem.getBook().getBookId();
        List<BookItem> bookItemsOfBook = bookItems.getOrDefault(bookId, new ArrayList<>());
        bookItemsOfBook.add(bookItem);
        bookItems.put(bookId, bookItemsOfBook);

        List<BookItem> bookAvailItemsOfBook = availableBookItems.getOrDefault(bookId, new ArrayList<>());
        bookAvailItemsOfBook.add(bookItem);
        availableBookItems.put(bookId, bookAvailItemsOfBook);
    }

    public void markUnAvailableBookItem(BookItem bookItem) {
        bookItem.markAvailable(false);
        Integer bookId = bookItem.getBook().getBookId();
        List<BookItem> bookItemsOfBook = bookItems.getOrDefault(bookId, new ArrayList<>());
        bookItemsOfBook.add(bookItem);
        bookItems.put(bookId, bookItemsOfBook);

        List<BookItem> bookAvailItemsOfBook = availableBookItems.getOrDefault(bookId, new ArrayList<>());
        int index = bookAvailItemsOfBook.indexOf(bookItem);
        bookAvailItemsOfBook.remove(index);
        availableBookItems.put(bookId, bookAvailItemsOfBook);
    }

    public Long getFine(Checkout checkout, Date checkinDate) {
        long daysBetween = TimeUnit.DAYS.convert(checkinDate.getTime() - checkout.getCheckoutDate().getTime(), TimeUnit.MILLISECONDS);
        if (daysBetween <= BOOK_TAKE_HOME_MAX_LIMIT)
            return 0L;
        return daysBetween * FINE_PER_DAY;
    }

    @Override
    public Book searchBookByTitle(String title) {
        return books.stream().filter(book -> Objects.deepEquals(book.getBookTitle(), title)).findFirst().orElse(null);
    }

    @Override
    public List<Book> searchBookByCategory(BookSubject category) {
        return books.stream().filter(book -> Objects.deepEquals(book.getBookSubject(), category)).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBookByPublishDate(Date publishDate) {
        return books.stream().filter(book -> Objects.deepEquals(book.getPublishedDate(), publishDate)).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBookByAuthor(Integer authorId) {
        return authors.stream().filter(author -> Objects.deepEquals(authorId, author.getAuthorId()))
                .map(Author::getPublishedBooks).findFirst().orElse(new ArrayList<>());
    }

    @Override
    public void addAuthor(Author author) {
        if (authors.contains(author))
            throw new AuthorAleadyExistsException("Author already exists on id " + author.getAuthorId());
        authors.add(author);
    }

    @Override
    public void addBookToAuthor(Book book, Author author) {
        if (author.getPublishedBooks().contains(book))
            return;
        author.addBook(book);
        authors.add(author);
    }

    @Override
    public void issueLibraryCard(LibraryCard libraryCard) {
        libraryCards.add(libraryCard);
    }

    @Override
    public List<BookItem> getBookItemsByBookId(Integer bookId) {
        return availableBookItems.get(bookId);
    }

    @Override
    public Set<BookItem> checkOutBookItemByUserId(String userBarCode) {

        return libraryCards.stream().filter(libraryCard -> Objects.deepEquals(userBarCode, libraryCard.getBarCode()))
                .map(LibraryCard::getAllCheckedOutBookItems).findFirst().orElse(Set.of());
    }

    @Override
    public LibraryCard getLibraryCardByBarCode(String libCardBarCode) {
        return libraryCards.stream().filter(libraryCard -> Objects.deepEquals(libCardBarCode, libraryCard.getBarCode()))
                .findFirst().orElse(null);
    }

    @Override
    public void getBookNotAvailableBookItem(LibraryCard libraryCard, Book book) {
        List<BookItem> bookItems = availableBookItems.get(book.getBookId());
        if (bookItems.size() > 0)
            throw new BookAvailableException("Book item is available");

        print.info("book item not available adding in subscribers");

        book.addSubscriber(libraryCard.getUser());
    }

    @Override
    public void updateSubscribedOnBookArrival(Book book) {
        book.notifySubscriber();
    }

}
