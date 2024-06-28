package org.penzgtu.Application.models.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {

    @Embedded
    private Address address;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String password;

    @Embedded
    private Name name;

    private String phone;

    @Version
    private int __v;
}