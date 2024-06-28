package org.penzgtu.Application.repository;

import org.penzgtu.Application.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
