package org.penzgtu.Application;

import org.junit.jupiter.api.Test;
import org.penzgtu.Application.builder.CartBuilder;
import org.penzgtu.Application.controller.CartController;
import org.penzgtu.Application.models.cart.Cart;
import org.penzgtu.Application.models.cart.ProductQuantity;
import org.penzgtu.Application.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @Test
    public void shouldReturnListOfCarts() throws Exception {
        ProductQuantity productQuantity = new ProductQuantity();
        productQuantity.setProductId(1L);
        productQuantity.setQuantity(2);

        List<ProductQuantity> products = Arrays.asList(productQuantity);

        Cart cart1 = new CartBuilder().withId(1L).withUserId(1L).withProducts(products).build();
        Cart cart2 = new CartBuilder().withId(2L).withUserId(2L).withProducts(products).build();
        List<Cart> allCarts = Arrays.asList(cart1, cart2);

        given(cartService.getAllCarts()).willReturn(allCarts);

        this.mockMvc.perform(get("/carts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'userId': 1, 'products': [{'productId': 1, 'quantity': 2}]}," +
                        "{'id': 2, 'userId': 2, 'products': [{'productId': 1, 'quantity': 2}]}]"));
    }
}

