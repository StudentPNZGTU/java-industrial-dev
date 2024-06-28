package org.penzgtu.Application.data;

import com.github.javafaker.Faker;
import org.penzgtu.Application.models.cart.Cart;
import org.penzgtu.Application.models.cart.ProductQuantity;
import org.penzgtu.Application.models.product.Product;
import org.penzgtu.Application.models.product.Rating;
import org.penzgtu.Application.models.user.*;
import org.penzgtu.Application.services.CartService;
import org.penzgtu.Application.services.ProductService;
import org.penzgtu.Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataGenerator {

    private static DataGenerator instance;

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final Faker faker = new Faker();

    @Value("${data.generator.user.count}")
    private int userCount;

    @Value("${data.generator.product.count}")
    private int productCount;

    @Value("${data.generator.cart.count}")
    private int cartCount;

    @Value("${data.generator.cart.product.count}")
    private int cartProductCount;

    @Autowired
    public DataGenerator(UserService userService, ProductService productService, CartService cartService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
    }

    public void init() {
        instance = this;
        generateData();
    }

    public static DataGenerator getInstance() {
        if (instance == null) {
            throw new IllegalStateException("DataGenerator bean has not been initialized yet");
        }
        return instance;
    }

    private void generateData() {
        generateUsers(userCount);
        generateProducts(productCount);
        generateCarts(cartCount);
    }

    private void generateUsers(int n) {
        for (int i = 0; i < n; i++) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            user.setUsername(faker.name().username());
            user.setPassword(faker.internet().password());
            Name name = new Name();
            name.setLastname(faker.name().lastName());
            name.setFirstname(faker.name().firstName());
            user.setName(name);

            Address address = new Address();
            address.setCity(faker.address().city());
            address.setStreet(faker.address().streetName());
            address.setNumber(faker.number().numberBetween(1, 100));
            address.setZipcode(faker.address().zipCode());

            GeoLocation geoLocation = new GeoLocation();
            geoLocation.setLat(faker.address().latitude());
            geoLocation.setLng(faker.address().longitude());
            address.setGeolocation(geoLocation);
            user.setAddress(address);

            user.setPhone(faker.phoneNumber().phoneNumber());
            user.set__v(0);
            userService.saveUser(user);
        }
    }

    private void generateProducts(int n) {
        for (int i = 0; i < n; i++) {
            Product product = new Product();
            product.setId(productService.getLastId() + 1);
            product.setTitle(faker.commerce().productName());
            product.setPrice(faker.number().randomDouble(2, 10, 1000));
            product.setDescription(faker.lorem().sentence());
            product.setCategory(faker.commerce().department());
            product.setImage(faker.internet().image());
            Rating rating = new Rating();
            rating.setRate(faker.number().randomDouble(2, 1, 5));
            rating.setCount(faker.number().numberBetween(1, 100));
            product.setRating(rating);
            productService.saveProduct(product);
        }
    }

    private void generateCarts(int n) {
        for (int i = 0; i < n; i++) {
            Cart cart = new Cart();
            cart.setUserId(faker.number().numberBetween(1, 10));
            cart.setDate(faker.date().between(
                    new Date(2023, 1, 1), new Date(2024, 12, 28))
            );
            cart.setProducts(generateProductQuantities(cartProductCount));
            cartService.saveCart(cart);
        }
    }

    private List<ProductQuantity> generateProductQuantities(int n) {
        List<ProductQuantity> products = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ProductQuantity productQuantity = new ProductQuantity();
            productQuantity.setProductId(faker.number().numberBetween(1, 10));
            productQuantity.setQuantity(faker.number().numberBetween(1, 10));
            products.add(productQuantity);
        }
        return products;
    }
}
