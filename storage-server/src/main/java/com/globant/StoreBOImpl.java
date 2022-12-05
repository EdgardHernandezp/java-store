package com.globant;

import com.globant.pojos.DeleteInfo;
import com.globant.pojos.Product;
import com.globant.pojos.ProductType;
import com.globant.pojos.StockOperation;
import com.globant.repos.StoreRepository;
import com.globant.requests.StoreRequest;
import com.globant.utils.ParserUtil;
import java.util.Arrays;
import java.util.Optional;

public class StoreBOImpl implements StoreBO {

    private StoreRepository repository;

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
                StockOperation stockWithdrawal = (StockOperation) request.getBody();
                int productCode = stockWithdrawal.getProductCode();
                Optional<Product> optProduct = retrieveProductsFromStorage(productCode, stockWithdrawal.getQuantity());
                if (optProduct.isEmpty()) {
                    int productStock = checkExistingStock(productCode);
                    responseCode = 412;
                    responseDescription = "not enough stock to fulfill request, product remaining stock = " + productStock;
                } else
                    payload = optProduct.get();
                break;
            case CHECK_PRODUCT_EXISTENCE:
                Integer productId = (Integer) request.getBody();
                int productStock = checkExistingStock(productId);
                responseDescription = "product with id=" + productId + " has " + productStock + " units in stock";
                break;
            default:
                responseCode = 400;
                responseDescription =
                        "Unrecognized action code; must be one of these (case-sensitive): " + Arrays.toString(Actions.values());
                break;
        }
        return ParserUtil.generateResponse(responseCode, responseDescription, payload);
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

    private Optional<Product> retrieveProductsFromStorage(int productCode, int stock) {
        Product product = null;
        if (repository.updateStock(productCode, stock))
            product = repository.findProductByCode(productCode);
        return Optional.ofNullable(product);
    }

}
