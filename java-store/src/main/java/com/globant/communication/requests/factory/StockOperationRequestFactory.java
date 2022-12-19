package com.globant.communication.requests.factory;

import com.globant.communication.Actions;
import com.globant.communication.requests.Request;
import com.globant.shoppingcart.Item;

public class StockOperationRequestFactory implements RequestFactory<Item[]> {
    private final Item[] items;

    public StockOperationRequestFactory(Item[] items) {
        this.items = items;
    }

    @Override
    public Request<Item[]> generateRequest() {
        return new Request<>(Actions.RETRIEVE_PRODUCTS, items);
    }
}
