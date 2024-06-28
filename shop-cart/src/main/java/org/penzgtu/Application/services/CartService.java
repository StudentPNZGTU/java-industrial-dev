package org.penzgtu.Application.services;

import org.penzgtu.Application.models.cart.Cart;
import org.penzgtu.Application.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CartService {

    private static final String API_URL = "https://fakestoreapi.com/carts";
    private RestTemplate restTemplate;
    private CartRepository cartRepository;

    @Autowired
    public CartService(RestTemplate restTemplate, CartRepository cartRepository) {
        this.restTemplate = restTemplate;
        this.cartRepository = cartRepository;
    }

    public List<Cart> fetchAndSaveCarts() {
        Cart[] carts = restTemplate.getForObject(API_URL, Cart[].class);
        if (carts != null) {
            cartRepository.saveAll(Arrays.asList(carts));
            return cartRepository.findAll();
        }
        throw new RuntimeException("Failed to fetch carts from API");
    }
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }


    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart saveCartForNewUser(long user_id) {
        Cart cart = new Cart();
        cart.setUserId(user_id);
        cart.setDate(new Date());
        cart.setProducts(new ArrayList<>());
        cart.set__v(0);
        cartRepository.save(cart);
        return cart;
    }
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    public Long getLastCartIdByUserId(Long userId) {
        return cartRepository.findTopByUserIdOrderByIdDesc(userId)
                .map(Cart::getId)
                .orElseThrow(() -> new RuntimeException("No cart found for userId: " + userId));
    }
    public void deleteCartById(Long id) {
        if (!cartRepository.existsById(id)) {
            throw new RuntimeException("Cart not found");
        }
    }
}