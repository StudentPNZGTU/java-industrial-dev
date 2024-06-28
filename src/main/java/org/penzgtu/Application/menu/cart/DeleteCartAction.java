package org.penzgtu.Application.menu.cart;

import org.penzgtu.Application.menu.ActionMenu;
import org.penzgtu.Application.services.CartService;


public class DeleteCartAction extends ActionMenu {

    private final CartService cartService;
    private final long user_id;
    public DeleteCartAction(CartService cartService, long user_id) {
        super("Remove items from cart?", "Empty shopping");
        this.cartService = cartService;
        this.user_id = user_id;
    }

    @Override
    public void executeCustomAction() {
        clear();
        String y = this.prompt("(Y/n) ", String.class);
        if (y.equals("y")) {
            removeItemsFromCart();
            this.println("You have emptied your cart");
        } else {
            this.println("Operation canceled..");
        }
    }
    public void removeItemsFromCart() {
        cartService.saveCartForNewUser(user_id);
        // cartService.deleteCartById(cartService.getLastCartIdByUserId(user_id)); - можно если нужно
    }
}