package com.globant;

import com.globant.payment.DefaultPaymentProcessor;
import com.globant.shoppingcart.ShoppingCartDefault;

public class Main {

    public static void main(String[] args) {
        //TODO group products by type to find the cheaper ones
        StoreBO storeBO = new StoreBO(new CostumerSession(new ShoppingCartDefault(), new DefaultPaymentProcessor()));
        storeBO.run();
    }
}
