package com.globant.communication.requests.factory;

import com.globant.communication.Actions;
import com.globant.communication.requests.Request;

public class StockOperationRequestFactory implements RequestFactory<StockOperationRequestFactory.StockOperation> {
    private final StockOperation stockOperation;

    public StockOperationRequestFactory(int productCode, int quantity) {
        this.stockOperation = new StockOperation(productCode, quantity);
    }

    @Override
    public Request<StockOperation> generateRequest() {
        return new Request<>(Actions.RETRIEVE_PRODUCTS, stockOperation);
    }

    static class StockOperation {
        private final int productCode;
        private final int quantity;

        public StockOperation(int productCode, int quantity) {
            this.productCode = productCode;
            this.quantity = quantity;
        }

        public int getProductCode() {
            return productCode;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
