package com.lb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author {

    private Integer authorId;
    private String name;
    private List<Book> publishedBooks;

    public Author(Integer authorId, String name) {
        this.authorId = authorId;
        this.name = name;
        this.publishedBooks = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.publishedBooks.add(book);
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getPublishedBooks() {
        return publishedBooks;
    }

    public void setPublishedBooks(List<Book> publishedBooks) {
        this.publishedBooks = publishedBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(authorId, author.authorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", publishedBooks=" + publishedBooks +
                '}';
    }
}
