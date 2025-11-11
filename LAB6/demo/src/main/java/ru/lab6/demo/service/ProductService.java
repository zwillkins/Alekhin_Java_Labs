package ru.lab6.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ru.lab6.demo.model.Product;

@Service
public class ProductService {
    private final Map<Long, Product> productStorage = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public ProductService() {
        createProduct(new Product(null, "Ноуты", 1200.0));
        createProduct(new Product(null, "Мониторы", 2500.50));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productStorage.values());
    }

    public Product getProductById(Long id) {
        return productStorage.get(id);
    }

    public Product createProduct(Product product) {
        Long newId = idCounter.incrementAndGet();
        product.setId(newId);
        productStorage.put(newId, product);
        return product;
    }

    public Product updateProduct(Long id, Product productDetails) {
        if (productStorage.containsKey(id)) {
            productDetails.setId(id);
            productStorage.put(id, productDetails);
            return productDetails;
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        return productStorage.remove(id) != null;
    }
}