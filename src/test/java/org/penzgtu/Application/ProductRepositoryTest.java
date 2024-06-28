package org.penzgtu.Application;

import org.junit.jupiter.api.Test;
import org.penzgtu.Application.builder.ProductBuilder;
import org.penzgtu.Application.models.product.Product;
import org.penzgtu.Application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldFindNoProductsIfRepositoryIsEmpty() {
        List<Product> products = productRepository.findAll();
        assertThat(products).isEmpty();
    }

    @Test
    public void shouldStoreAProduct() {
        Product product = new ProductBuilder().withTitle("Product1").withDescription("Description1")
                .withPrice(10.0).build();
        product = productRepository.save(product);
        assertThat(product).hasFieldOrPropertyWithValue("name", "Product1");
        assertThat(product).hasFieldOrPropertyWithValue("description", "Description1");
        assertThat(product).hasFieldOrPropertyWithValue("price", 10.0);
    }
}
