package com.globant.communication.requests.factory;

import com.globant.communication.Actions;
import com.globant.communication.requests.Request;

public class ProductExistenceRequestFactory implements RequestFactory<Integer> {
    private final int productCode;

    public ProductExistenceRequestFactory(int productCode) {
        this.productCode = productCode;
    }

    @Override
    public Request<Integer> generateRequest() {
        return new Request<>(Actions.CHECK_PRODUCT_EXISTENCE, productCode);
    }
}
