package org.penzgtu.Application.builder;

import org.penzgtu.Application.models.user.Address;
import org.penzgtu.Application.models.user.Name;
import org.penzgtu.Application.models.user.User;

public class UserBuilder {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Name name;

    private Address address;
    private String phone;
    private int __v;

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withName(Name name) {
        this.name = name;
        return this;
    }

    public UserBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public UserBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }


    public UserBuilder withVersion(int version) {
        this.__v = version;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setAddress(address);
        user.setPhone(phone);
        user.set__v(__v);
        return user;
    }
}

