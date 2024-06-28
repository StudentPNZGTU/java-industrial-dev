package org.penzgtu.Application.builder;

import org.penzgtu.Application.models.product.Product;

public class ProductBuilder {
    private Long id;
    private String title;
    private String description;
    private Double price;

    public ProductBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withPrice(Double price) {
        this.price = price;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }
}
