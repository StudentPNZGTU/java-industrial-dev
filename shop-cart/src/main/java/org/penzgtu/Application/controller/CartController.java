package org.penzgtu.Application.controller;

import jakarta.validation.Valid;
import org.penzgtu.Application.models.cart.Cart;
import org.penzgtu.Application.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/fetch")
    public List<Cart> fetchAndSaveCarts() {
        return cartService.fetchAndSaveCarts();
    }

    @PostMapping
    public Cart saveCart(@Valid @RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCartById(@PathVariable Long id) {
        cartService.deleteCartById(id);
    }
}
