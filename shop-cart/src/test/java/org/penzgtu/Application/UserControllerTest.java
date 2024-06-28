package org.penzgtu.Application;


import org.junit.jupiter.api.Test;
import org.penzgtu.Application.builder.UserBuilder;
import org.penzgtu.Application.controller.UserController;
import org.penzgtu.Application.models.user.Address;
import org.penzgtu.Application.models.user.GeoLocation;
import org.penzgtu.Application.models.user.Name;
import org.penzgtu.Application.models.user.User;
import org.penzgtu.Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnListOfUsers() throws Exception {
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setLat("40.730610");
        geoLocation.setLng("-73.935242");

        Address address = new Address();
        address.setGeolocation(geoLocation);
        address.setCity("New York");
        address.setStreet("Wall Street");
        address.setNumber(10);
        address.setZipcode("10005");

        Name name = new Name();
        name.setFirstname("John");
        name.setLastname("Doe");

        User user1 = new UserBuilder().withId(1L)
                .withUsername("user1")
                .withEmail("user1@example.com")
                .withPassword("password1")
                .withName(name)
                .withAddress(address)
                .withPhone("1234567890")
                .withVersion(1)
                .build();

        User user2 = new UserBuilder().withId(2L)
                .withUsername("user2")
                .withEmail("user2@example.com")
                .withPassword("password2")
                .withName(name)
                .withAddress(address)
                .withPhone("0987654321")
                .withVersion(1)
                .build();

        List<User> allUsers = Arrays.asList(user1, user2);

        given(userService.getAllUsers()).willReturn(allUsers);

        this.mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id': 1, 'username': 'user1', 'email': " +
                        "'user1@example.com', 'password': 'password1', 'name': {'firstname': 'John', 'lastname': " +
                        "'Doe'}, 'address': {'geolocation': {'lat': '40.730610', 'lng': '-73.935242'}, 'city': " +
                        "'New York', 'street': 'Wall Street', 'number': 10, 'zipcode': '10005'}, 'phone': " +
                        "'1234567890', '__v': 1}," +
                        "{'id': 2, 'username': 'user2', 'email': 'user2@example.com', 'password': " +
                        "'password2', 'name': {'firstname': 'John', 'lastname': 'Doe'}, 'address': " +
                        "{'geolocation': {'lat': '40.730610', 'lng': '-73.935242'}, 'city': 'New York', " +
                        "'street': 'Wall Street', 'number': 10, 'zipcode': '10005'}, 'phone': '0987654321', " +
                        "'__v': 1}]"));
    }
}

