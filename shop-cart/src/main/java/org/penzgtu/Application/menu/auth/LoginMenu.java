package org.penzgtu.Application.menu.auth;

import io.bretty.console.view.MenuView;
import org.penzgtu.Application.menu.cart.CartAction;
import org.penzgtu.Application.menu.cart.DeleteCartAction;
import org.penzgtu.Application.menu.cart.ItemAction;
import org.penzgtu.Application.menu.product.ProductsViewAction;
import org.penzgtu.Application.models.product.Product;
import org.penzgtu.Application.services.CartService;
import org.penzgtu.Application.services.ProductService;

import java.util.List;

public class LoginMenu extends MenuView {

    private final CartService cartService;

    public LoginMenu(long user_id, CartService cartService, ProductService productService) {
        super("User", "user");
        this.cartService = cartService;
        List<Product> products = productService.getAllProducts();
        this.addMenuItem(new ProductsViewAction(products));
        this.addMenuItem(new CartAction(cartService, products, user_id));
        this.addMenuItem(new ItemAction(cartService, products, user_id));
        this.addMenuItem(new DeleteCartAction(cartService, user_id));
    }
}
