package com.lb.models;

import com.lb.utils.RandomUtils;

import java.util.Date;
import java.util.Objects;

public class Checkout {
    private Integer checkoutId;
    private BookItem bookItem;
    private User user;
    private Date checkoutDate;
    private Date checkInDate;

    public Checkout(BookItem bookItem, User user, Date checkoutDate) {
        this.checkoutId = RandomUtils.random();
        this.bookItem = bookItem;
        this.user = user;
        this.checkoutDate = checkoutDate;
    }

    public Integer getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(Integer checkoutId) {
        this.checkoutId = checkoutId;
    }

    public BookItem getBookItem() {
        return bookItem;
    }

    public void setBookItem(BookItem bookItem) {
        this.bookItem = bookItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checkout checkout = (Checkout) o;
        return Objects.equals(bookItem, checkout.bookItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookItem);
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "checkoutId=" + checkoutId +
                ", bookItem=" + bookItem +
                ", user=" + user +
                ", checkoutDate=" + checkoutDate +
                ", checkInDate=" + checkInDate +
                '}';
    }
}
