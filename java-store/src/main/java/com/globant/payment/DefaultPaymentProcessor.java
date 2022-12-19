package com.globant.payment;

public class DefaultPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(float totalPurchase) {
        System.out.println("Payment Processed! Total amount = " + totalPurchase);
        return true;
    }
}
