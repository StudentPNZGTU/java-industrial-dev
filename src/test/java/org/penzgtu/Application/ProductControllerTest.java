package org.penzgtu.Application;

import org.junit.jupiter.api.Test;
import org.penzgtu.Application.controller.ProductController;
import org.penzgtu.Application.models.product.Product;
import org.penzgtu.Application.builder.ProductBuilder;
import org.penzgtu.Application.services.ProductService;
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

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void shouldReturnListOfProducts() throws Exception {
        Product product1 = new ProductBuilder().withId(1L).withTitle("Product1").withDescription("Description1")
                .withPrice(10.0).build();
        Product product2 = new ProductBuilder().withId(2L).withTitle("Product2").withDescription("Description2")
                .withPrice(20.0).build();
        List<Product> allProducts = Arrays.asList(product1, product2);

        given(productService.getAllProducts()).willReturn(allProducts);

        this.mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'name': 'Product1', 'description': 'Description1', 'price': 10.0}," +
                        "{'id': 2, 'name': 'Product2', 'description': 'Description2', 'price': 20.0}]"));
    }
}

