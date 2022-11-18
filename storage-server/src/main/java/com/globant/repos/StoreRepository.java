package com.globant.repos;

import com.globant.Product;
import com.globant.ProductType;

public interface StoreRepository {
    boolean createProduct(Product product);

    Product deleteProduct(int productId);

    boolean createProductType(ProductType productType);

    ProductType deleteProductType(int productTypeId);

    void modifyProductAmount(int productId, int amount);
}
