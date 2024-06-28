package org.penzgtu.Application.iterator;

import org.penzgtu.Application.models.cart.ProductQuantity;

import java.util.ArrayList;
import java.util.List;

public class ProductQuantityCollection implements IterableCollection {
    private List<ProductQuantity> products;

    public ProductQuantityCollection() {
        this.products = new ArrayList<>();
    }
    public ProductQuantityCollection(List<ProductQuantity> products) {
        this.products = products;
    }

    public void addProduct(ProductQuantity productQuantity) {
        products.add(productQuantity);
    }
    @Override
    public Iterator createIterator() {
        return new ProductQuantityIterator(products);
    }
}
