package org.penzgtu.Application;

import org.junit.jupiter.api.Test;
import org.penzgtu.Application.builder.CartBuilder;
import org.penzgtu.Application.models.cart.Cart;
import org.penzgtu.Application.models.cart.ProductQuantity;
import org.penzgtu.Application.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void shouldFindNoCartsIfRepositoryIsEmpty() {
        List<Cart> carts = cartRepository.findAll();
        assertThat(carts).isEmpty();
    }

    @Test
    public void shouldStoreACart() {
        ProductQuantity productQuantity = new ProductQuantity();
        productQuantity.setProductId(1L);
        productQuantity.setQuantity(2);

        List<ProductQuantity> products = Arrays.asList(productQuantity);

        Cart cart = new CartBuilder().withUserId(1L).withProducts(products).build();
        cart = cartRepository.save(cart);
        assertThat(cart).hasFieldOrPropertyWithValue("userId", 1L);
        assertThat(cart.getProducts().get(0)).hasFieldOrPropertyWithValue("productId", 1L);
        assertThat(cart.getProducts().get(0)).hasFieldOrPropertyWithValue("quantity", 2);
    }
}
