package org.penzgtu.Application.builder;

import org.penzgtu.Application.models.cart.Cart;
import org.penzgtu.Application.models.cart.ProductQuantity;

import java.util.List;

public class CartBuilder {
    private Long id;
    private Long userId;
    private List<ProductQuantity> products;

    private int __v;

    public CartBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CartBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public CartBuilder withProducts(List<ProductQuantity> products) {
        this.products = products;
        return this;
    }

    public CartBuilder withVersion(int version) {
        this.__v = version;
        return this;
    }

    public Cart build() {
        Cart cart = new Cart();
        cart.setId(id);
        cart.setUserId(userId);
        cart.setProducts(products);
        cart.set__v(__v);
        return cart;
    }
}
