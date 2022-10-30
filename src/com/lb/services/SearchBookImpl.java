package com.lb.services;

import com.lb.enums.BookSubject;
import com.lb.models.Book;
import com.lb.models.SystemProxy;

import java.util.Date;
import java.util.List;

public class SearchBookImpl implements SearchBook{

    private final SystemProxy systemProxy;

    public SearchBookImpl(SystemProxy systemProxy) {
        this.systemProxy = systemProxy;
    }

    @Override
    public Book searchBookByTitle(String title) {
        return systemProxy.searchBookByTitle(title);
    }

    @Override
    public List<Book> searchBookByCategory(BookSubject category) {
        return systemProxy.searchBookByCategory(category);
    }

    @Override
    public List<Book> searchBookByPublishDate(Date publishDate) {
        return systemProxy.searchBookByPublishDate(publishDate);
    }

    @Override
    public List<Book> searchBookByAuthor(Integer authorId) {
        return systemProxy.searchBookByAuthor(authorId);
    }
}
