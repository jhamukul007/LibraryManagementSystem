package com.lb.payment;

import java.util.Date;

public abstract class CardPaymentStrategy implements PaymentStrategy{

    public CardPaymentStrategy(String cardNumber, String nameOnCard, Integer cvv, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    private String cardNumber;
    private String nameOnCard;
    private Integer cvv;
    private Date expirationDate;
}
