package org.penzgtu.Application.iterator;

import org.penzgtu.Application.models.product.Product;

import java.util.List;

public class ProductIterator implements Iterator<Product> {
    private final List<Product> products;
    private int position;

    public ProductIterator(List<Product> products) {
        this.products = products;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < products.size();
    }

    @Override
    public Product next() {
        if (this.hasNext()) {
            return products.get(position++);
        }
        return null;
    }
    @Override
    public void reset() {
        this.position = 0;
    }
}
