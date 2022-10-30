package com.lb.services;

import com.lb.enums.BookSubject;
import com.lb.models.Book;

import java.util.Date;
import java.util.List;

public interface SearchBook {
    Book searchBookByTitle(String title);
    List<Book> searchBookByCategory(BookSubject category);
    List<Book> searchBookByPublishDate(Date publishDate);
    List<Book> searchBookByAuthor(Integer authorId);
}
