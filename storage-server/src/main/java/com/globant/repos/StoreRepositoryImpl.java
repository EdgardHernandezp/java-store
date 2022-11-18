package com.globant.repos;

import com.globant.Product;
import com.globant.ProductType;
import java.util.HashMap;
import java.util.Map;

public class StoreRepositoryImpl implements StoreRepository {

    private static final Map<Integer, Product> products = new HashMap<>();
    private static final Map<Integer, ProductType> productTypes = new HashMap<>();
    private static final Map<Integer, Integer> productStorage = new HashMap<>();
    private static int productCodeIncrement = 1;
    private static int productTypeCodeIncrement = 1;

    //TODO: this class should be a singleton

    static {
        ProductType vegetablesType = new ProductType(1, "vegetables");
        productTypes.put(productTypeCodeIncrement++, vegetablesType);
        ProductType diariesType = new ProductType(2, "diaries");
        productTypes.put(productTypeCodeIncrement++, diariesType);

        products.put(productCodeIncrement++, new Product(1, "Tomato", 2f, vegetablesType));
        products.put(productCodeIncrement++, new Product(2, "Lettuce", 4f, vegetablesType));
        products.put(productCodeIncrement++, new Product(3, "Milk", 3f, diariesType));
        products.put(productCodeIncrement++, new Product(4, "Soy Milk", 2f, diariesType));

        productStorage.put(1, 10);
        productStorage.put(2, 20);
        productStorage.put(3, 10);
        productStorage.put(4, 15);

    }

    @Override
    public boolean createProduct(Product product) {
        products.put(productCodeIncrement++, product);
        return true;
    }

    @Override
    public Product deleteProduct(int productId) {
        return products.remove(productId);
    }

    @Override
    public boolean createProductType(ProductType productType) {
        productTypes.put(productTypeCodeIncrement++, productType);
        return true;
    }

    @Override
    public ProductType deleteProductType(int productTypeId) {
        return productTypes.remove(productTypeId);
    }

    @Override
    public void modifyProductAmount(int productId, int amount) {
        //TODO check for amounts of products before returning product
        Integer currentProductAmount = productStorage.get(productId);
        productStorage.put(productId, currentProductAmount - amount);
    }
}