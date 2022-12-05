package com.globant.communication.requests.factory;

import com.globant.communication.Actions;
import com.globant.communication.requests.Request;
import com.globant.communication.requests.StockOperation;

//TODO: change name; now it's not just withdrawal
public class StockOperationRequestFactory implements RequestFactory<StockOperation> {
    private final StockOperation stockOperation;

    public StockOperationRequestFactory(int productCode, int quantity) {
        this.stockOperation = new StockOperation(productCode, quantity);
    }

    @Override
    public Request<StockOperation> generateRequest() {
        return new Request<>(Actions.RETRIEVE_PRODUCTS, stockOperation);
    }

    //TODO: nest StockOperation class in here
}
