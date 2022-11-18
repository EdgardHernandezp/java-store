package com.globant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.communication.Request;
import com.globant.communication.Response;

import com.globant.shoppingcart.ShoppingCart;
import com.globant.shoppingcart.ShoppingCartDefault;
import com.globant.communication.StorageProtocol;
import java.util.LinkedHashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //TODO group products by type to find the cheaper ones
        //TODO find functionality
        int amount = 1;
        ObjectMapper mapper = new ObjectMapper();
        LinkedHashMap<String, Object> request = new LinkedHashMap<>();
        request.put("actionCode", 0);
        LinkedHashMap<String, Object> body = new LinkedHashMap<>();
        body.put("code", 1);
        body.put("name", "Onion");
        body.put("price", 2d);

        LinkedHashMap<String, Object> productType = new LinkedHashMap<>();
        productType.put("code", 1);
        //TODO: maybe just the code is necessary, the product must already exist. Or create it if it doesn't
        productType.put("name", "vegetables");
        body.put("productType", productType);

        request.put("body", body);
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
}
