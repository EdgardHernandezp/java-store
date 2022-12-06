package com.globant.repos;

import com.globant.pojos.Product;
import com.globant.pojos.ProductType;
import java.util.List;

public interface StoreRepository {
    boolean createProduct(Product product);

    Product deleteProduct(int productId);

    boolean createProductType(ProductType productType);

    ProductType deleteProductType(int productTypeId);

    boolean updateStock(int productId, int amount);

    Integer checkExistingStock(int productId);

    Product findProductByCode(int productCode);

    List<Product> searchProductByName(String productName);
}
