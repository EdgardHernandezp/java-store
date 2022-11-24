package com.globant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.globant.pojos.DeleteInfo;
import com.globant.pojos.Product;
import com.globant.pojos.ProductType;
import com.globant.pojos.StockWithdrawal;
import com.globant.repos.StoreRepository;
import com.globant.requests.StoreRequest;
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
            Actions action = Actions.fromString(request.getAction());
            switch (action) {
                case ADD_PRODUCT:
                    addProduct((Product) request.getBody());
                    response = ParserUtil.generateResponse(200, "OK");
                    break;
                case DELETE_RESOURCE:
                    DeleteInfo deleteInfo = (DeleteInfo) request.getBody();
                    if (deleteInfo.getResourceType() == 0)
                        deleteProduct(deleteInfo.getResourceId());
                    else
                        deleteProductType(deleteInfo.getResourceId());
                    response = ParserUtil.generateResponse(200, "OK");
                    break;
                case ADD_PRODUCT_TYPE:
                    addProductType((ProductType) request.getBody());
                    response = ParserUtil.generateResponse(200, "OK");
                    break;
                case RETRIEVE_PRODUCTS:
                    //TODO: response must indicate the current stock to client in case of error
                    StockWithdrawal stockWhitdrawal = (StockWithdrawal) request.getBody();
                    if (retrieveProductsFromStorage(stockWhitdrawal.getProductCode(), stockWhitdrawal.getRequestedQuantity()))
                        response = ParserUtil.generateResponse(200, "OK");
                    else
                        response = ParserUtil.generateResponse(400, "not enough stock to fulfill request");
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

    private boolean retrieveProductsFromStorage(int productId, int stock) {
        return repository.updateStock(productId, stock);
    }

    enum Actions {
        ADD_PRODUCT("addProduct"), DELETE_RESOURCE("deleteResource"), ADD_PRODUCT_TYPE("addProductType"), RETRIEVE_PRODUCTS("retrieve");

        private String text;

        Actions(String text) {
            this.text = text;
        }

        public static Actions fromString(String text) {
            for (Actions action : Actions.values()) {
                if (action.text.equalsIgnoreCase(text)) {
                    return action;
                }
            }
            return null;
        }

        public String getText() {
            return text;
        }
    }
}
