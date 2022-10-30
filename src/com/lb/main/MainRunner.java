package com.lb.main;

import com.lb.console.ConsolePrint;
import com.lb.console.Print;
import com.lb.enums.BookSubject;
import com.lb.enums.Role;
import com.lb.models.Author;
import com.lb.models.Book;
import com.lb.models.BookItem;
import com.lb.models.Librarian;
import com.lb.models.LibraryCard;
import com.lb.models.SystemImpl;
import com.lb.models.SystemProxy;
import com.lb.models.User;
import com.lb.services.SearchBook;
import com.lb.services.SearchBookImpl;
import com.lb.utils.DateUtils;
import com.lb.utils.RandomUtils;

import java.text.ParseException;
import java.util.List;

public class MainRunner {
    public static void main(String[] args) throws ParseException {

        Print console = new ConsolePrint();
        SystemProxy systemProxy = new SystemProxy(console);
        Librarian librarian = new Librarian(systemProxy, console);
        SearchBook searchBook = new SearchBookImpl(systemProxy);

        User user = new User("Mukul", "jhamukul@gmail.com", Role.LIBRARIAN);
        librarian.createUser(user, user);
        console.info("LIBRARIAN created successfully");

        Book book = new Book(1, "Math by Rd sharma", BookSubject.MATH,
                DateUtils.convertDateFromString("12/08/2022"));

        Book book1 = new Book(2, "MATHURIYA MATHS BOOK", BookSubject.MATH,
                DateUtils.convertDateFromString("12/03/2021"));

        Book book2 = new Book(3, "Maths Tables Book for beginners", BookSubject.MATH,
                DateUtils.convertDateFromString("12/03/2020"));

        console.info("Creating book !!!");

        librarian.addBook(user, book);
        librarian.addBook(user, book1);
        librarian.addBook(user, book2);

        console.info("Book Created !!!");

        Author author1 = new Author(1, "R D Sharma");
        Author author2 = new Author(2, "M k Rahul");
        //Author author3 = new Author(3, "R D Sharma");

        author1.addBook(book);
        author2.addBook(book1);

        console.info("Adding author !!!");
        librarian.addAuthor(author1);
        librarian.addAuthor(author2);
        librarian.addBookToAuthor(book2, author1);

        BookItem bookItem1 = new BookItem(RandomUtils.randomString(), book, "1A");
        BookItem bookItem2 = new BookItem(RandomUtils.randomString(), book, "2B");
        BookItem bookItem3 = new BookItem(RandomUtils.randomString(), book, "3B");

        console.info("Adding bookItem to book");
        librarian.createBookItem(bookItem1);
        librarian.createBookItem(bookItem2);
        librarian.createBookItem(bookItem3);

        console.info(searchBook.searchBookByTitle(book.getBookTitle()));
        console.info(searchBook.searchBookByTitle(book2.getBookTitle()));

        console.info(searchBook.searchBookByCategory(BookSubject.MATH));

        console.info(searchBook.searchBookByPublishDate(DateUtils.convertDateFromString("12/03/2021")));

        console.info(searchBook.searchBookByCategory(BookSubject.MATH));

        console.info(searchBook.searchBookByAuthor(author1.getAuthorId()));

        User user1 = new User("Jack", "jack@gmail.com", Role.MEMBER);
        User user2 = new User("Manoj", "manoj@gmail.com", Role.MEMBER);
        User user3 = new User("Kohli", "kohli@gmail.com", Role.MEMBER);
        User user4 = new User("Rohit", "rohit@gmail.com", Role.MEMBER);
        User user5 = new User("Rahul", "rahul@gmail.com", Role.MEMBER);
        User user6 = new User("Dhawan", "dhawan@gmail.com", Role.MEMBER);

        librarian.createUser(user, user1);
        librarian.createUser(user, user2);
        librarian.createUser(user, user3);
        librarian.createUser(user, user4);
        librarian.createUser(user, user5);
        librarian.createUser(user, user6);

        console.info("MEMBERs created successfully");

        LibraryCard libraryCard1 = new LibraryCard(RandomUtils.randomString() , user1, 0);
        LibraryCard libraryCard2 = new LibraryCard(RandomUtils.randomString() , user2, 0);
        LibraryCard libraryCard3 = new LibraryCard(RandomUtils.randomString() , user3, 0);

        LibraryCard libraryCard4 = new LibraryCard(RandomUtils.randomString() , user4, 0);
        LibraryCard libraryCard5 = new LibraryCard(RandomUtils.randomString() , user5, 0);
        LibraryCard libraryCard6 = new LibraryCard(RandomUtils.randomString() , user6, 0);

        console.info("libraryCard assigned to users");
        librarian.assignLibraryCard(libraryCard1);
        librarian.assignLibraryCard(libraryCard2);
        librarian.assignLibraryCard(libraryCard3);
        librarian.assignLibraryCard(libraryCard4);
        librarian.assignLibraryCard(libraryCard5);
        librarian.assignLibraryCard(libraryCard6);

        List<BookItem> bookItems = systemProxy.getBookItemsByBookId(book.getBookId());

        BookItem bookedBookItem =  bookItems.get(0);

        librarian.checkOutBook(bookedBookItem, libraryCard1);

        console.info(systemProxy.getBookItemsByBookId(book.getBookId()));

        console.info(systemProxy.checkOutBookItemByUserId(libraryCard1.getBarCode()));

        console.info(systemProxy.getLibraryCardByBarCode(libraryCard1.getBarCode()));

        List<BookItem> bookItems1 = systemProxy.getBookItemsByBookId(book.getBookId());

        BookItem bookedBookItem1 =  bookItems1.get(0);

        librarian.checkOutBook(bookedBookItem1, libraryCard2);


        List<BookItem> bookItems2 = systemProxy.getBookItemsByBookId(book.getBookId());

        BookItem bookedBookItem2 =  bookItems2.get(0);

        librarian.checkOutBook(bookedBookItem2, libraryCard2);

        console.info("Avaiable book as "+ systemProxy.getBookItemsByBookId(book.getBookId()));

        systemProxy.getBookNotAvailableBookItem(libraryCard4, bookedBookItem.getBook());
        systemProxy.getBookNotAvailableBookItem(libraryCard5, bookedBookItem.getBook());
        systemProxy.getBookNotAvailableBookItem(libraryCard6, bookedBookItem.getBook());

        librarian.checkInBook(bookedBookItem, libraryCard1, 0L, DateUtils.convertDateFromString("29/11/2022"));

        // console.info(systemProxy.getLibraryCardByBarCode(libraryCard1.getBarCode()));


    }
}
