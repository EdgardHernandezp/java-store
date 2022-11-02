package com.globant;

import java.util.HashMap;
import java.util.Map;

public class DefaultStorageProtocol implements StorageProtocol {
    @Override
    public Response handleRequest(Request request) {
        Map<Integer, Product> products = new HashMap<>();
        products.put(1, new Product(1, "Milk", 2f));

        Product product = products.get(request.productCode);
        String errorDescription = null;
        return new Response(product, errorDescription);
    }
}
