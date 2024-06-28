package org.penzgtu.Application;

import org.junit.jupiter.api.Test;
import org.penzgtu.Application.builder.UserBuilder;
import org.penzgtu.Application.models.user.Address;
import org.penzgtu.Application.models.user.GeoLocation;
import org.penzgtu.Application.models.user.Name;
import org.penzgtu.Application.models.user.User;
import org.penzgtu.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldFindNoUsersIfRepositoryIsEmpty() {
        List<User> users = userRepository.findAll();
        assertThat(users).isEmpty();
    }

    @Test
    public void shouldStoreAUser() {
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

        User user = new UserBuilder().withUsername("user1")
                .withEmail("user1@example.com")
                .withPassword("password1")
                .withName(name)
                .withAddress(address)
                .withPhone("1234567890")
                .withVersion(1)
                .build();

        user = userRepository.save(user);
        assertThat(user).hasFieldOrPropertyWithValue("username", "user1");
        assertThat(user).hasFieldOrPropertyWithValue("email", "user1@example.com");
        assertThat(user).hasFieldOrPropertyWithValue("password", "password1");
        assertThat(user).hasFieldOrPropertyWithValue("name.firstname", "John");
        assertThat(user).hasFieldOrPropertyWithValue("name.lastname", "Doe");
        assertThat(user).hasFieldOrPropertyWithValue("address.city", "New York");
        assertThat(user).hasFieldOrPropertyWithValue("address.street", "Wall Street");
        assertThat(user).hasFieldOrPropertyWithValue("address.number", 10);
        assertThat(user).hasFieldOrPropertyWithValue("address.zipcode", "10005");
        assertThat(user).hasFieldOrPropertyWithValue("phone", "1234567890");
        assertThat(user).hasFieldOrPropertyWithValue("__v", 1);
    }
}
