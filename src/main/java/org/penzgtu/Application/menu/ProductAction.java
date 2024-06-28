package org.penzgtu.Application.menu;

import org.penzgtu.Application.services.ProductService;

public class ProductAction extends ActionMenu {

    private final ProductService productService;
    public ProductAction(ProductService productService) {
        super("", "");
        this.productService = productService;
    }
    @Override
    public void executeCustomAction() {

    }

    public void displayProducts() {

    }
}
