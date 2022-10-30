package com.lb.payment;

import java.util.Date;

public class CreditCardPaymentStrategy extends CardPaymentStrategy{

    public CreditCardPaymentStrategy(String cardNumber, String nameOnCard, Integer cvv, Date expirationDate) {
        super(cardNumber, nameOnCard, cvv, expirationDate);
    }

    @Override
    public void pay(int amount) {
        System.out.println("Payment has been received via credit card");
    }

}
