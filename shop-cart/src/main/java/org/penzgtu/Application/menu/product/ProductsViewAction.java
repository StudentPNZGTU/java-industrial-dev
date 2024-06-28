package org.penzgtu.Application.menu.product;

import org.penzgtu.Application.menu.ActionMenu;
import org.penzgtu.Application.models.product.Product;

import java.util.List;

public class ProductsViewAction extends ActionMenu {
    private final List<Product> products;

    public ProductsViewAction(List<Product> products) {
        super("Pruducts", "View Products");
        this.products = products;
    }

    @Override
    public void executeCustomAction() {
        clear();
        displayAllProducts();
    }

    public void displayAllProducts() {
        String[] headers = {"ID", "Title", "Price", "Description", "Category", "Image"};
        outAsciiTable(headers, convertListToArray(products));
    }

    private static String[][] convertListToArray(List<Product> products) {
        String[][] data = new String[products.size()][6];
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            data[i][0] = String.valueOf(product.getId());
            data[i][1] = product.getTitle();
            data[i][2] = String.valueOf(product.getPrice());
            data[i][3] = product.getDescription();
            data[i][4] = product.getCategory();
            data[i][5] = product.getImage();
        }
        return data;
    }
}
