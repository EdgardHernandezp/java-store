package com.globant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globant.repos.StoreRepository;
import com.globant.utils.ParserUtil;

public class StoreBOImpl implements StoreBO {

    private StoreRepository repository;

    public StoreBOImpl(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public String handleRequest(StoreRequest request) {

        //TODO: save default response json as property or separate json file
        String response = "{\"responseCode\":400,\"description\":\"Invalid action code\"}";
        try {
            //TODO: handle case where code receive doesn't exist
            Actions action = Actions.values()[request.getActionCode()];
            switch (action) {
                case ADD_PRODUCT:
                    addProduct((Product) request.getBody());
                    response = ParserUtil.generateResponse(200, "OK");
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
        repository.updateStock(productId, amount);
    }

    enum Actions {
        ADD_PRODUCT,
        DELETE_PRODUCT,
        ADD_PRODUCT_TYPE,
        DELETE_PRODUCT_TYPE,
        RETRIEVE_PRODUCTS
    }
}
