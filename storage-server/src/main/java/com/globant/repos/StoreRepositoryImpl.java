package com.globant.repos;

import com.globant.pojos.Product;
import com.globant.pojos.ProductType;
import java.util.HashMap;
import java.util.Map;

public class StoreRepositoryImpl implements StoreRepository {
    private static final Map<Integer, Product> products = new HashMap<>();
    private static final Map<Integer, ProductType> productTypes = new HashMap<>();
    private static final Map<Integer, Integer> stock = new HashMap<>();
    private static StoreRepositoryImpl instance;
    private static int productCodeIncrement = 1;
    private static int productTypeCodeIncrement = 1;

    static {
        ProductType vegetablesType = new ProductType(1, "vegetables");
        productTypes.put(productTypeCodeIncrement++, vegetablesType);
        ProductType diariesType = new ProductType(2, "diaries");
        productTypes.put(productTypeCodeIncrement++, diariesType);

        products.put(productCodeIncrement++, new Product(1, "Tomato", 2f, vegetablesType));
        products.put(productCodeIncrement++, new Product(2, "Lettuce", 4f, vegetablesType));
        products.put(productCodeIncrement++, new Product(3, "Milk", 3f, diariesType));
        products.put(productCodeIncrement++, new Product(4, "Soy Milk", 2f, diariesType));

        stock.put(1, 10);
        stock.put(2, 20);
        stock.put(3, 10);
        stock.put(4, 15);

    }

    private StoreRepositoryImpl() {
    }

    public static synchronized StoreRepositoryImpl getInstance() {
        if (instance == null)
            instance = new StoreRepositoryImpl();
        return instance;
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
    public boolean updateStock(int productId, int quantity) {
        Integer currentProductQuantity = stock.get(productId);
        if (currentProductQuantity < quantity)
            return false;
        stock.put(productId, currentProductQuantity - quantity);
        return true;
    }

    @Override
    public Integer checkExistingStock(int productId) {
        return stock.get(productId);
    }
}
