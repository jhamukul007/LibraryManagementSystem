package com.lb.payment;

import java.util.Date;

public class DebitCardPaymentStrategy extends CardPaymentStrategy{

    public DebitCardPaymentStrategy(String cardNumber, String nameOnCard, Integer cvv, Date expirationDate) {
        super(cardNumber, nameOnCard, cvv, expirationDate);
    }

    @Override
    public void pay(int amount) {
        System.out.println("Payment done via Debit Card");
    }

}
