package com.globant.communication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.globant.communication.requests.factory.FindAvailableProductsRequestFactory;
import com.globant.communication.requests.factory.ProductExistenceRequestFactory;
import com.globant.communication.requests.factory.ProductSearchRequestFactory;
import com.globant.communication.requests.factory.RequestFactory;
import com.globant.communication.requests.factory.StockOperationRequestFactory;
import com.globant.shoppingcart.Item;
import com.globant.shoppingcart.Product;
import com.globant.utils.ParseUtil;
import java.util.Optional;

public final class ServerFacade {
    private static final ServerEntryPoint serverEntryPoint = SocketServerEntryPoint.getInstance();

    private ServerFacade() {
    }
    
    private static <T, S> T exchange(RequestFactory<S> requestFactory, TypeReference<Response<T>> typeRef) {
        String request = ParseUtil.parseRequest(requestFactory.generateRequest());
        Response<T> response = ParseUtil.parseResponse(serverEntryPoint.sendRequest(request), typeRef);
        T responsePayload = null;
        if (response.getResponseCode() == 200)
            responsePayload = response.getPayload();
        else
            System.out.println(response.getDescription());
        return responsePayload;
    }

    public static Item[] updateProductsStockInStorage(Item[] items) {
        return exchange(new StockOperationRequestFactory(items), new TypeReference<>() {
        });
    }

    public static Product[] searchProductByName(String userProvidedProductName) {
        return exchange(new ProductSearchRequestFactory(userProvidedProductName), new TypeReference<>() {
        });
    }

    public static Product[] searchAvailableProducts() {
        return exchange(new FindAvailableProductsRequestFactory(), new TypeReference<>() {
        });
    }

    public static Optional<Product> checkProductExistence(int productCode) {
        Product product = exchange(new ProductExistenceRequestFactory(productCode), new TypeReference<>() {
        });
        return Optional.ofNullable(product);
    }
}
