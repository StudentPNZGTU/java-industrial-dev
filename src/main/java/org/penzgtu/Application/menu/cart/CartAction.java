package org.penzgtu.Application.menu.cart;

import org.penzgtu.Application.iterator.Iterator;
import org.penzgtu.Application.iterator.ProductCollection;
import org.penzgtu.Application.menu.ActionMenu;
import org.penzgtu.Application.models.product.Product;
import org.penzgtu.Application.models.cart.ProductQuantity;
import org.penzgtu.Application.services.CartService;

import java.util.List;

public class CartAction extends ActionMenu {
    private List<ProductQuantity> productQuantities;
    private final List<Product> products;
    private final CartService cartService;
    private final long user_id;

    private Iterator iterator;

    public CartAction(CartService cartService, List<Product> products, long user_id) {
        super("Shopping cart", "View cart");
        this.cartService = cartService;
        this.products = products;
        this.user_id = user_id;
        updateCart();
    }
    @Override
    public void executeCustomAction() {
        clear();
        displayCart();
    }
    public void displayCart() {
        updateCart();
        String[] headers = {"Quantity", "ID", "Title", "Price", "Description", "Category", "Image"};
        String[][] products = new String[productQuantities.size()][7];
        for (int i = 0; i < productQuantities.size(); i++) {
            Product product = getProductById(productQuantities.get(i).getProductId());
            products[i][0] = String.valueOf(productQuantities.get(i).getQuantity());
            products[i][1] = String.valueOf(product.getId());
            products[i][2] = product.getTitle();
            products[i][3] = String.valueOf(product.getPrice());
            products[i][4] = product.getDescription();
            products[i][5] = product.getCategory();
            products[i][6] = product.getImage();
        }
        outAsciiTable(headers, products);
    }
    public Product getProductById(long product_id) {
        ProductCollection productCollection = new ProductCollection(products);
        iterator = productCollection.createIterator();
        while (iterator.hasNext()) {
            Product product = (Product) iterator.next();
            if (product.getId() == product_id) {
                return product;
            }
        }
        throw new RuntimeException("Product not found");
    }

    public void updateCart() {
        this.productQuantities = cartService.getCartById(cartService.getLastCartIdByUserId(user_id)).getProducts();
    }
}
