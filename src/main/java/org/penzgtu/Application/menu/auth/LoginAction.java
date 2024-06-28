package org.penzgtu.Application.menu.auth;

import io.bretty.console.view.MenuView;
import org.penzgtu.Application.dto.AuthRequest;
import org.penzgtu.Application.menu.ActionMenu;
import org.penzgtu.Application.services.AuthService;
import org.penzgtu.Application.services.CartService;
import org.penzgtu.Application.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginAction extends ActionMenu {
    private final AuthService authService;
    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public LoginAction(AuthService authService, CartService cartService, ProductService productService) {
        super("Login", "login");
        this.authService = authService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @Override
    public void executeCustomAction() {
        long user_id = login();

        if (user_id == -1) {
            System.exit(0);
        }

        MenuView loginMenu = new LoginMenu(user_id, cartService, productService);
        loginMenu.setParentView(this);
        loginMenu.display();
    }

    public long login() {
        long user_id = -1;
        short count = 0;
        clear();
        while (user_id == -1 && count < 4) {
            String username = this.prompt("Enter your username: ", String.class);
            String password = this.prompt("Enter your password: ", String.class);
            user_id = Long.parseLong(authService.login(getAuthRequest(username, password)).getToken());
            count++;
        }
        return user_id;
    }

    public AuthRequest getAuthRequest(String username, String password) {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(username);
        authRequest.setPassword(password);
        return authRequest;
    }
}
