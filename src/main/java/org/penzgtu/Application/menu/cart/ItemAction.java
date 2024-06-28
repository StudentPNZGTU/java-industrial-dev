package org.penzgtu.Application.menu.cart;

import org.penzgtu.Application.iterator.Iterator;
import org.penzgtu.Application.iterator.ProductCollection;
import org.penzgtu.Application.iterator.ProductQuantityCollection;
import org.penzgtu.Application.menu.ActionMenu;
import org.penzgtu.Application.models.cart.Cart;
import org.penzgtu.Application.models.cart.ProductQuantity;
import org.penzgtu.Application.models.product.Product;
import org.penzgtu.Application.services.CartService;

import java.util.Date;
import java.util.List;

public class ItemAction extends ActionMenu {

    private Iterator iterator;

    private final List<Product> products;
    private final CartService cartService;
    private Cart cart;

    private final long user_id;
    public ItemAction(CartService cartService, List<Product> products, long user_id) {
        super("What product do you want to add?", "Add item to cart");
        this.cartService = cartService;
        this.user_id = user_id;
        this.products = products;
        updateCart();
    }

    @Override
    public void executeCustomAction() {
        updateCart();
        clear();
        Long product_id = this.prompt("id: ", Long.class);
        if (productExists(product_id)) {
            addProduct(product_id);
            this.println("Product number "+product_id+" added to cart");
        } else {
            this.println("Product not found");
        }
    }

    public void addProduct(long product_id) {
        ProductQuantityCollection productQuantityCollection = new ProductQuantityCollection(cart.getProducts());
        iterator = productQuantityCollection.createIterator();
        while (iterator.hasNext()) {
            ProductQuantity productQuantity = (ProductQuantity) iterator.next();
            if (productQuantity.getProductId() == product_id) {
                productQuantity.setQuantity(productQuantity.getQuantity()+1);
                break;
            }
        }
        ProductQuantity productQuantity = new ProductQuantity();
        productQuantity.setProductId(product_id);
        productQuantity.setQuantity(1);
        cart.getProducts().add(productQuantity);
        cart.setDate(new Date());
        cartService.saveCart(cart);
    }

    public boolean productExists(long product_id) {
        ProductCollection productCollection = new ProductCollection(products);
        iterator = productCollection.createIterator();
        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            if (product.getId() == product_id) {
                return true;
            }
        }
        return false;
    }
    public void updateCart() {
        this.cart = cartService.getCartById(cartService.getLastCartIdByUserId(user_id));
    }
}
