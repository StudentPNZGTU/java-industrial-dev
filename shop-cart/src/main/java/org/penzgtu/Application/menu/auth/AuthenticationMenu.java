package org.penzgtu.Application.menu.auth;

import io.bretty.console.view.MenuView;
import org.penzgtu.Application.data.DataGenerator;
import org.penzgtu.Application.data.DataLoader;
import org.penzgtu.Application.menu.admin.AdminMenu;
import org.penzgtu.Application.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMenu extends MenuView {

    private final UserService userService;
    private final AuthService authService;

    private final CartService cartService;
    private final ProductService productService;

    private final DatabaseService databaseService;

    private final DataGenerator dataGenerator;

    private final DataLoader dataLoader;

    @Autowired
    public AuthenticationMenu(UserService userService, AuthService authService, ProductService productService,
                              CartService cartService, DatabaseService databaseService, DataGenerator dataGenerator,
                              DataLoader dataLoader) {
        super("Authentication", "auth");
        this.userService = userService;
        this.authService = authService;
        this.cartService = cartService;
        this.productService = productService;
        this.databaseService = databaseService;
        this.dataGenerator = dataGenerator;
        this.dataLoader = dataLoader;
        this.addMenuItem(new LoginAction(authService, cartService, productService));
        this.addMenuItem(new RegistrationAction(userService, cartService));
        this.addMenuItem(new AdminMenu(databaseService, dataGenerator, dataLoader));
    }

}