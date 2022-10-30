package com.lb.models;

import com.lb.console.ConsolePrint;
import com.lb.console.Print;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryCard {
    private String barCode;
    private User user;
    private Integer fine;
    private Integer availableBookCount;
    private Map<String, Checkout> currentCheckoutBook;

    public LibraryCard(String barCode, User user, Integer fine) {
        this.barCode = barCode;
        this.user = user;
        this.fine = fine;
        this.availableBookCount = 5;
        this.currentCheckoutBook = new HashMap<>();
    }

    public String getBarCode() {
        return barCode;
    }

    public void changeBarCode(String barCode) {
        this.barCode = barCode;
    }

    public User getUser() {
        return user;
    }

    public void changeUser(User user) {
        this.user = user;
    }

    public Integer getFine() {
        return fine;
    }

    public void updateFine(Integer fine) {
        this.fine = fine;
    }

    public Integer getAvailableBookCount() {
        return availableBookCount;
    }

    public void updateAvailableBookCount(Integer availableBookCount) {
        this.availableBookCount = availableBookCount;
    }

    private Map<String, Checkout> getCurrentCheckoutBook() {
        return currentCheckoutBook;
    }

    public void setCurrentCheckoutBook(Map<String, Checkout> currentCheckoutBook) {
        this.currentCheckoutBook = currentCheckoutBook;
    }

    public void addCheckoutBook(Checkout checkout){
        getCurrentCheckoutBook().put(checkout.getBookItem().getBarCode(), checkout);
    }

    public void checkInBook(String barCode){
        currentCheckoutBook.remove(barCode);
        updateAvailableBookCount(getAvailableBookCount() + 1);
    }

    public Checkout getCheckout(String bookItemBarCode){
        return getCurrentCheckoutBook().get(bookItemBarCode);
    }

    public void payFine(int amountPaid){
        this.updateFine(this.getFine() - amountPaid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryCard that = (LibraryCard) o;
        return Objects.equals(barCode, that.barCode) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barCode, user);
    }

    public Set<BookItem> getAllCheckedOutBookItems(){
        return currentCheckoutBook.values().stream().map(Checkout::getBookItem).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "LibraryCard{" +
                "barCode='" + barCode + '\'' +
                ", user=" + user +
                ", fine=" + fine +
                ", availableBookCount=" + availableBookCount +
                ", currentCheckoutBook=" + currentCheckoutBook +
                '}';
    }
}
