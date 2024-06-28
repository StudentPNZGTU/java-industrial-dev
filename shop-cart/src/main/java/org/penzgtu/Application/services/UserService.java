package org.penzgtu.Application.services;

import org.penzgtu.Application.adapter.GeoLocationAdapter;
import org.penzgtu.Application.models.user.GeoLocation;
import org.penzgtu.Application.models.user.User;
import org.penzgtu.Application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private static final String API_URL = "https://fakestoreapi.com/users";
    private final RestTemplate restTemplate;

    private final PasswordEncoder passwordEncoder;

    private final GeoLocationAdapter geoLocationAdapter;
    UserRepository userRepository;

    @Autowired
    public UserService(RestTemplate restTemplate, PasswordEncoder passwordEncoder,
                       GeoLocationAdapter geoLocationAdapter, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.geoLocationAdapter = geoLocationAdapter;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public long getLastId() {return userRepository.count();}

    public User saveUser(User user) {return userRepository.save(user);}

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public long getUserIdByUsernameAndPassword(String username, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Предположим что наши пароли закодированы в БД
            if (passwordEncoder.matches(rawPassword, passwordEncoder.encode(user.getPassword()))) {
                return user.getId();
            }
        }
        return -1;
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
    }
    /*
        Получение пользователей с API
     */
    public List<User> fetchAndSaveUsers() {
        User[] users = restTemplate.getForObject(API_URL, User[].class);
        if (users != null) {
            userRepository.saveAll(Arrays.asList(users));
            return userRepository.findAll();
        }
        throw new RuntimeException("Failed to fetch products from API");
    }

    public void updateUserGeoLocation(Long userId) {
        User user = getUserById(userId);
        GeoLocation geoLocation = geoLocationAdapter.getGeoLocation();
        user.getAddress().setGeolocation(geoLocation);
        saveUser(user);
    }
}