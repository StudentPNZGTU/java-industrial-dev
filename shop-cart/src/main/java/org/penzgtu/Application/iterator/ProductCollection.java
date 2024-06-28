package org.penzgtu.Application.iterator;

import org.penzgtu.Application.models.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCollection implements IterableCollection {
    private List<Product> products;

    public ProductCollection() {
        this.products = new ArrayList<>();
    }
    public ProductCollection(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product productQuantity) {
        products.add(productQuantity);
    }
    @Override
    public Iterator createIterator() {
        return new ProductIterator(products);
    }
}
