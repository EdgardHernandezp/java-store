package com.globant.repos;

import com.globant.pojos.Product;
import com.globant.pojos.ProductType;
import com.globant.pojos.StockOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public StockOperation[] updateStock(StockOperation[] stockOperations) {
        List<StockOperation> unfulfilledStockOps = new ArrayList<>();
        for (StockOperation stockOp : stockOperations) {
            if (stockOp.getQuantity() < 0)
                subtractStock(stockOp, unfulfilledStockOps);
            else
                addStock(stockOp);
        }
        return unfulfilledStockOps.toArray(new StockOperation[unfulfilledStockOps.size()]);
    }

    private static void addStock(StockOperation stockOp) {
        int productCode = stockOp.getProduct().getCode();
        Integer currentProductQuantity = stock.get(productCode);
        stock.put(productCode, currentProductQuantity + stockOp.getQuantity());
    }

    private void subtractStock(StockOperation stockOp, List<StockOperation> unfulfilledStockOps) {
        int productCode = stockOp.getProduct().getCode();
        Integer currentProductQuantity = stock.get(productCode);
        int remainingProductStock = currentProductQuantity + stockOp.getQuantity();
        if (remainingProductStock <= 0) {
            stock.remove(productCode);
            if (remainingProductStock < 0)
                unfulfilledStockOps.add(new StockOperation(stockOp.getProduct(), Math.abs(remainingProductStock)));
        } else
            stock.put(productCode, remainingProductStock);
    }

    @Override
    public Integer checkExistingStock(int productId) {
        return stock.get(productId);
    }

    @Override
    public Optional<Product> findProductByCode(int productCode) {
        return Optional.ofNullable(products.get(productCode));
    }

    @Override
    public List<Product> searchProductByName(final String productName) {
        return products.entrySet().stream().map(entry -> entry.getValue())
                .filter(product -> Pattern.compile(Pattern.quote(productName), Pattern.CASE_INSENSITIVE).matcher(product.getName()).find())
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAvailableProducts() {
        return stock.entrySet().stream().filter(entry -> entry.getValue() > 0).map(entry -> products.get(entry.getKey()))
                .collect(Collectors.toList());
    }
}
