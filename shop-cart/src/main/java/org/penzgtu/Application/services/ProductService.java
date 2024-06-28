package org.penzgtu.Application.services;

import org.penzgtu.Application.models.product.Product;
import org.penzgtu.Application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    private static final String API_URL = "https://fakestoreapi.com/products";

    private RestTemplate restTemplate;
    ProductRepository productRepository;

    @Autowired
    public ProductService(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public long getLastId() {return productRepository.count();}

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Cart not found");
        }
    }

    public List<Product> fetchAndSaveProducts() {
        Product[] products = restTemplate.getForObject(API_URL, Product[].class);
        if (products != null) {
            productRepository.saveAll(Arrays.asList(products));
            return Arrays.asList(products);
        } else {
            throw new RuntimeException("Failed to fetch products from API");
        }
    }

    public boolean productExists(Long id) {
        return productRepository.existsById(id);
    }
}

