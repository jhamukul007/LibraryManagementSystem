package com.lb.models;

import java.util.Objects;

public class BookItem {
    private String barCode;
    private Book book;
    private String rackPosition;
    private boolean isAvailable;

    public BookItem(String barCode, Book book, String rackPosition) {
        this.barCode = barCode;
        this.book = book;
        this.rackPosition = rackPosition;
        this.isAvailable = true;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getRackPosition() {
        return rackPosition;
    }

    public void setRackPosition(String rackPosition) {
        this.rackPosition = rackPosition;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void markAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem bookItem = (BookItem) o;
        return Objects.equals(barCode, bookItem.barCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barCode);
    }

    @Override
    public String toString() {
        return "BookItem{" +
                "barCode='" + barCode + '\'' +
                ", book=" + book +
                ", rackPosition='" + rackPosition + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
