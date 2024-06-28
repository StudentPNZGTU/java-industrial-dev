package org.penzgtu.Application.menu.auth;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import io.bretty.console.view.ActionView;
import jakarta.el.MethodInfo;
import org.penzgtu.Application.dto.RegisterRequest;
import org.penzgtu.Application.models.cart.Cart;
import org.penzgtu.Application.models.cart.ProductQuantity;
import org.penzgtu.Application.models.product.Product;
import org.penzgtu.Application.models.user.Address;
import org.penzgtu.Application.models.user.Name;
import org.penzgtu.Application.models.user.User;
import org.penzgtu.Application.services.CartService;
import org.penzgtu.Application.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



public class RegistrationAction extends ActionView {

    private final UserService userService;
    private final CartService cartService;


    public RegistrationAction(UserService userService, CartService cartService) {
        super("registration window", "register");
        this.userService = userService;
        this.cartService = cartService;
    }
    @Override
    public void executeCustomAction() {

        try {
            long last_id = userService.getLastId()+1;
            displayUserForm();
            RegisterRequest requestEntity = populateUser(last_id);
            displayAddressForm();
            requestEntity.getUser().setAddress(populateUserAddress());
            userService.saveUser(requestEntity.getUser());
            userService.updateUserGeoLocation(requestEntity.getUser().getId());
            cartService.saveCartForNewUser(last_id);
        } catch (ClassNotFoundException ignored) {}
    }


    public RegisterRequest populateUser(long last_id) {
        RegisterRequest request = new RegisterRequest(new User());
        User user = request.getUser();
        user.setId(last_id);
        user.setName(new Name());
        user.setUsername(this.prompt("Username: ", String.class));
        user.setPassword(this.prompt("Password: ", String.class));
        user.setEmail(this.prompt("Email: ", String.class));
        user.getName().setFirstname(this.prompt("First Name:", String.class));
        user.getName().setLastname(this.prompt("Last Name: ", String.class));
        user.setPhone(this.prompt("Phone: ", String.class));
        return request;
    }

    public Address populateUserAddress() {
        Address address = new Address();
        address.setCity(this.prompt("City: ", String.class));
        address.setStreet(this.prompt("Street: ", String.class));
        address.setNumber(this.prompt("Number: ", Integer.class));
        address.setZipcode(this.prompt("Zipcode: ", String.class));
        return address;
    }

    public void displayUserForm() throws ClassNotFoundException {
        List<MethodInfo> methods = Arrays.asList(
                new MethodInfo(
                        "setUsername", Class.forName(
                        "java.lang.String"), null
                ),
                new MethodInfo(
                        "setPassword",
                        Class.forName("java.lang.String"), null
                ),
                new MethodInfo(
                        "setEmail",
                        Class.forName("java.lang.String"), null
                ),
                new MethodInfo(
                        "setFirstName",
                        Class.forName("java.lang.String"), null
                ),
                new MethodInfo(
                        "setLastName",
                        Class.forName("java.lang.String"), null
                ),
                new MethodInfo(
                        "setPhone",
                        Class.forName("java.lang.String"), null
                ),
                new MethodInfo(
                        "setAddress",
                        Class.forName("org.penzgtu.Application.models.user.Address"), null
                )
        );
        displayEntityTable(methods, "fill User:", "Parameters");
    }


    public void displayAddressForm() throws ClassNotFoundException {
        List<MethodInfo> methods = Arrays.asList(
                new MethodInfo(
                        "setCity",
                        Class.forName("java.lang.String"), null
                ),
                new MethodInfo(
                        "setStreet",
                        Class.forName("java.lang.String"), null
                ),
                new MethodInfo(
                        "setNumber",
                        Class.forName("java.lang.String"), null
                ),
                new MethodInfo(
                        "setZipCode",
                        Class.forName("java.lang.String"), null
                )
        );
        displayEntityTable(methods, "fill Address:", "Parameters");
    }


    public void displayEntityTable(List<MethodInfo> methods, String column1, String column2) {
        String table = AsciiTable.getTable(methods, Arrays.asList(
                new Column().header(column1).dataAlign(HorizontalAlign.LEFT).with(MethodInfo::getName),
                new Column().header(column2).dataAlign(HorizontalAlign.LEFT).with(method -> {
                    return method.getReturnType().getSimpleName();
                })
        ));
        this.println(table);
    }
}
