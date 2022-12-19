package com.globant.repos;

import com.globant.pojos.Product;
import com.globant.pojos.ProductType;
import com.globant.pojos.StockOperation;
import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    boolean createProduct(Product product);

    Product deleteProduct(int productId);

    boolean createProductType(ProductType productType);

    ProductType deleteProductType(int productTypeId);

    StockOperation[] updateStock(StockOperation[] stockOperations);

    Integer checkExistingStock(int productId);

    Optional<Product> findProductByCode(int productCode);

    List<Product> searchProductByName(String productName);

    List<Product> findAvailableProducts();
}
