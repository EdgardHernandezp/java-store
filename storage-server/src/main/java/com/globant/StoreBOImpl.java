package com.globant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.repos.StoreRepository;
import java.util.LinkedHashMap;

public class StoreBOImpl implements StoreBO {

    private StoreRepository repository;

    public StoreBOImpl(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public String handleRequest(String request) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<LinkedHashMap<String, Object>> type = new TypeReference<LinkedHashMap<String, Object>>() {};
        //TODO: save default response json as property or separate json file
        String response = "{\"responseCode\":400,\"description\":\"Invalid action code\"}";
        try {
            LinkedHashMap<String, Object> convertedRequest = mapper.readValue(request, type);
            Integer actionCode = (Integer) convertedRequest.get("actionCode");
            LinkedHashMap<String, Object> body = (LinkedHashMap<String, Object>) convertedRequest.get("body");
            Actions action = Actions.values()[actionCode];
            switch (action) {
                case ADD_PRODUCT:
                    Integer code = (Integer) body.get("code");
                    String name = (String) body.get("name");
                    Double price = (Double) body.get("price");

                    LinkedHashMap<String, Object> productType = (LinkedHashMap<String, Object>) body.get("productType");
                    Integer productTypeCode = (Integer) productType.get("code");
                    String productTypeName = (String) productType.get("name");

                    addProduct(new Product(code, name, price, new ProductType(productTypeCode, productTypeName)));
                    LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
                    responseMap.put("responseCode", 200);
                    responseMap.put("description", "OK");
                    response = mapper.writeValueAsString(responseMap);
                    break;
                case DELETE_PRODUCT:
                    break;
                case ADD_PRODUCT_TYPE:
                    break;
                case DELETE_PRODUCT_TYPE:
                    break;
                case RETRIEVE_PRODUCTS:
                    //TODO: validation in case no product amount available
                    break;
                default:
                    break;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response;
    }

    private void addProduct(Product product) {
        repository.createProduct(product);
    }

    private void deleteProduct(int productId) {
        repository.deleteProduct(productId);
    }

    private void addProductType(ProductType productType) {
        repository.createProductType(productType);
    }

    private void deleteProductType(int productTypeId) {
        repository.deleteProductType(productTypeId);
    }

    private void retrieveProductsFromStorage(int productId, int amount) {
        repository.modifyProductAmount(productId, amount);
    }

    enum Actions {
        ADD_PRODUCT,
        DELETE_PRODUCT,
        ADD_PRODUCT_TYPE,
        DELETE_PRODUCT_TYPE,
        RETRIEVE_PRODUCTS
    }
}
