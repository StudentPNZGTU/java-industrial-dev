package org.penzgtu.Application;

import org.junit.jupiter.api.Test;
import org.penzgtu.Application.builder.CartBuilder;
import org.penzgtu.Application.models.cart.Cart;
import org.penzgtu.Application.models.cart.ProductQuantity;
import org.penzgtu.Application.repository.CartRepository;
import org.penzgtu.Application.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @Test
    public void shouldReturnListOfCarts() {
        ProductQuantity productQuantity = new ProductQuantity();
        productQuantity.setProductId(1L);
        productQuantity.setQuantity(2);

        List<ProductQuantity> products = Arrays.asList(productQuantity);

        Cart cart1 = new CartBuilder().withId(1L).withUserId(1L).withProducts(products).build();
        Cart cart2 = new CartBuilder().withId(2L).withUserId(2L).withProducts(products).build();
        List<Cart> allCarts = Arrays.asList(cart1, cart2);

        given(cartRepository.findAll()).willReturn(allCarts);

        List<Cart> carts = cartService.getAllCarts();
        assertThat(carts).isEqualTo(allCarts);
    }
}
