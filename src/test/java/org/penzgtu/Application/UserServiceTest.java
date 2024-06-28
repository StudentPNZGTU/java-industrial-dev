package org.penzgtu.Application;

import org.junit.jupiter.api.Test;
import org.penzgtu.Application.builder.UserBuilder;
import org.penzgtu.Application.models.user.Address;
import org.penzgtu.Application.models.user.GeoLocation;
import org.penzgtu.Application.models.user.Name;
import org.penzgtu.Application.models.user.User;
import org.penzgtu.Application.repository.UserRepository;
import org.penzgtu.Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void shouldReturnListOfUsers() {
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

        given(userRepository.findAll()).willReturn(allUsers);

        List<User> users = userService.getAllUsers();
        assertThat(users).isEqualTo(allUsers);
    }
}
