package org.penzgtu.Application.repository;


import org.penzgtu.Application.models.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findTopByUserIdOrderByIdDesc(Long userId);
}
