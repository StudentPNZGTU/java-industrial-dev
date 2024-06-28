package org.penzgtu.Application;

import org.junit.jupiter.api.Test;
import org.penzgtu.Application.models.product.Product;
import org.penzgtu.Application.builder.ProductBuilder;
import org.penzgtu.Application.repository.ProductRepository;
import org.penzgtu.Application.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void shouldReturnListOfProducts() {
        Product product1 = new ProductBuilder().withId(1L).withTitle("Product1").withDescription("Description1")
                .withPrice(10.0).build();
        Product product2 = new ProductBuilder().withId(2L).withTitle("Product2").withDescription("Description2")
                .withPrice(20.0).build();
        List<Product> allProducts = Arrays.asList(product1, product2);

        given(productRepository.findAll()).willReturn(allProducts);

        List<Product> products = productService.getAllProducts();
        assertThat(products).isEqualTo(allProducts);
    }
}
