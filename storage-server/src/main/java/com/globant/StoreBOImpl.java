package com.globant;

import com.globant.pojos.DeleteInfo;
import com.globant.pojos.Product;
import com.globant.pojos.ProductType;
import com.globant.pojos.StockOperation;
import com.globant.repos.StoreRepository;
import com.globant.requests.StoreRequest;
import com.globant.utils.ParserUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StoreBOImpl implements StoreBO {

    private final StoreRepository repository;

    public StoreBOImpl(StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public String handleRequest(StoreRequest request) {

        int responseCode = 200;
        String responseDescription = "OK";
        Object payload = null;
        switch (request.getAction()) {
            case ADD_PRODUCT:
                addProduct((Product) request.getBody());
                break;
            case DELETE_RESOURCE:
                DeleteInfo deleteInfo = (DeleteInfo) request.getBody();
                if (deleteInfo.getResourceType() == 0)
                    deleteProduct(deleteInfo.getResourceId());
                else
                    deleteProductType(deleteInfo.getResourceId());
                break;
            case ADD_PRODUCT_TYPE:
                addProductType((ProductType) request.getBody());
                break;
            case RETRIEVE_PRODUCTS:
                StockOperation[] stockOperations = (StockOperation[]) request.getBody();
                stockOperations = retrieveProductsFromStorage(stockOperations);
                if (stockOperations.length > 0) {
                    responseDescription = "Couldn't fulfill product stock completely";
                    payload = stockOperations;
                }
                break;
            case CHECK_PRODUCT_EXISTENCE:
                Integer productId = (Integer) request.getBody();
                int productStock = checkExistingStock(productId);
                responseDescription = "product with id=" + productId + " has " + productStock + " units in stock";
                payload = findProductByCode(productId).orElse(null);
                break;
            case SEARCH_PRODUCT:
                List<Product> products = searchProductByName((String) request.getBody());
                if (products.isEmpty()) {
                    responseCode = 204;
                    responseDescription = "No results";
                }
                payload = products;
                break;
            case FIND_AVAILABLE_PRODUCTS:
                List<Product> availableProducts = findAvailableProducts();
                if (availableProducts.isEmpty()) {
                    responseCode = 204;
                    responseDescription = "No results";
                }
                payload = availableProducts;
                break;
            default:
                responseCode = 400;
                responseDescription =
                        "Unrecognized action code; must be one of these (case-sensitive): " + Arrays.toString(Actions.values());
                break;
        }
        return ParserUtil.generateResponse(responseCode, responseDescription, payload);
    }

    private List<Product> searchProductByName(String productName) {
        return repository.searchProductByName(productName);
    }

    private int checkExistingStock(Integer productId) {
        Optional<Integer> productStock = Optional.ofNullable(repository.checkExistingStock(productId));
        return productStock.orElse(0);
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

    private StockOperation[] retrieveProductsFromStorage(StockOperation[] stockOperations) {
        return repository.updateStock(stockOperations);
    }

    private List<Product> findAvailableProducts() {
        return repository.findAvailableProducts();
    }

    private Optional<Product> findProductByCode(int code) {
        return repository.findProductByCode(code);
    }

}
