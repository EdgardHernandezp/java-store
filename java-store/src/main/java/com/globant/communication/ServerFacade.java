package com.globant.communication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.globant.Product;
import com.globant.ParseUtil;
import com.globant.communication.requests.factory.StockWithdrawalRequestFactory;
import java.util.Optional;

public class ServerFacade {
    private final ServerEntryPoint serverEntryPoint = SocketServerEntryPoint.getInstance();

    public Optional<Product> retrieveProductFromStorage(int productCode, int quantity) {
        String request = ParseUtil.parseRequest(new StockWithdrawalRequestFactory(productCode, quantity).generateRequest());
        TypeReference<Response<Product>> responseType = new TypeReference<>() {
        };
        Response<Product> response = ParseUtil.parseResponse(serverEntryPoint.sendRequest(request), responseType);
        Product product = null;
        if (response.getResponseCode() == 200)
            product = response.getPayload();
        else
            System.out.println(response.getDescription());
        return Optional.ofNullable(product);
    }
}
