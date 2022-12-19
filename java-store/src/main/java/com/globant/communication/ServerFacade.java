package com.globant.communication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.globant.communication.requests.factory.FindAvailableProductsRequestFactory;
import com.globant.communication.requests.factory.ProductExistenceRequestFactory;
import com.globant.communication.requests.factory.ProductSearchRequestFactory;
import com.globant.shoppingcart.Item;
import com.globant.shoppingcart.Product;
import com.globant.utils.ParseUtil;
import com.globant.communication.requests.factory.StockOperationRequestFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO: duplicate code in class, deal with it.
//Generics methods might to the trick
//TODO: consider singleton for this class
public class ServerFacade {
    private final ServerEntryPoint serverEntryPoint = SocketServerEntryPoint.getInstance();

    public Item[] updateProductsStockInStorage(Item[] items) {
        String request = ParseUtil.parseRequest(new StockOperationRequestFactory(items).generateRequest());
        TypeReference<Response<Item[]>> responseType = new TypeReference<>() {
        };
        Response<Item[]> response = ParseUtil.parseResponse(serverEntryPoint.sendRequest(request), responseType);
        Item[] itemsNotProcessed = null;
        if (response.getResponseCode() == 200)
            itemsNotProcessed = response.getPayload();
        else
            System.out.println(response.getDescription());
        return itemsNotProcessed;
    }

    public List<Product> searchProductByName(String userProvidedProductName) {
        String request = ParseUtil.parseRequest(new ProductSearchRequestFactory(userProvidedProductName).generateRequest());
        TypeReference<Response<List<Product>>> responseType = new TypeReference<>() {
        };
        Response<List<Product>> response = ParseUtil.parseResponse(serverEntryPoint.sendRequest(request), responseType);
        List<Product> products = new ArrayList<>();
        if (response.getResponseCode() == 200)
            products = response.getPayload();
        else
            System.out.println(response.getDescription());
        return products;
    }

    public List<Product> searchAvailableProducts() {
        String request = ParseUtil.parseRequest(new FindAvailableProductsRequestFactory().generateRequest());
        //TODO: pass class type not TypeRef, making code clearer here. Pass it to ParseUtil
        TypeReference<Response<List<Product>>> responseType = new TypeReference<>() {
        };
        Response<List<Product>> response = ParseUtil.parseResponse(serverEntryPoint.sendRequest(request), responseType);
        List<Product> products = new ArrayList<>();
        if (response.getResponseCode() == 200)
            products = response.getPayload();
        else
            System.out.println(response.getDescription());
        return products;
    }

    public Optional<Product> checkProductExistence(int productCode) {
        String request = ParseUtil.parseRequest(new ProductExistenceRequestFactory(productCode).generateRequest());
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
