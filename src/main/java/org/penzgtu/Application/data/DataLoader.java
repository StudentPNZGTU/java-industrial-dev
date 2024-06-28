package org.penzgtu.Application.data;

import org.penzgtu.Application.services.CartService;
import org.penzgtu.Application.services.ProductService;
import org.penzgtu.Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public DataLoader(UserService userService, ProductService productService, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
    }

    // @PostConstruct
    public void loadData() {
        try {
            System.out.println("Fetching and saving users...");
            userService.fetchAndSaveUsers();
            System.out.println("Users fetched and saved.");

            System.out.println("Fetching and saving products...");
            productService.fetchAndSaveProducts();
            System.out.println("Products fetched and saved.");

            System.out.println("Fetching and saving carts...");
            cartService.fetchAndSaveCarts();
            System.out.println("Carts fetched and saved.");
        } catch (Exception e) {
            System.err.println("Failed to fetch and save data: " + e.getMessage());
        }
    }
}
