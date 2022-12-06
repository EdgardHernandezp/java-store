package com.globant.communication.requests.factory;

import com.globant.communication.Actions;
import com.globant.communication.requests.Request;

public class ProductSearchRequestFactory implements RequestFactory<String> {
    private final String productName;

    public ProductSearchRequestFactory(String productName) {
        this.productName = productName;
    }

    @Override
    public Request<String> generateRequest() {
        return new Request<>(Actions.SEARCH_PRODUCT, productName);
    }
}
