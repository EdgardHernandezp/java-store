package com.globant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.globant.communication.StorageProtocol;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {
        //TODO group products by type to find the cheaper ones
        //TODO find functionality
        int amount = 1;
        ObjectMapper mapper = new ObjectMapper();
        LinkedHashMap<String, Object> request = new LinkedHashMap<>();
        request.put("action", "addProductType");

        //LinkedHashMap<String, Object> body = createProductbody();
        LinkedHashMap<String, Object> body = createProductTypeBody();
        //LinkedHashMap<String, Object> body = createDeleteInfoBody();
        //LinkedHashMap<String, Object> body = createWhitdrawalRequestBody();
        request.put("body", body);
        //request.put("body", 1);


        try {
            String requestStr = mapper.writeValueAsString(request);
            String response = StorageProtocol.requestProduct(requestStr.concat("\n"));
            System.out.println(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        ShoppingCart shoppingCart = new ShoppingCartDefault();
//        List<Product> products = response.getProducts();
//        shoppingCart.addItem(products.get(0), amount);
//
//        shoppingCart.showShoppingCartContent();
//        System.out.println("Total: " + shoppingCart.calculateTotal());

//        shoppingCart.deleteItem(2);
//        shoppingCart.showShoppingCartContent();
//        System.out.println("Total: " + shoppingCart.calculateTotal());

    }

    private static LinkedHashMap<String, Object> createWhitdrawalRequestBody() {
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("productCode", 1);
        body.put("requestedQuantity", 10);
        return body;
    }

    private static LinkedHashMap<String, Object> createDeleteInfoBody() {
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("resourceType", 1);
        body.put("resourceId", 1);
        return body;
    }

    private static LinkedHashMap<String, Object> createProductTypeBody() {
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("code", 1);
        body.put("name", "fruits");
        return body;
    }

    private static LinkedHashMap<String, Object> createProductbody() {
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("code", 1);
        body.put("name", "Onion");
        body.put("price", 2d);

        LinkedHashMap<String, Object> productType = new LinkedHashMap<>();
        productType.put("code", 1);
        //TODO: maybe just the code is necessary, the product must already exist. Or create it if it doesn't
        productType.put("name", "vegetables");
        body.put("productType", productType);
        return body;
    }
}
