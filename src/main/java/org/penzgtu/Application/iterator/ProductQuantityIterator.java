package org.penzgtu.Application.iterator;

import org.penzgtu.Application.models.cart.ProductQuantity;

import java.util.List;

public class ProductQuantityIterator implements Iterator<ProductQuantity> {
    private final List<ProductQuantity> products;
    private int position;

    public ProductQuantityIterator(List<ProductQuantity> products) {
        this.products = products;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < products.size();
    }

    @Override
    public ProductQuantity next() {
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
