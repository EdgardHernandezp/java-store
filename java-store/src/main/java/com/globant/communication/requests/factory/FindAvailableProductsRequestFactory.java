package com.globant.communication.requests.factory;

import com.globant.communication.Actions;
import com.globant.communication.requests.Request;

public class FindAvailableProductsRequestFactory implements RequestFactory<Void> {
    @Override
    public Request<Void> generateRequest() {
        return new Request<>(Actions.FIND_AVAILABLE_PRODUCTS, null);
    }
}
