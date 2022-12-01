package com.globant.communication.requests.factory;

import com.globant.communication.Actions;
import com.globant.communication.requests.Request;
import com.globant.communication.requests.StockWithdrawal;

public class StockWithdrawalRequestFactory implements RequestFactory<StockWithdrawal> {
    private final StockWithdrawal stockWithdrawal;

    public StockWithdrawalRequestFactory(int productCode, int quantity) {
        this.stockWithdrawal = new StockWithdrawal(productCode, quantity);
    }

    @Override
    public Request<StockWithdrawal> generateRequest() {
        return new Request<>(Actions.RETRIEVE_PRODUCTS, stockWithdrawal);
    }

    //TODO: nest StockWithdrawal class in here
}
