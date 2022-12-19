package com.globant.payment;

public interface PaymentProcessor {
    boolean processPayment(float totalPurchase);
}
